package org.philosophy.carwashing.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.mapper.GenericRequestMapper;
import org.philosophy.carwashing.model.Booking;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BookingRequestMapper extends
        GenericRequestMapper<Booking, BookingRequestDto> {

    @Mapping(target = "washType.id", source = "washTypeId")
    @Mapping(target="box.id", source="boxId")
    @Mapping(target="user.id", source="userId")
    Booking toEntity(BookingRequestDto dto);

}
