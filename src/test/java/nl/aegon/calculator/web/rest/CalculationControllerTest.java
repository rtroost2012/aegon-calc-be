package nl.aegon.calculator.web.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.model.CalculationModel;
import nl.aegon.calculator.service.CalculatorPersistenceService;
import nl.aegon.calculator.service.CalculatorService;
import nl.aegon.calculator.transformer.CalculationTransformer;
import nl.aegon.calculator.web.dto.CalculationDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CalculationTransformer calculationTransformer;

    @MockBean
    private CalculatorService calculatorService;

    @MockBean
    private CalculatorPersistenceService calculatorPersistenceService;

    private CalculationModel sampleCalculatedModel = new CalculationModel(
            1L, CalculationType.ADDITION, 10, 20, 30.0);

    /**
     * Performs the basics of HTTP calling and asserting common properties for a given arithmetic endpoint
     * @param URL - endpoint under test
     * @param calculationModel - calculation model to use as input
     * @throws Exception
     */
    private void assertHttpCallArithmetic(String URL, CalculationModel calculationModel) throws Exception {
        mockMvc
            .perform(post(URL)
                    .content(objectMapper.writeValueAsString(calculationTransformer.toDTO(calculationModel)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(result -> {
                var responseDTO = objectMapper.readValue(
                        result.getResponse().getContentAsString(),
                        new TypeReference<CalculationDTO>(){}
                );
                var expectedDTO = calculationTransformer.toDTO(calculationModel);
                assertEquals(expectedDTO, responseDTO);
            });

        Mockito.verify(calculatorPersistenceService, times(1)).save(any());
    }

    @Test
    public void testCanFetchHistory() throws Exception {
        final List<CalculationModel> calculations = List.of(sampleCalculatedModel);
        when(calculatorPersistenceService.history()).thenReturn(calculations);
        mockMvc
            .perform(get("/history")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(result -> {
                var responseDTO = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<List<CalculationDTO>>(){}
                );
                var expectedDTO = calculations
                        .stream()
                        .map(x -> calculationTransformer.toDTO(x))
                        .collect(Collectors.toList());
                assertEquals(expectedDTO, responseDTO);
            });
    }

    @Test
    public void testCanAddNumbers() throws Exception {
        when(calculatorService.add(sampleCalculatedModel.getA(), sampleCalculatedModel.getB())).thenReturn(
            sampleCalculatedModel.getResult()
        );
        assertHttpCallArithmetic("/add", sampleCalculatedModel);
    }

    @Test
    public void testCanSubtractNumbers() throws Exception {
        var sampleModel = new CalculationModel(1L, CalculationType.SUBTRACTION, 10, 20, -10.0);
        when(calculatorService.subtract(sampleModel.getA(), sampleModel.getB())).thenReturn(sampleModel.getResult());
        assertHttpCallArithmetic("/subtract", sampleModel);
    }

    @Test
    public void testCanMultiplyNumbers() throws Exception {
        var sampleModel = new CalculationModel(1L, CalculationType.MULTIPLICATION, 10, 20, 200.0);
        when(calculatorService.multiply(sampleModel.getA(), sampleModel.getB())).thenReturn(sampleModel.getResult());
        assertHttpCallArithmetic("/multiply", sampleModel);
    }

    @Test
    public void testCanDivideNumbers() throws Exception {
        var sampleModel = new CalculationModel(1L, CalculationType.DIVISION, 20, 10, 2.0);
        when(calculatorService.divide(sampleModel.getA(), sampleModel.getB())).thenReturn(sampleModel.getResult());
        assertHttpCallArithmetic("/divide", sampleModel);
    }
}
