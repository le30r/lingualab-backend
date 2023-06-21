package xyz.le30r.lingualab.auth.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import xyz.le30r.lingualab.api.AuthApi;
import xyz.le30r.lingualab.auth.entity.Auth;
import xyz.le30r.lingualab.auth.service.AuthService;
import xyz.le30r.lingualab.auth.service.InviteService;
import xyz.le30r.lingualab.dto.InviteDto;
import xyz.le30r.lingualab.dto.RegisterRequestDto;

@RestController
@RequiredArgsConstructor
public class AuthApiController implements AuthApi {
    private final AuthService authService;
    private final InviteService inviteService;

    @Override
    public ResponseEntity<InviteDto> createInvite(InviteDto inviteDto) {
        return ResponseEntity.ok(inviteService.createInvite(inviteDto));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Void> register(String invite, RegisterRequestDto registerRequestDto) {

        if (registerRequestDto == null) {
            throw new HttpMessageNotReadableException("Empty body");
        } else {
            Auth auth = authService.register(invite, registerRequestDto);
            return ResponseEntity.created(new URI("/api/v1/user/" + auth.getId())).build();
        }
    }
}
