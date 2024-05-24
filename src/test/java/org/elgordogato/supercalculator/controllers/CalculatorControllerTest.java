package org.elgordogato.supercalculator.controllers;

import org.elgordogato.supercalculator.entities.Operation;
import org.elgordogato.supercalculator.services.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {
    @Autowired
    private JacksonTester<List<Double>> jsonList;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CalculatorService calculatorService;
    private List<Double> numbers;


    @BeforeEach
    public void setUp() {
        numbers = List.of(2.0, 3.0, 5.0);
    }

    @Test
    public void testCalculateAdd() throws Exception {
        // given
        given(calculatorService.calculate(Operation.add, numbers))
                .willReturn(10.0);

        // when
        MockHttpServletResponse response = mvc.perform(post("/calculate/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonList.write(numbers).getJson()))
                .andReturn().getResponse();

        // then
        verify(calculatorService, times(1))
                .calculate(Operation.add, numbers);
        assertEquals(201, response.getStatus());
        assertEquals(String.valueOf(10.0), response.getContentAsString());
    }
}