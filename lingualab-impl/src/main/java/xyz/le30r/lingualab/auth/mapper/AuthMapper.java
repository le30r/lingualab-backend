package xyz.le30r.lingualab.auth.mapper;

import org.mapstruct.Mapper;
import xyz.le30r.lingualab.auth.entity.Auth;
import xyz.le30r.lingualab.dto.AuthDto;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthDto mapEntityToDto(Auth auth);
}
