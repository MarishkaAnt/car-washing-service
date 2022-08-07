package org.philosophy.carwashing.rest;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.service.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boxes")
public class BoxController {

    private final BoxService boxService;

    @GetMapping("")
    public ResponseEntity<List<Box>> getAll(){
        List<Box> boxes = boxService.findAll();
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }
}
