package org.philosophy.carwashing.rest;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.responsedto.DiscountSettingsResponseDto;
import org.philosophy.carwashing.service.DiscountSettingsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/discounts")
public class DiscountSettingsController {

    private final DiscountSettingsServiceImpl discountSettingsService;

    @GetMapping
    public ResponseEntity<List<DiscountSettingsResponseDto>> getAll() {
        List<DiscountSettingsResponseDto> dtos = discountSettingsService.findAll();
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping("/set-max-value")
    public ResponseEntity<Void> setMaxValue(@RequestBody @Valid Integer newMaxValue) {
        Integer response = discountSettingsService.setMax(newMaxValue);
        if (response != 1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/set-min-value")
    public ResponseEntity<Void> setMinValue(@RequestBody @Valid Integer newMinValue) {
        Integer response = discountSettingsService.setMin(newMinValue);
        if (response != 1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
