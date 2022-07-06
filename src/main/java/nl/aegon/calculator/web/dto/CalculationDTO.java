package nl.aegon.calculator.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.aegon.calculator.enums.CalculationType;

@AllArgsConstructor
@Data
public class CalculationDTO {
    private final CalculationType type;
    private final int a;
    private final int b;
    private final double result;
}
