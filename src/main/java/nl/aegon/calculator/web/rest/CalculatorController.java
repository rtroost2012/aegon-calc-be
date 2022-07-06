package nl.aegon.calculator.web.rest;

import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.model.CalculationModel;
import nl.aegon.calculator.service.CalculatorService;
import nl.aegon.calculator.transformer.CalculationTransformer;
import nl.aegon.calculator.web.dto.CalculationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}

