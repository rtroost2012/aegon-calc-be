package nl.aegon.calculator.service;

import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.model.CalculationModel;
import nl.aegon.calculator.repository.CalculationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CalculatorPersistenceServiceTest {
    @Mock
    private CalculationRepository calculationRepository;

    @InjectMocks
    private CalculatorPersistenceService calculatorPersistenceService;

    @Captor
    private ArgumentCaptor<CalculationModel> calculationArgumentCaptor;

    @Test
    void testCanPersistCalculation() {
        final long id = 1L;
        final int a = 10;
        final int b = 30;
        final CalculationType type = CalculationType.ADDITION;
        final double result = 40.0;

        calculatorPersistenceService.save(new CalculationModel(id, type, a, b, result));

        Mockito.verify(calculationRepository).save(calculationArgumentCaptor.capture());

        final CalculationModel receivedValue = calculationArgumentCaptor.getValue();
        assertEquals(receivedValue.getId(), id);
        assertEquals(receivedValue.getA(), a);
        assertEquals(receivedValue.getB(), b);
        assertEquals(receivedValue.getType(), type);
        assertEquals(receivedValue.getResult(), result);
    }

    @Test
    void testCanFindAllCalculations() {
        calculatorPersistenceService.history();
        Mockito.verify(calculationRepository, times(1)).findAll();
    }
}