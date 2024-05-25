package org.elgordogato.supercalculator.repositories;

import org.elgordogato.supercalculator.entities.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    @Query("""
            SELECT c.result
            FROM Calculation c
            """)
    List<Double> findAllResults();
}