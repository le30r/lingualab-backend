package xyz.le30r.lingualab.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.le30r.lingualab.dto.UserDto;
import xyz.le30r.lingualab.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", source = "auth.role")
    UserDto mapEntityToDto(User user);

    @Mapping(target = "auth", ignore = true)
    User mapDtoToEntity(UserDto userDto);
}
