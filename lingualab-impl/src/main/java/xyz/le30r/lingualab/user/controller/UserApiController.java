package xyz.le30r.lingualab.user.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import xyz.le30r.lingualab.api.UserApi;
import xyz.le30r.lingualab.dto.UserDto;
import xyz.le30r.lingualab.user.service.UserService;

@RequiredArgsConstructor
public class UserApiController implements UserApi {

    private final UserService userService;

    @PreAuthorize("#username == authentication.principal.username || hasRole('ADMIN')")
    @NotNull
    @Override
    public ResponseEntity<UserDto> getUserByUsername(@NotNull String username) {
        return ResponseEntity.ok(this.userService.getUser(username));
    }

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }

}
