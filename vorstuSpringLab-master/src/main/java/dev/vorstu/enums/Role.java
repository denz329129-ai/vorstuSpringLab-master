package dev.vorstu.enums;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"),
    STUDENT("USER"),
    TEACHER("TEACHER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
