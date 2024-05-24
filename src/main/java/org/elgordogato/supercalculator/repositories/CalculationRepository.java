package org.elgordogato.supercalculator.repositories;

import org.elgordogato.supercalculator.entities.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}