package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.UserLoginResponseDto;
import org.philosophy.carwashing.model.User;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserLoginResponseDtoMapper extends
        GenericEntityMapper<User, UserLoginResponseDto> {

    User toEntity(UserLoginResponseDto userLoginResponseDto);

    UserLoginResponseDto toDto(User user);
}
