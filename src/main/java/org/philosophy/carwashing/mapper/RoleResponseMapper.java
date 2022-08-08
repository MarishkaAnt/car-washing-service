package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.RoleRequestDto;
import org.philosophy.carwashing.dto.responsedto.RoleResponseDto;
import org.philosophy.carwashing.model.Role;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface RoleResponseMapper extends
        GenericEntityMapper<Role, RoleResponseDto>  {

    Role toEntity(RoleResponseDto dto);

    RoleResponseDto toDto(Role role);
}
