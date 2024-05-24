package org.elgordogato.supercalculator.services;

import lombok.extern.slf4j.Slf4j;
import org.elgordogato.supercalculator.entities.Calculation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service("CalculatorServiceForFile")
public class CalculatorServiceForFile extends CalculatorService {
    private static final AtomicLong id = new AtomicLong(0);
    Path directoryPath = Paths.get("./file");
    @Value("${calculator.filePath}")
    private String fileName;

    public CalculatorServiceForFile() {
        createDirectoryIfNotExists();
    }

    private void createDirectoryIfNotExists() {
        try {
            Files.createDirectories(directoryPath);
            log.debug("Directory created: " + directoryPath.toAbsolutePath());
        } catch (IOException e) {
            log.debug("Error creating directory: " + e.getMessage());
        }
    }

    @Override
    public Calculation save(Calculation calculation) {
        calculation.setId(id.getAndIncrement());
        String filePath = directoryPath.resolve(fileName).toString();
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(calculation + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage(), e);
        }
        return calculation;
    }
}
