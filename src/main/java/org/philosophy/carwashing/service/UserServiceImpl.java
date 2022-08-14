package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.UserSignInRequestDto;
import org.philosophy.carwashing.dto.responsedto.UserViewResponseDto;
import org.philosophy.carwashing.enums.Roles;
import org.philosophy.carwashing.mapper.request.UserSignInRequestDtoMapper;
import org.philosophy.carwashing.mapper.response.UserViewResponseMapper;
import org.philosophy.carwashing.model.User;
import org.philosophy.carwashing.repository.UserRepository;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements GenericService<Integer,
        UserViewResponseDto, UserSignInRequestDto> {

    private final UserRepository userRepository;
    private final UserSignInRequestDtoMapper requestDtoMapper;
    private final UserViewResponseMapper responseMapper;
    private final ParameterValidator<UserSignInRequestDto> validator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserViewResponseDto create(UserSignInRequestDto dto) {
        validator.validateDtoNotNull(dto);
        User user = requestDtoMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setIsActive(true);
        user.setRole(Roles.USER);
        user.setHasDiscount(false);
        user.setDiscountAmount(0);
        User saved = userRepository.save(user);
        return responseMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            user.setIsActive(false);
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

    @Override
    public UserViewResponseDto update(Integer id, UserSignInRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (dto.getFirstName() != null){
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null){
            user.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null){
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null){
            user.setEmail(passwordEncoder.encode(dto.getPassword()));
        }
        User saved = userRepository.save(user);
        return responseMapper.toDto(saved);
    }

    @Secured("ROLE_ADMIN")
    public UserViewResponseDto changeRole(Integer id, Roles role){
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        user.setRole(role);
        User saved = userRepository.save(user);
        return responseMapper.toDto(saved);
    }
}
