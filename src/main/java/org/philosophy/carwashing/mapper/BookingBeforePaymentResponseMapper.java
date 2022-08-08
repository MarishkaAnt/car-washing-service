package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.BookingBeforePaymentResponseDto;
import org.philosophy.carwashing.model.Booking;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BookingBeforePaymentResponseMapper extends
        GenericEntityMapper<Booking, BookingBeforePaymentResponseDto>  {

    @Mapping(target="request.id", source="requestId")
    Booking toEntity(BookingBeforePaymentResponseDto dto);

    @Mapping(target="requestId", source="request.id")
    BookingBeforePaymentResponseDto toDto(Booking booking);
}
