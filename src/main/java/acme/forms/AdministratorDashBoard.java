package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashBoard implements Serializable {

    // Serialisation identifier -----------------------------------------------

    protected static final long    serialVersionUID    = 1L;

    // Attributes -------------------------------------------------------------
    
    Double						  totalNumberOfComponents;
    Double                        averageRetailPriceOfComponentsByTechnologyCurrency;
    Double                        deviationRetailPriceOfComponentsByTechnologyCurrency;
    Double                        minimumRetailPriceOfComponentsByTechnologyCurrency;
    Double                        maximumRetailPriceOfComponentsByTechnologyCurrency;
    Double                        totalNumberOfTools;
    Double                        averageRetailPriceOfToolsByCurrency;
    Double                        deviationRetailPriceOfToolsByCurrency;
    Double                        minimumRetailPriceOfToolsByCurrency;
    Double                        maximumRetailPriceOfToolsByCurrency;
    Double                        totalNumberOfPatronagesByType;
    Double                        averagePatronagesBudgedByType;
    Double                        deviationPatronagesBudgedByType;
    Double                        minimumPatronagesBudgedByType;
    Double                        maximumPatronagesBudgedByType;

    // Derived attributes -----------------------------------------------------

    // Relationships ----------------------------------------------------------
}