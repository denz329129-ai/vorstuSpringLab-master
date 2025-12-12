package dev.vorstu.services;

import dev.vorstu.dto.GroupDto;
import dev.vorstu.dto.mapping.GroupMapping;
import dev.vorstu.entity.Group;
import dev.vorstu.jwt.JwtAuthentication;
import dev.vorstu.dto.jwt.JwtRequest;
import dev.vorstu.dto.jwt.JwtResponse;
import dev.vorstu.entity.User;
import dev.vorstu.jwt.JwtProvider;
import dev.vorstu.repositories.GroupRepository;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    //todo concurrenthashmap как работает хэш мап, set в целом
    private final Map<String, String> refreshStorage = new HashMap<>();

    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final GroupRepository groupRepository;
    private final GroupMapping groupMapping;

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userService.getByLogin(authRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        String encodedPassword = user.getPassword().getPassword();

        if (passwordEncoder.matches(authRequest.getPassword(), encodedPassword) && (user.getEnable() == true)) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getUsername(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new BadCredentialsException("Неправильный пароль");
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login)
                        .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login)
                        .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new BadCredentialsException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }


    public List<GroupDto> getGroups() {

        List<Group> groups = groupRepository.findAll();

        return groups.stream()
                .map(group -> groupMapping.toDto(group))
                .collect(Collectors.toList());
    }
}
