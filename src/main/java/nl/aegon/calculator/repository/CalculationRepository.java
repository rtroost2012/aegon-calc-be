package nl.aegon.calculator.repository;

import nl.aegon.calculator.model.CalculationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<CalculationModel, Long> {
}
