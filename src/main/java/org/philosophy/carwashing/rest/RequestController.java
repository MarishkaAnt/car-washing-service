package org.philosophy.carwashing.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.RequestRequestDto;
import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.service.RequestServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class RequestController {

    private final RequestServiceImpl requestService;

    @GetMapping
    @Tag(name = "Выводит все имеющиеся в БД боксы")
    public ResponseEntity<Page<RequestResponseDto>> getAll(Pageable pageable){
        Page<RequestResponseDto> dtos = requestService.findAll(pageable);
        return ResponseEntity.ok().body(dtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RequestResponseDto> getById(@PathVariable Integer id){
        RequestResponseDto responseDto = requestService.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    //@Secured(ROLE_ADMIN)
    public ResponseEntity<RequestResponseDto> create(@RequestBody RequestRequestDto request) {
        RequestResponseDto responseDto = requestService.create(request);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        requestService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
