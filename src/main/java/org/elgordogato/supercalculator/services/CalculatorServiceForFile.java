package org.elgordogato.supercalculator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.elgordogato.supercalculator.entities.Calculation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service("CalculatorServiceForFile")
public class CalculatorServiceForFile extends CalculatorService {
    private static final AtomicLong id = new AtomicLong(0);
    private final Path directoryPath = Paths.get("./file");
    private final String filePath;
    private final ObjectMapper objectMapper;
    @Value("${calculator.filePath}")
    private String fileName;

    public CalculatorServiceForFile() {
        createDirectoryIfNotExists();
        objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        filePath = directoryPath.resolve(fileName).toString();
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
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            String calculationJson = objectMapper.writeValueAsString(calculation);
            fileWriter.write(calculationJson + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage(), e);
        }
        return calculation;
    }

    @Override
    public List<Double> getResults() {
        List<Double> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                results.add(
                        objectMapper.readValue(line, Calculation.class).getResult());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reaDING FROM file: " + e.getMessage(), e);
        }
        return results;
    }
}
