package nl.aegon.calculator.service;

import nl.aegon.calculator.model.CalculationModel;
import nl.aegon.calculator.repository.CalculationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorPersistenceService {

    private final CalculationRepository calculationRepository;

    CalculatorPersistenceService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    /**
     * Returns calculation history
     * @return List of all the previous calculations
     */
    public List<CalculationModel> history() {
        return this.calculationRepository.findAll();
    }

    /**
     * Persists a new calculation
     * @param calculation - The calculation to persist
     * @return Whether creation has succeeded
     */
    public CalculationModel save(CalculationModel calculation) {
        return calculationRepository.save(calculation);
    }
}
