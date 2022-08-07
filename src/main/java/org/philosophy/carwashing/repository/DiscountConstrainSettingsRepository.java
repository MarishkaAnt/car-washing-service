package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.model.DiscountSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountConstrainSettingsRepository extends
        JpaRepository<DiscountSettings,Integer> {
}
