package org.philosophy.carwashing.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.WashTypeRequestDto;
import org.philosophy.carwashing.dto.responsedto.WashTypeResponseDto;
import org.philosophy.carwashing.service.WashTypeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/wash-types")
public class WashTypeController {

    private final WashTypeServiceImpl washTypeService;

    @GetMapping
    @Tag(name = "Выводит все имеющиеся в БД услуги")
    public ResponseEntity<Page<WashTypeResponseDto>> getAll(Pageable pageable){
        Page<WashTypeResponseDto> dtos = washTypeService.findAll(pageable);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WashTypeResponseDto> getById(@PathVariable Integer id){
        WashTypeResponseDto responseDto = washTypeService.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    //@Secured(ROLE_ADMIN)
    public ResponseEntity<WashTypeResponseDto> create(@RequestBody WashTypeRequestDto dto) {
        WashTypeResponseDto responseDto = washTypeService.create(dto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        washTypeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
