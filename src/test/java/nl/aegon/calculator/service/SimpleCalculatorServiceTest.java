package nl.aegon.calculator.service;

import nl.aegon.calculator.services.SimpleCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SimpleCalculatorServiceTest {

    private final SimpleCalculatorService calculatorService = new SimpleCalculatorService();
    private final int a = 10;
    private final int b = 20;

    @Test
    public void testAdditionOfNumbers() {
        final double overflow = (double)Integer.MAX_VALUE + b;
        assertEquals(calculatorService.add(a, b), 30);
        assertEquals(calculatorService.add(Integer.MAX_VALUE, b), overflow);
    }

    @Test
    public void testSubtractionOfNumbers() {
        final double overflow = a - (double)Integer.MAX_VALUE;
        assertEquals(calculatorService.subtract(a, b), -10);
        assertEquals(calculatorService.subtract(b, a), 10);
        assertEquals(calculatorService.subtract(a, Integer.MAX_VALUE), overflow);
    }

    @Test
    public void testMultiplicationOfNumbers() {
        final double overflow = Double.MAX_VALUE + 10;
        assertEquals(calculatorService.multiply(a, b), 200);
//        assertEquals(calculatorService.multiply(a, Double.MAX_VALUE), overflow);
    }

    @Test
    public void testDivisionOfNumbers() {
        assertEquals(calculatorService.divide(b, a), 2);
        assertEquals(calculatorService.divide(a, b), 0.5);
    }

    @Test
    public void testDivisionByZero() {
        assertEquals(calculatorService.divide(0, b), 0);
        assertThrows(ArithmeticException.class, () -> {
            calculatorService.divide(b, 0);
        });
    }
}
