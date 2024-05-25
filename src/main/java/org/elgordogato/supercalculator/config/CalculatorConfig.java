package org.elgordogato.supercalculator.config;

import org.elgordogato.supercalculator.services.CalculatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorConfig {

    @Value("${calculator.qualifier}")
    private String qualifier;

    @Bean
    public CalculatorService calculatorService(ApplicationContext context) {
        return context.getBean(qualifier, CalculatorService.class);
    }
}
