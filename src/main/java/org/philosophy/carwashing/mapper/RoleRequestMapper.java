package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.RoleRequestDto;
import org.philosophy.carwashing.model.Role;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface RoleRequestMapper extends
        GenericEntityMapper<Role, RoleRequestDto>  {

    Role toEntity(RoleRequestDto dto);

    RoleRequestDto toDto(Role role);
}
