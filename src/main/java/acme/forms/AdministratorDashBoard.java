package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronage.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashBoard implements Serializable {

    // Serialisation identifier -----------------------------------------------

    protected static final long    serialVersionUID    = 1L;

    // Attributes -------------------------------------------------------------
    
    Long						         totalNumberOfComponents;
    Map<Pair<String, String>, Double>    averageRetailPriceOfComponentsByTechnologyCurrency;
    Map<Pair<String, String>, Double>    deviationRetailPriceOfComponentsByTechnologyCurrency;
    Map<Pair<String, String>, Double>    minimumRetailPriceOfComponentsByTechnologyCurrency;
    Map<Pair<String, String>, Double>    maximumRetailPriceOfComponentsByTechnologyCurrency;
    Long                                 totalNumberOfTools;
    Map<String, Double>                  averageRetailPriceOfToolsByCurrency;
    Map<String, Double>                  deviationRetailPriceOfToolsByCurrency;
    Map<String, Double>                  minimumRetailPriceOfToolsByCurrency;
    Map<String, Double>                  maximumRetailPriceOfToolsByCurrency;
    Map<PatronageStatus, Long>        	 totalNumberOfPatronagesByStatus;
    Map<PatronageStatus, Double>         averagePatronagesBudgetByStatus;
    Map<PatronageStatus, Double>         deviationPatronagesBudgetByStatus;
    Map<PatronageStatus, Double>         minimumPatronagesBudgetByStatus;
    Map<PatronageStatus, Double>         maximumPatronagesBudgetByStatus;

    // Derived attributes -----------------------------------------------------

    // Relationships ----------------------------------------------------------
}