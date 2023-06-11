package xyz.le30r.lingualab.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.le30r.lingualab.auth.entity.Auth;
import xyz.le30r.lingualab.auth.entity.Role;
import xyz.le30r.lingualab.auth.mapper.UserMapper;
import xyz.le30r.lingualab.auth.repository.AuthRepository;
import xyz.le30r.lingualab.auth.service.AuthService;
import xyz.le30r.lingualab.dto.RegisterRequestDto;
import xyz.le30r.lingualab.dto.UserDto;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

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
                .build();

        return authRepository.save(auth);
    }

    public UserDto getUser(String username)
    {
        return userMapper.mapEntityToDto(authRepository.findByLogin(username));
    }
}