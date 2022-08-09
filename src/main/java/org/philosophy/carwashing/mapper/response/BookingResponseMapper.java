package org.philosophy.carwashing.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.BookingResponseDto;
import org.philosophy.carwashing.mapper.GenericResponseMapper;
import org.philosophy.carwashing.model.Booking;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BookingResponseMapper extends
        GenericResponseMapper<Booking, BookingResponseDto> {

    @Mapping(target="requestId", source="request.id")
    @Mapping(target="boxId", source="box.id")
    BookingResponseDto toDto(Booking booking);
}
