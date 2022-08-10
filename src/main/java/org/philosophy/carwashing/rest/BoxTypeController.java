package org.philosophy.carwashing.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BoxTypeRequestDto;
import org.philosophy.carwashing.dto.responsedto.BoxTypeResponseDto;
import org.philosophy.carwashing.service.BoxTypeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/box-types")
public class BoxTypeController {

    private final BoxTypeServiceImpl boxTypeService;

    @GetMapping
    public ResponseEntity<Page<BoxTypeResponseDto>> getAllWithParameters(Pageable pageable){
        Page<BoxTypeResponseDto> dtos = boxTypeService.findAll(pageable);
        return ResponseEntity.ok().body(dtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BoxTypeResponseDto> getById(@PathVariable Integer id){
        BoxTypeResponseDto responseDto = boxTypeService.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    //@Secured(ROLE_ADMIN)
    public ResponseEntity<BoxTypeResponseDto> create(@RequestBody BoxTypeRequestDto dto) {
        BoxTypeResponseDto responseDto = boxTypeService.create(dto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        boxTypeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
