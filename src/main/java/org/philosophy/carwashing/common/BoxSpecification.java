package org.philosophy.carwashing.common;

import lombok.experimental.UtilityClass;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.BoxType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;

@UtilityClass
public class BoxSpecification {

    public static Specification<Box> hasId(Integer id){
        if (id == null || id <= 0) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Box> hasBoxType(BoxType boxType){
        if (boxType == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("boxType"), boxType);

    }

    public static Specification<Box> hasOpenTime(LocalTime openTime){
        if (openTime == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("openTime"), openTime);

    }
    public static Specification<Box> hasCloseTime(LocalTime closeTime){
        if (closeTime == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("openTime"), closeTime);

    }
}
