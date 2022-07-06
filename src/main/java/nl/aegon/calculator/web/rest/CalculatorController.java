package nl.aegon.calculator.web.rest;

import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.model.CalculationModel;
import nl.aegon.calculator.service.CalculatorPersistenceService;
import nl.aegon.calculator.service.CalculatorService;
import nl.aegon.calculator.transformer.CalculationTransformer;
import nl.aegon.calculator.web.dto.CalculationDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final CalculatorPersistenceService calculatorPersistenceService;
    private final CalculationTransformer calculationTransformer;

    public CalculatorController(CalculatorService calculatorService, CalculatorPersistenceService calculatorPersistenceService, CalculationTransformer transformer) {
        this.calculatorService = calculatorService;
        this.calculatorPersistenceService = calculatorPersistenceService;
        this.calculationTransformer = transformer;
    }

    @GetMapping("/history")
    public List<CalculationDTO> history() {
        return calculatorPersistenceService
                .history()
                .stream()
                .map(calculationTransformer::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public CalculationDTO add(@Valid @RequestBody CalculationDTO input) {
        final CalculationModel calculation = calculationTransformer.toModel(input);
        calculation.setResult(calculatorService.add(input.getA(), input.getB()));
        calculation.setType(CalculationType.ADDITION);
        calculatorPersistenceService.save(calculation);
        return calculationTransformer.toDTO(calculation);
    }

    @PostMapping("/subtract")
    public CalculationDTO subtract(@Valid @RequestBody CalculationDTO input) {
        final CalculationModel calculation = calculationTransformer.toModel(input);
        calculation.setResult(calculatorService.subtract(input.getA(), input.getB()));
        calculation.setType(CalculationType.SUBTRACTION);
        calculatorPersistenceService.save(calculation);
        return calculationTransformer.toDTO(calculation);
    }

    @PostMapping("/multiply")
    public CalculationDTO multiply(@Valid @RequestBody CalculationDTO input) {
        final CalculationModel calculation = calculationTransformer.toModel(input);
        calculation.setResult(calculatorService.multiply(input.getA(), input.getB()));
        calculation.setType(CalculationType.MULTIPLICATION);
        calculatorPersistenceService.save(calculation);
        return calculationTransformer.toDTO(calculation);
    }

    @PostMapping("/divide")
    public CalculationDTO divide(@Valid @RequestBody CalculationDTO input) {
        final CalculationModel calculation = calculationTransformer.toModel(input);
        calculation.setResult(calculatorService.divide(input.getA(), input.getB()));
        calculation.setType(CalculationType.DIVISION);
        calculatorPersistenceService.save(calculation);
        return calculationTransformer.toDTO(calculation);
    }
}

