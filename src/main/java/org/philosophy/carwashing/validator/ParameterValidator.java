package org.philosophy.carwashing.validator;

import org.springframework.stereotype.Component;

@Component
public class ParameterValidator <D>{

    public void validateIdIsNullOrNegative(Integer id){
        if (id == null || (id <= 0)) {
            throw new IllegalArgumentException("Id должно быть больше нуля и не null");
        }
    }

    public void validateDtoNotNull(D entity){
        if(entity == null) {
            throw new IllegalArgumentException("Передаваемая сущность == null");
        }
    }

}
