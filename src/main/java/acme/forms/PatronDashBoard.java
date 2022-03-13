package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashBoard implements Serializable{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

		Double						totalNumberOfPatronagesByType;
		Double						averageBudgetOfPatronagesTypesByCurrency;
		Double						desviationBudgetOfPatronagesTypesByCurrency;
		Double						minimumBudgetOfPatronagesTypesByCurrency;
		Double						maximumBudgetOfPatronagesTypesByCurrency;
}
