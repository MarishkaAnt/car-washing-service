package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.repository.BoxRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxService {

    private final BoxRepository boxRepository;

    public List<Box> findAll(){
        return boxRepository.findAll();
    }
}
