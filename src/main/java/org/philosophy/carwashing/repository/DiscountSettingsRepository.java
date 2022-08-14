package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.model.DiscountSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DiscountSettingsRepository extends
        JpaRepository<DiscountSettings,Integer> {

    @Modifying
    @Transactional
    @Query("update DiscountSettings d set d.maxValue = :value where d.id = 1 ")
    Integer setMaxValue(@Param("value") Integer value);

    @Modifying
    @Transactional
    @Query("update DiscountSettings d set d.minValue = :value where d.id = 1 ")
    Integer setMinValue(@Param("value") Integer value);

    @Query(nativeQuery = true,
            value = "with constrains as (select ds.id as id, ds.max_value as maximum, ds.min_value as minimum " +
                    "                    from discount_settings as ds where ds.id = 1) " +
                    "select case when :discount <= cs.minimum then cs.minimum " +
                    "            when :discount >= cs.maximum then cs.maximum " +
                    "            else :discount end " +
                    "            as new_discount " +
                    "       from constrains as cs")
    Integer checkDiscount(@Param("discount") Integer discount);

}
