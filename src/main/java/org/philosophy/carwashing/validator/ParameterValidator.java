package org.philosophy.carwashing.validator;

import org.springframework.stereotype.Component;

/**
 * Валидатор DTO и id
 * @param <D> - принимает DTO для проверки на null
 * @Throws IllegalArgumentException
 */
@Component
public class ParameterValidator<D> {

    public void validateIdIsNullOrNegative(Integer id) {
        if (id == null || (id <= 0)) {
            throw new IllegalArgumentException("Id должно быть больше нуля и не null");
        }
    }

    public void validateDtoNotNull(D dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Передаваемая сущность == null");
        }
    }

}
