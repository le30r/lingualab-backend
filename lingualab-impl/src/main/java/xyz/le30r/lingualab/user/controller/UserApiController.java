package xyz.le30r.lingualab.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import xyz.le30r.lingualab.api.UserApi;
import xyz.le30r.lingualab.auth.entity.Role;
import xyz.le30r.lingualab.dto.UserDto;
import xyz.le30r.lingualab.user.service.UserService;

import java.time.OffsetDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class UserApiController implements UserApi {

    private final UserService userService;

    @PreAuthorize("#username == authentication.principal.username || hasRole('ADMIN')")
    @Override
    public ResponseEntity<UserDto> getUserByUsername(String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        var user = userService.getUser(id);
        if (Objects.equals(user.getRole(), Role.TEACHER.name())
                && SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .noneMatch(grantedAuthority -> Objects.equals(grantedAuthority.getAuthority(),
                        Role.ADMIN.name()) ||
                        Objects.equals(grantedAuthority.getAuthority(),
                                Role.TEACHER.name()))) {
            throw new AccessDeniedException("You do not have permissions");
        }
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> updateUser(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createUser(UserDto userDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id, OffsetDateTime expiresAt) {
        return null;
    }



}
