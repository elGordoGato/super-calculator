package org.elgordogato.supercalculator.services;

import org.elgordogato.supercalculator.entities.Calculation;
import org.elgordogato.supercalculator.entities.Operation;
import org.elgordogato.supercalculator.repositories.CalculationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceForDbTest {
    @Mock
    private CalculationRepository repository;
    @InjectMocks
    private CalculatorServiceForDb calculatorService;

    @BeforeEach
    void setUp() {
        when(repository.save(any(Calculation.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
    }


    @Test
    void testAddOperation() {
        List<Double> numbers = Arrays.asList(4.0, 2.0, 3.0);
        Double result = calculatorService.calculate(Operation.add, numbers);

        assertEquals(9.0, result);
    }

    @Test
    void testMulOperation() {

        List<Double> numbers = Arrays.asList(2.0, 3.0);
        Double result = calculatorService.calculate(Operation.mul, numbers);

        assertEquals(6.0, result);
    }

    @Test
    void testMulAndAddOperation() {
        List<Double> numbers = Arrays.asList(2.0, 3.0, 4.0);
        Double result = calculatorService.calculate(Operation.mulAndAdd, numbers);

        assertEquals(10.0, result);
    }
}