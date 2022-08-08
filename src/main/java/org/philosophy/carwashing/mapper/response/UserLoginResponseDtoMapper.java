package org.philosophy.carwashing.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.UserLoginResponseDto;
import org.philosophy.carwashing.mapper.GenericResponseMapper;
import org.philosophy.carwashing.model.User;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserLoginResponseDtoMapper extends
        GenericResponseMapper<User, UserLoginResponseDto> {

    UserLoginResponseDto toDto(User user);

}
