package org.philosophy.carwashing.common;

import lombok.experimental.UtilityClass;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.BoxType;
import org.philosophy.carwashing.model.BoxType_;
import org.philosophy.carwashing.model.Box_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalTime;

@UtilityClass
public class BoxSpecification {

    public static Specification<Box> hasId(Integer id) {
        return id != null && id > 0 ?
                (root, query, cb) -> cb.equal(root.get(Box_.id), id) : null;
    }

    public static Specification<Box> hasBoxTypeId(Integer boxTypeId) {
        return boxTypeId == null ?
                null : (root, query, cb) -> cb.equal(root.get(Box_.boxType).get(BoxType_.id), boxTypeId);
    }

    public static Specification<Box> hasOpenTime(LocalTime openTime) {
        return openTime == null ?
                null : (root, query, cb) -> cb.equal(root.get(Box_.openTime), openTime);
    }

    public static Specification<Box> hasCloseTime(LocalTime closeTime) {
        return closeTime == null ?
                null : (root, query, cb) -> cb.equal(root.get(Box_.closeTime), closeTime);
    }
}
