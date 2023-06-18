package xyz.le30r.lingualab.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.le30r.lingualab.auth.entity.Auth;
import xyz.le30r.lingualab.auth.entity.Role;
import xyz.le30r.lingualab.auth.event.UserCreatedEvent;
import xyz.le30r.lingualab.auth.mapper.AuthMapper;
import xyz.le30r.lingualab.auth.repository.AuthRepository;
import xyz.le30r.lingualab.auth.service.AuthService;
import xyz.le30r.lingualab.dto.AuthDto;
import xyz.le30r.lingualab.dto.RegisterRequestDto;

import java.time.Instant;

@Service
@RequiredArgsConstructor
class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public Auth register(RegisterRequestDto registerRequestDto) {
        final var hashedPassword = passwordEncoder.encode(
                registerRequestDto.getPassword()
        );

        val authContext = SecurityContextHolder.getContext().getAuthentication();
        val isAdmin = authContext.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ADMIN")
        );

        if (Role.valueOf(registerRequestDto.getRole()) == Role.ADMIN
                && !isAdmin) {
            throw new IllegalStateException();
        }

        val auth = Auth.builder()
                .id(null)
                .login(registerRequestDto.getLogin())
                .password(hashedPassword)
                .role(Role.valueOf(registerRequestDto.getRole()))
                .createdAt(Instant.now())
                .expiresAt(registerRequestDto.getExpiresAt().toInstant())
                .build();
        authRepository.save(auth);

        return auth;
    }

    public AuthDto getAuth(String username)
    {
        return authMapper.mapEntityToDto(authRepository.findByLogin(username));
    }
}