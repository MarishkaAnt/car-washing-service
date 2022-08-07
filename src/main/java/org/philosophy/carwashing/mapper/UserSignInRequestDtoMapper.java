package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.UserSignInRequestDto;
import org.philosophy.carwashing.model.User;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserSignInRequestDtoMapper extends
        GenericEntityMapper<User, UserSignInRequestDto> {

    User toEntity(UserSignInRequestDto userLoginRequestDto);

    UserSignInRequestDto toDto(User user);
}
