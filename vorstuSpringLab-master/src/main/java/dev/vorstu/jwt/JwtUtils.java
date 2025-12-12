package dev.vorstu.jwt;


import dev.vorstu.enums.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        jwtInfoToken.setUserId(claims.get("id", Long.class));
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {

        String role = claims.get("roles", String.class);

        return Set.of(Role.valueOf(role));

    }
}
