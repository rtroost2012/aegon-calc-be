package nl.aegon.calculator.web.rest;

import nl.aegon.calculator.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @GetMapping("/add")
    public ResponseEntity<Double> add() {
        return ResponseEntity.ok(calculatorService.add(10, 30));
    }
}

