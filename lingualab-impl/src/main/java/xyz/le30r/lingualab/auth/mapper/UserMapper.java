package xyz.le30r.lingualab.auth.mapper;

import org.mapstruct.Mapper;
import xyz.le30r.lingualab.auth.entity.Auth;
import xyz.le30r.lingualab.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapEntityToDto(Auth auth);
}
