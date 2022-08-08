package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.UserFindAllResponseDto;
import org.philosophy.carwashing.model.User;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserFindAllResponseMapper extends
        GenericEntityMapper<User, UserFindAllResponseDto> {

    @Mapping(target = "role.id", source = "roleId")
    User toEntity(UserFindAllResponseDto dto);

    @Mapping(target = "roleId", source = "role.id")
    UserFindAllResponseDto toDto(User user);
}
