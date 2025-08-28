package com.jortega.levelup.auth.infrastructure;

import com.jortega.levelup.auth.port.CurrentUserProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityContextCurrentUserProvider implements CurrentUserProvider {
    public UUID idOrThrow() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserPrincipal(UUID userId))) {
            throw new IllegalStateException("No autenticado");
        }
        return userId;
    }
}
