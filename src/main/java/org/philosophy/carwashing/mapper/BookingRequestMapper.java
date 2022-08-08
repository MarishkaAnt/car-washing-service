package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.model.Booking;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BookingRequestMapper extends
        GenericEntityMapper<Booking, BookingRequestDto>  {

    @Mapping(target="request.id", source="requestId")
    Booking toEntity(BookingRequestDto dto);

    @Mapping(target="requestId", source="request.id")
    BookingRequestDto toDto(Booking booking);
}
