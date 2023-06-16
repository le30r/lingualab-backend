package xyz.le30r.lingualab.user.service;

import org.jetbrains.annotations.NotNull;
import xyz.le30r.lingualab.dto.UserDto;

public interface UserService {
    UserDto getUser(String username);

    UserDto getUser(Long id);
}
