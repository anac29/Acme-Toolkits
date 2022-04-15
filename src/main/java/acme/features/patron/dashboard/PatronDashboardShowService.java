package acme.features.patron.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageStatus;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{

	@Autowired
	protected PatronDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		final int masterId = request.getPrincipal().getActiveRoleId();
		final List<Object[]> totalNumberOfPatronagesByStatus = this.repository.findTotalNumberOfPatronagesByStatus(masterId);
		final Map<PatronageStatus,Long> mapTotalNumberOfPatronagesByStatus = totalNumberOfPatronagesByStatus.stream().collect(Collectors.toMap(p -> (PatronageStatus) p[0], p -> (Long) p[1]));
		
		final Map<Pair<String,PatronageStatus>,Double> mapAverageBudgetOfPatronagesStatusByCurrency = new HashMap<>();
		final Map<Pair<String,PatronageStatus>,Double> mapDeviationBudgetOfPatronagesStatusByCurrency = new HashMap<>();
		final Map<Pair<String,PatronageStatus>,Double> mapMinimumBudgetOfPatronagesStatusByCurrency = new HashMap<>();
		final Map<Pair<String,PatronageStatus>,Double> mapMaximumBudgetOfPatronagesStatusByCurrency = new HashMap<>();
		
		final List<Object[]> metricas = this.repository.findOtherMetrics(masterId);
		for(final Object[] fila : metricas) {
			final String currency = (String) fila[0];
			final PatronageStatus status = (PatronageStatus) fila[1];
			final Double avg = (Double) fila[2];
			final Double stdev = (Double) fila[3];
			final Double minBudget = (Double) fila[4];
			final Double maxBudget = (Double) fila[5];
			
			mapAverageBudgetOfPatronagesStatusByCurrency.put(Pair.of(currency, status), avg);
			mapDeviationBudgetOfPatronagesStatusByCurrency.put(Pair.of(currency, status), stdev);
			mapMaximumBudgetOfPatronagesStatusByCurrency.put(Pair.of(currency, status), maxBudget);
			mapMinimumBudgetOfPatronagesStatusByCurrency.put(Pair.of(currency, status), minBudget);
		}
		
		final PatronDashboard resultPdb = new PatronDashboard();
		resultPdb.setTotalNumberOfPatronagesByStatus(mapTotalNumberOfPatronagesByStatus);
		resultPdb.setAverageBudgetOfPatronagesStatusByCurrency(mapAverageBudgetOfPatronagesStatusByCurrency);
		resultPdb.setDeviationBudgetOfPatronagesStatusByCurrency(mapDeviationBudgetOfPatronagesStatusByCurrency);
		resultPdb.setMaximumBudgetOfPatronagesStatusByCurrency(mapMaximumBudgetOfPatronagesStatusByCurrency);
		resultPdb.setMinimumBudgetOfPatronagesStatusByCurrency(mapMinimumBudgetOfPatronagesStatusByCurrency);
		
		return resultPdb;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberOfPatronagesByStatus",
			"averageBudgetOfPatronagesStatusByCurrency", "deviationBudgetOfPatronagesStatusByCurrency",
			"minimumBudgetOfPatronagesStatusByCurrency", "maximumBudgetOfPatronagesStatusByCurrency");
		model.setAttribute("readonly", true);
		
		final Set<String> currencies = entity.getAverageBudgetOfPatronagesStatusByCurrency().keySet().stream().map(Pair::getFirst).collect(Collectors.toSet());
		model.setAttribute("currencies", currencies);
	}

}
