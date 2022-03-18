package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronage.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashBoard implements Serializable{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

		Map<PatronageStatus, Integer>								totalNumberOfPatronagesByStatus;
		Map<Pair<String, PatronageStatus>,Double>					averageBudgetOfPatronagesStatusByCurrency;
		Map<Pair<String, PatronageStatus>,Double>					desviationBudgetOfPatronagesStatusByCurrency;
		Map<Pair<String, PatronageStatus>,Double>					minimumBudgetOfPatronagesStatusByCurrency;
		Map<Pair<String, PatronageStatus>,Double>					maximumBudgetOfPatronagesStatusByCurrency;
}
