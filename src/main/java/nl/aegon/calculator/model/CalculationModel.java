package nl.aegon.calculator.model;

import com.sun.istack.NotNull;
import nl.aegon.calculator.enums.CalculationType;

import javax.persistence.*;

@Entity
public class CalculationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private CalculationType type;

    @NotNull
    private int a;

    @NotNull
    private int b;

    @NotNull
    private double result;
}
