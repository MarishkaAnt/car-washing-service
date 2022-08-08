package org.philosophy.carwashing.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.philosophy.carwashing.dto.requestdto.BoxRequestDto;
import org.philosophy.carwashing.dto.responsedto.BoxResponseDto;
import org.philosophy.carwashing.service.BoxServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boxes")
public class BoxController {

    private final BoxServiceImpl boxServiceImpl;

    @GetMapping
    @Tag(name = "Выводит все имеющиеся в БД боксы")
    public ResponseEntity<Page<BoxResponseDto>> getAllPageable(Pageable pageable){
        Page<BoxResponseDto> dtos = boxServiceImpl.findAll(pageable);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoxResponseDto> getById(@PathVariable Integer id){
        BoxResponseDto responseDto = boxServiceImpl.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    public ResponseEntity<BoxResponseDto> create(@RequestBody BoxRequestDto request) {
        log.debug("post controller {}", request);
        BoxResponseDto responseDto = boxServiceImpl.create(request);
        return ResponseEntity.ok().body(responseDto);
    }

}
