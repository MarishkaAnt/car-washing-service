package org.philosophy.carwashing.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.UserSignInRequestDto;
import org.philosophy.carwashing.dto.responsedto.UserViewResponseDto;
import org.philosophy.carwashing.service.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserServiceImpl bookingServiceImpl;

    @GetMapping
    @Tag(name = "Выводит все имеющиеся в БД брони")
    public ResponseEntity<Page<UserViewResponseDto>> getAll(Pageable pageable){
        Page<UserViewResponseDto> dtos = bookingServiceImpl.findAll(pageable);
        return ResponseEntity.ok().body(dtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserViewResponseDto> getById(@PathVariable Integer id){
        UserViewResponseDto responseDto = bookingServiceImpl.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    //@Secured(ROLE_ADMIN)
    public ResponseEntity<UserViewResponseDto> create(@RequestBody UserSignInRequestDto dto) {
        UserViewResponseDto responseDto = bookingServiceImpl.create(dto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        bookingServiceImpl.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
