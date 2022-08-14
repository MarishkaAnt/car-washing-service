package org.philosophy.carwashing.common;

import lombok.experimental.UtilityClass;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.BoxType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;

@UtilityClass
public class BoxSpecification {

    public static Specification<Box> hasId(Integer id){
        return id != null && id > 0 ?
                (root, query, cb) -> cb.equal(root.<Integer>get("id"), id) : null;
    }

    public static Specification<Box> hasBoxTypeId(Integer boxTypeId){
        return boxTypeId == null?
                null : (root, query, cb) -> cb.equal(root.<Integer>get("boxType").get("id"), boxTypeId);
    }

    public static Specification<Box> hasOpenTime(LocalTime openTime){
        return openTime == null ?
            null : (root, query, cb) -> cb.equal(root.<LocalTime>get("openTime"), openTime);
    }

    public static Specification<Box> hasCloseTime(LocalTime closeTime){
        return closeTime == null ?
                null : (root, query, cb) -> cb.equal(root.<LocalTime>get("closeTime"), closeTime);
    }
}
