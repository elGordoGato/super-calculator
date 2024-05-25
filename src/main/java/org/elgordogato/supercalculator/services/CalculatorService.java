package org.elgordogato.supercalculator.services;

import org.elgordogato.supercalculator.entities.Calculation;
import org.elgordogato.supercalculator.entities.Operation;
import org.elgordogato.supercalculator.exceptions.BadRequestException;

import java.util.List;

public abstract class CalculatorService {
    public Double calculate(Operation operation, List<Double> numbers) {
        if (numbers.size() < 2) {
            throw new BadRequestException(numbers.toString(), operation.toString());
        }
        Double result = switch (operation) {
            case add -> numbers.stream().reduce(0.0, Double::sum);
            case mul -> numbers.stream().reduce(1.0, (a, b) -> a * b);
            case sub -> numbers.get(0) - numbers.stream().skip(1).reduce(0.0, Double::sum);
            case div -> numbers.get(0) / numbers.stream().skip(1).reduce(1.0, (a, b) -> a * b);
            case mulAndAdd -> mulAndAdd(numbers);
        };
        Calculation calculation = Calculation.builder()
                .numbers(numbers)
                .operation(operation)
                .result(result)
                .build();
        return save(calculation).getResult();
    }

    public abstract List<Double> getResults();

    protected abstract Calculation save(Calculation calculation);

    private Double mulAndAdd(List<Double> numbers) {
        if (numbers.size() < 3) {
            throw new BadRequestException(numbers.toString(), Operation.mulAndAdd.toString());
        }
        Double result = numbers.get(0) * numbers.get(1);
        for (int i = 2; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }
}
