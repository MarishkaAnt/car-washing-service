package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.BookingAfterPaymentResponseDto;
import org.philosophy.carwashing.model.Booking;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BookingResponseMapper extends
        GenericEntityMapper<Booking, BookingAfterPaymentResponseDto>  {

    @Mapping(target="request.id", source="requestId")
    Booking toEntity(BookingAfterPaymentResponseDto dto);

    @Mapping(target="requestId", source="request.id")
    BookingAfterPaymentResponseDto toDto(Booking booking);
}
