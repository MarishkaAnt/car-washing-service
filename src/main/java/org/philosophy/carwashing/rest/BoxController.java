package org.philosophy.carwashing.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.common.BoxFilter;
import org.philosophy.carwashing.dto.requestdto.BoxRequestDto;
import org.philosophy.carwashing.dto.responsedto.BoxResponseDto;
import org.philosophy.carwashing.service.BoxServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/boxes")
public class BoxController {

    private final BoxServiceImpl boxService;

    @GetMapping
    public ResponseEntity<Page<BoxResponseDto>> getAllWithParameters(BoxFilter filter, Pageable pageable){
        Page<BoxResponseDto> dtos = boxService.findAll(filter.toSpecification(), pageable);
        return ResponseEntity.ok().body(dtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BoxResponseDto> getById(@PathVariable Integer id){
        BoxResponseDto responseDto = boxService.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    //@Secured(ROLE_ADMIN)
    public ResponseEntity<BoxResponseDto> create(@RequestBody BoxRequestDto request) {
        BoxResponseDto responseDto = boxService.create(request);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    //@Secured(ROLE_ADMIN)
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        boxService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    //@Secured(ROLE_ADMIN)
    public ResponseEntity<BoxResponseDto> update(@RequestBody BoxRequestDto dto, @PathVariable Integer id) {
        BoxResponseDto responseDto = boxService.update(id, dto);
        return ResponseEntity.ok().body(responseDto);
    }


}
