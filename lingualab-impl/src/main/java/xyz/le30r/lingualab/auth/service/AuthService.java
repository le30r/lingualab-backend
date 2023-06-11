package xyz.le30r.lingualab.auth.service;

import xyz.le30r.lingualab.auth.entity.Auth;
import xyz.le30r.lingualab.dto.RegisterRequestDto;
import xyz.le30r.lingualab.dto.UserDto;

public interface AuthService {
    Auth register(RegisterRequestDto registerRequestDto);
    UserDto getUser(String username);
}