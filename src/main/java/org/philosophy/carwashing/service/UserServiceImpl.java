package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.UserSignInRequestDto;
import org.philosophy.carwashing.dto.responsedto.UserViewResponseDto;
import org.philosophy.carwashing.mapper.request.UserSignInRequestDtoMapper;
import org.philosophy.carwashing.mapper.response.UserViewResponseMapper;
import org.philosophy.carwashing.model.Booking;
import org.philosophy.carwashing.model.User;
import org.philosophy.carwashing.repository.UserRepository;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements GenericService<Integer,
        UserViewResponseDto, UserSignInRequestDto> {

    private final UserRepository userRepository;
    private final UserSignInRequestDtoMapper requestDtoMapper;
    private final UserViewResponseMapper responseMapper;
    private final ParameterValidator<UserSignInRequestDto> validator;

    @Override
    public UserViewResponseDto create(UserSignInRequestDto dto) {
        validator.validateDtoNotNull(dto);


        return null;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//        List<Booking> bookings = user.getBookings();
//        if(bookings.size() != 0) {
            user.setIsActive(false);
//        } else {
//            userRepository.deleteById(id);
//        }
    }

    @Override
    public UserViewResponseDto findById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        return userRepository.findById(id)
                .map(responseMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<UserViewResponseDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(responseMapper::toDto);
    }
/*
ToDo
 */

    @Override
    public UserViewResponseDto update(Integer integer, UserSignInRequestDto dto) {
        return null;
    }
}
