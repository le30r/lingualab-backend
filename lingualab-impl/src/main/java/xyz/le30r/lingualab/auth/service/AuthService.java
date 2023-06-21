package xyz.le30r.lingualab.auth.service;

import xyz.le30r.lingualab.auth.entity.Auth;
import xyz.le30r.lingualab.dto.AuthDto;
import xyz.le30r.lingualab.dto.RegisterRequestDto;

public interface AuthService {
    Auth register(String inviteLink, RegisterRequestDto registerRequestDto);
    AuthDto getAuth(String username);
}