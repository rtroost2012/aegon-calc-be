package nl.aegon.calculator.web.rest;

import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.model.CalculationModel;
import nl.aegon.calculator.service.CalculatorService;
import nl.aegon.calculator.transformer.CalculationTransformer;
import nl.aegon.calculator.web.dto.CalculationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final CalculationTransformer calculationTransformer;

    public CalculatorController(CalculatorService calculatorService, CalculationTransformer transformer) {
        this.calculatorService = calculatorService;
        this.calculationTransformer = transformer;
    }

    @PostMapping("/add")
    public CalculationDTO add(@RequestBody CalculationDTO input) {
        final CalculationModel calculation = calculationTransformer.toModel(input);
        calculation.setResult(calculatorService.add(input.getA(), input.getB()));
        calculation.setType(CalculationType.ADDITION);
        return calculationTransformer.toDTO(calculation);
    }
    @PostMapping("/subtract")
    public CalculationDTO subtract(@RequestBody CalculationDTO input) {
        final CalculationModel calculation = calculationTransformer.toModel(input);
        calculation.setResult(calculatorService.subtract(input.getA(), input.getB()));
        calculation.setType(CalculationType.SUBTRACTION);
        return calculationTransformer.toDTO(calculation);
    }
    @PostMapping("/multiply")
    public CalculationDTO multiply(@RequestBody CalculationDTO input) {
        final CalculationModel calculation = calculationTransformer.toModel(input);
        calculation.setResult(calculatorService.multiply(input.getA(), input.getB()));
        calculation.setType(CalculationType.MULTIPLICATION);
        return calculationTransformer.toDTO(calculation);
    }
    @PostMapping("/divide")
    public CalculationDTO divide(@RequestBody CalculationDTO input) {
        final CalculationModel calculation = calculationTransformer.toModel(input);
        calculation.setResult(calculatorService.divide(input.getA(), input.getB()));
        calculation.setType(CalculationType.DIVISION);
        return calculationTransformer.toDTO(calculation);
    }
}

