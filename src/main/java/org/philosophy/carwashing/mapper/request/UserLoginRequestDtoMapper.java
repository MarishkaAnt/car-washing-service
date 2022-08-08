package org.philosophy.carwashing.mapper.request;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.UserLoginRequestDto;
import org.philosophy.carwashing.enums.Roles;
import org.philosophy.carwashing.mapper.GenericRequestMapper;
import org.philosophy.carwashing.model.User;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserLoginRequestDtoMapper extends
        GenericRequestMapper<User, UserLoginRequestDto> {

    User toEntity(UserLoginRequestDto userLoginRequestDto);

    @AfterMapping
    default void setDefaultRoleAndIsActive(@MappingTarget User user) {
        user.setRole(Roles.ANONYMOUS);
        user.setIsActive(true);
    }

}
