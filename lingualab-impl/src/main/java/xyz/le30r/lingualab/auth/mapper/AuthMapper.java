package xyz.le30r.lingualab.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.le30r.lingualab.auth.entity.Auth;
import xyz.le30r.lingualab.auth.entity.Invite;
import xyz.le30r.lingualab.dto.AuthDto;
import xyz.le30r.lingualab.dto.InviteDto;
import static xyz.le30r.lingualab.generic.utils.DateTimeUtils.toOffsetDateTime;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthDto mapEntityToDto(Auth auth);

    @Mapping(target = "expiresAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Auth mapDtoToEntity(AuthDto authDto);

    @Mapping(target = "teacherId", source = "teacherId")
    @Mapping(target = "inviteLink", source = "link")
    @Mapping(target = "createdAt",
            expression = "java(xyz.le30r.lingualab.generic.utils.DateTimeUtils.toOffsetDateTime(invite.getCreatedAt()))")
    @Mapping(target = "expiresAt",
            expression = "java(xyz.le30r.lingualab.generic.utils.DateTimeUtils.toOffsetDateTime(invite.getExpiresAt()))")
    InviteDto mapEntityToDto(Invite invite);

}
