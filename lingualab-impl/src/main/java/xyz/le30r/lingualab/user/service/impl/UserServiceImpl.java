package xyz.le30r.lingualab.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.le30r.lingualab.dto.UserDto;
import xyz.le30r.lingualab.generic.exception.ObjectNotFoundException;
import xyz.le30r.lingualab.user.mapper.UserMapper;
import xyz.le30r.lingualab.user.repository.UserRepository;
import xyz.le30r.lingualab.user.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    @Override
    public UserDto getUser(String username) {
        var user = userRepository.getUserByAuth_Login(username);
        return userMapper.mapEntityToDto(user);
    }

    @Override
    public UserDto getUser(Long id) {
        return userMapper.mapEntityToDto(userRepository.findById(id).orElseThrow(ObjectNotFoundException::new));
    }
}
