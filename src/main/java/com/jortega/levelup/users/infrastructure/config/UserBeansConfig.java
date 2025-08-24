package com.jortega.levelup.users.infrastructure.config;

import com.jortega.levelup.shared.infrastructure.security.JwtService;
import com.jortega.levelup.users.application.LoginUserService;
import com.jortega.levelup.users.application.RegisterUserService;
import com.jortega.levelup.users.domain.UserService;
import com.jortega.levelup.users.port.in.LoginUserUseCase;
import com.jortega.levelup.users.port.in.RegisterUserUseCase;
import com.jortega.levelup.users.port.out.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserBeansConfig {
    
    @Bean
    UserService userService(PasswordEncoder passwordEncoder) {
        return new UserService(passwordEncoder);
    }
    
    @Bean
    RegisterUserUseCase registerUserUseCase(UserRepository userRepository, UserService userService) {
        return new RegisterUserService(userRepository, userService);
    }
    
    @Bean
    LoginUserUseCase loginUserUseCase(UserRepository userRepository, UserService userService, JwtService jwtService) {
        return new LoginUserService(userRepository, userService, jwtService);
    }
}
