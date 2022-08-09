package org.philosophy.carwashing.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.dto.responsedto.BookingResponseDto;
import org.philosophy.carwashing.service.BookingServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bookings")
public class BookingController {

    private final BookingServiceImpl bookingServiceImpl;

    @GetMapping
    @Tag(name = "Выводит все имеющиеся в БД брони")
    public ResponseEntity<Page<BookingResponseDto>> getAll(Pageable pageable){
        Page<BookingResponseDto> dtos = bookingServiceImpl.findAll(pageable);
        return ResponseEntity.ok().body(dtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getById(@PathVariable Integer id){
        BookingResponseDto responseDto = bookingServiceImpl.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    //@Secured(ROLE_ADMIN)
    public ResponseEntity<BookingResponseDto> create(@RequestBody BookingRequestDto request) {
        BookingResponseDto responseDto = bookingServiceImpl.create(request);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        bookingServiceImpl.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
