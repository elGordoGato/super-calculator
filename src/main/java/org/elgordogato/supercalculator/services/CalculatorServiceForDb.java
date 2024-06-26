package org.elgordogato.supercalculator.services;

import lombok.RequiredArgsConstructor;
import org.elgordogato.supercalculator.entities.Calculation;
import org.elgordogato.supercalculator.repositories.CalculationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CalculatorServiceForDb")
@Transactional
@RequiredArgsConstructor
public class CalculatorServiceForDb extends CalculatorService {
    private final CalculationRepository repository;

    @Override
    public Calculation save(Calculation calculation) {
        return repository.save(calculation);
    }

    @Override
    public List<Double> getResults() {
        return repository.findAllResults();
    }
}
