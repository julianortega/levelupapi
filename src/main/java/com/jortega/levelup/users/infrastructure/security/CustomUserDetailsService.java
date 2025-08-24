package com.jortega.levelup.users.infrastructure.security;

import com.jortega.levelup.users.domain.User;
import com.jortega.levelup.users.port.out.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        try {
            UUID userUuid = UUID.fromString(userId);
            User user = userRepository.findById(userUuid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            
            return new org.springframework.security.core.userdetails.User(
                user.getId().toString(),
                user.getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
            );
        } catch (IllegalArgumentException e) {
            throw new UsernameNotFoundException("Invalid user ID format");
        }
    }
}
