package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.UserLoginRequestDto;
import org.philosophy.carwashing.model.User;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserLoginRequestDtoMapper extends
        GenericEntityMapper<User, UserLoginRequestDto> {

    User toEntity(UserLoginRequestDto userLoginRequestDto);

    UserLoginRequestDto toDto(User user);
}
