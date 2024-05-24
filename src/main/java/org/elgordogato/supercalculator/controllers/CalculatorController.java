package org.elgordogato.supercalculator.controllers;

import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elgordogato.supercalculator.entities.Operation;
import org.elgordogato.supercalculator.services.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/calculate")
@RequiredArgsConstructor
public class CalculatorController {
    private final CalculatorService calculatorService;

    @PostMapping("/{operation}")
    @ResponseStatus(HttpStatus.CREATED)
    public Double calculate(@PathVariable Operation operation,
                            @RequestBody @NotEmpty List<Double> numbers) {
        log.info("Received request to perform operation: {} with numbers: {}", operation, numbers);
        return calculatorService.calculate(operation, numbers);
    }
}
