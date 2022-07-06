package nl.aegon.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    /**
     * Add two numbers
     * @param a - First number
     * @param b - Second number
     * @return Added result
     */
    public double add(int a, int b) {
        return (double)a + b;
    }

    /**
     * Subtract two numbers
     * @param a - First number
     * @param b - Second number
     * @return Subtracted result
     */
    public double subtract(int a, int b) {
        return (double)a - b;
    }

    /**
     * Multiply two numbers
     * @param a - First number
     * @param b - Second number
     * @return Multiplied result
     */
    public double multiply(int a, int b) {
        return (double)a * b;
    }

    /**
     * Divide two numbers
     * @param a - First number
     * @param b - Second number
     * @return Divided result
     */
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Dividing by zero is not possible");
        }
        return (double)a / b;
    }
}
