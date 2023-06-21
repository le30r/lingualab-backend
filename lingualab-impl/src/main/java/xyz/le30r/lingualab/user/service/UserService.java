package xyz.le30r.lingualab.user.service;

import xyz.le30r.lingualab.dto.UserDto;

public interface UserService {
    UserDto getUser(String username);

    UserDto getUser(Long id);
}
