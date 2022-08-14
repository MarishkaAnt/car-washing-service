package org.philosophy.carwashing.rest;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.dto.responsedto.BookingResponseDto;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.service.BookingServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bookings")
public class BookingController {

    private final BookingServiceImpl bookingServiceImpl;

    @GetMapping
    public ResponseEntity<Page<BookingResponseDto>> getAll(Pageable pageable) {
        Page<BookingResponseDto> dtos = bookingServiceImpl.findAll(pageable);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getById(@PathVariable Integer id) {
        BookingResponseDto responseDto = bookingServiceImpl.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/accounting")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<BigDecimal> getAccounting() {
        BigDecimal totalMoneyAmount = bookingServiceImpl.getAccounting();
        return ResponseEntity.ok().body(totalMoneyAmount);
    }

    @PostMapping
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<BookingResponseDto> create(@RequestBody BookingRequestDto request) {
        BookingResponseDto responseDto = bookingServiceImpl.create(request);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        bookingServiceImpl.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<BookingResponseDto> update(@RequestBody BookingRequestDto dto, @PathVariable Integer id) {
        BookingResponseDto responseDto = bookingServiceImpl.update(id, dto);
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/{id}/change-status")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<BookingResponseDto> changeStatus(@PathVariable Integer id, @RequestParam String status) {
        BookingResponseDto responseDto = bookingServiceImpl.changeStatus(id, BookingStatuses.valueOf(status));
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/{id}/pay")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<BookingResponseDto> pay(@PathVariable Integer id) {
        BookingResponseDto responseDto = bookingServiceImpl.pay(id);
        return ResponseEntity.ok().body(responseDto);
    }


}
