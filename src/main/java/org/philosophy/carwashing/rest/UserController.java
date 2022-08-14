package org.philosophy.carwashing.rest;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.UserSignInRequestDto;
import org.philosophy.carwashing.dto.responsedto.UserViewResponseDto;
import org.philosophy.carwashing.enums.Roles;
import org.philosophy.carwashing.service.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Page<UserViewResponseDto>> getAll(Pageable pageable){
        Page<UserViewResponseDto> dtos = userService.findAll(pageable);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserViewResponseDto> getById(@PathVariable Integer id){
        UserViewResponseDto responseDto = userService.findById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    public ResponseEntity<UserViewResponseDto> create(@RequestBody UserSignInRequestDto dto) {
        UserViewResponseDto responseDto = userService.create(dto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserViewResponseDto> update(@RequestBody UserSignInRequestDto dto,
                                                      @PathVariable Integer id) {
        UserViewResponseDto responseDto = userService.update(id, dto);
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/{id}/change-role")
    public ResponseEntity<UserViewResponseDto> changeRole(@RequestBody String role,
                                                      @PathVariable Integer id) {
        UserViewResponseDto responseDto = userService.changeRole(id, Roles.valueOf(role));
        return ResponseEntity.ok().body(responseDto);
    }
}
