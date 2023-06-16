package xyz.le30r.lingualab.auth.controller;

import java.net.URI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import xyz.le30r.lingualab.api.AuthApi;
import xyz.le30r.lingualab.auth.entity.Auth;
import xyz.le30r.lingualab.auth.service.AuthService;
import xyz.le30r.lingualab.dto.RegisterRequestDto;
import xyz.le30r.lingualab.dto.UserDto;

@Controller
@RequiredArgsConstructor
public class AuthApiController implements AuthApi {
    private final AuthService authService;

    @SneakyThrows
    @NotNull
    public ResponseEntity<Void> register(@Nullable RegisterRequestDto registerRequestDto) {

        if (registerRequestDto == null) {
            throw new HttpMessageNotReadableException("Empty body");
        } else {
            Auth auth = authService.register(registerRequestDto);
            return ResponseEntity.created(new URI("/api/v1/user/" + auth.getId())).build();
        }
    }
}
