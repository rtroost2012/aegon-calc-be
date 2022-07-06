package nl.aegon.calculator.web.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.aegon.calculator.enums.CalculationType;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculationDTO {
    @NotNull
    private Integer a;

    @NotNull
    private Integer b;

    @Hidden
    private CalculationType type;

    @Hidden
    private Double result;
}
