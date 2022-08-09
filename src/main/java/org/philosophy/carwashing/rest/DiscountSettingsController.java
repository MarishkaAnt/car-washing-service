package org.philosophy.carwashing.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.dto.requestdto.DiscountSettingsRequestDto;
import org.philosophy.carwashing.dto.responsedto.BookingResponseDto;
import org.philosophy.carwashing.dto.responsedto.DiscountSettingsResponseDto;
import org.philosophy.carwashing.service.DiscountSettingsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/discounts")
public class DiscountSettingsController {

    private final DiscountSettingsServiceImpl discountSettingsService;

    @GetMapping
    @Tag(name = "Выводит все имеющиеся в БД брони")
    public ResponseEntity<Page<DiscountSettingsResponseDto>> getAll(Pageable pageable){
        Page<DiscountSettingsResponseDto> dtos = discountSettingsService.findAll(pageable);
        return ResponseEntity.ok().body(dtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DiscountSettingsResponseDto> getById(@PathVariable Integer id){
        DiscountSettingsResponseDto responseDto = discountSettingsService.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    //@Secured(ROLE_ADMIN)
    public ResponseEntity<DiscountSettingsResponseDto> create(@RequestBody DiscountSettingsRequestDto dto) {
        DiscountSettingsResponseDto responseDto = discountSettingsService.create(dto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        discountSettingsService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
