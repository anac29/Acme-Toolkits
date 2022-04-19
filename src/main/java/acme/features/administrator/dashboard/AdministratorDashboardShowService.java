/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageStatus;
import acme.forms.AdministratorDashBoard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashBoard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, AdministratorDashBoard> interface ----------------

	@Override
	public boolean authorise(final Request<AdministratorDashBoard> request) {
		assert request != null;
		return true;
	}

	@Override
	public AdministratorDashBoard findOne(final Request<AdministratorDashBoard> request) {
		assert request != null;

		final AdministratorDashBoard result;
		
		//Components
		
		final Long						           totalNumberOfComponents;
		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		
	    final Map<Pair<String, String>, Double>    averageRetailPriceOfComponentsByTechnologyCurrency  = new HashMap<>();
	    final Map<Pair<String, String>, Double>    deviationRetailPriceOfComponentsByTechnologyCurrency = new HashMap<>();
	    final Map<Pair<String, String>, Double>    minimumRetailPriceOfComponentsByTechnologyCurrency = new HashMap<>();
	    final Map<Pair<String, String>, Double>    maximumRetailPriceOfComponentsByTechnologyCurrency = new HashMap<>();
		
		final List<Object[]> metricsComponentsByTechnologyCurrency = this.repository.findMetricsComponentsByTechnologyCurrency();
	    for(final Object[] fila : metricsComponentsByTechnologyCurrency) {
			final String currency = (String) fila[0];
			final String technology = (String) fila[1];
			final Double avg = (Double) fila[2];
			final Double stdev = (Double) fila[3];
			final Double min = (Double) fila[4];
			final Double max = (Double) fila[5];
			
			averageRetailPriceOfComponentsByTechnologyCurrency.put(Pair.of(currency, technology), avg);
			deviationRetailPriceOfComponentsByTechnologyCurrency.put(Pair.of(currency, technology), stdev);
			minimumRetailPriceOfComponentsByTechnologyCurrency.put(Pair.of(currency, technology), min);
			maximumRetailPriceOfComponentsByTechnologyCurrency.put(Pair.of(currency, technology), max);
		}
	    
	   //Tools
	    
	    final Long                                 totalNumberOfTools;
	    totalNumberOfTools = this.repository.totalNumberOfTools();
	    
	    final Map<String, Double>                  averageRetailPriceOfToolsByCurrency = new HashMap<>();
	    final Map<String, Double>                  deviationRetailPriceOfToolsByCurrency = new HashMap<>();
	    final Map<String, Double>                  minimumRetailPriceOfToolsByCurrency = new HashMap<>();
	    final Map<String, Double>                  maximumRetailPriceOfToolsByCurrency = new HashMap<>();
	    
	    final List<Object[]> metricsToolsByCurrency = this.repository.findMetricsToolsByCurrency();
	    
	    for(final Object[] fila : metricsToolsByCurrency) {
			final String currency = (String) fila[0];
			final Double avg = (Double) fila[1];
			final Double stdev = (Double) fila[2];
			final Double min = (Double) fila[3];
			final Double max = (Double) fila[4];
			
			averageRetailPriceOfToolsByCurrency.put(currency, avg);
			deviationRetailPriceOfToolsByCurrency.put(currency, stdev);
			minimumRetailPriceOfToolsByCurrency.put(currency, min);
			maximumRetailPriceOfToolsByCurrency.put(currency, max);
	    }
	    
	    // Patronages
	    
	    final List<Object[]>         			   totalNumberOfPatronagesByStatus;
	    totalNumberOfPatronagesByStatus = this.repository.totalNumberOfPatronagesByStatus();
	    
    	final Map<PatronageStatus, Long> mapTotalNumberOfPatronagesByStatus = new HashMap<>();
	    for(final Object[] fila : totalNumberOfPatronagesByStatus) {
	    	final PatronageStatus status = (PatronageStatus) fila[0];
	    	final Long number = (Long) fila[1];
	    	mapTotalNumberOfPatronagesByStatus.put(status, number);
	    }
	    
	    final Map<PatronageStatus, Double>         averagePatronagesBudgetByStatus= new HashMap<>();
	    final Map<PatronageStatus, Double>         deviationPatronagesBudgetByStatus= new HashMap<>();
	    final Map<PatronageStatus, Double>         minimumPatronagesBudgetByStatus= new HashMap<>();
	    final Map<PatronageStatus, Double>         maximumPatronagesBudgetByStatus= new HashMap<>();


	    final List<Object[]> metricsPatronagesBudgetByStatus = this.repository.findMetricsPatronagesBudgetByStatus();
	    
	    for(final Object[] fila : metricsPatronagesBudgetByStatus) {
			final String status = (String) fila[0];
			final Double avg = (Double) fila[1];
			final Double stdev = (Double) fila[2];
			final Double min = (Double) fila[3];
			final Double max = (Double) fila[4];
			
			averageRetailPriceOfToolsByCurrency.put(status, avg);
			deviationRetailPriceOfToolsByCurrency.put(status, stdev);
			minimumRetailPriceOfToolsByCurrency.put(status, min);
			maximumRetailPriceOfToolsByCurrency.put(status, max);
	    }
	    
		result = new AdministratorDashBoard();
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setAverageRetailPriceOfComponentsByTechnologyCurrency(averageRetailPriceOfComponentsByTechnologyCurrency);
		result.setDeviationRetailPriceOfComponentsByTechnologyCurrency(deviationRetailPriceOfComponentsByTechnologyCurrency);
		result.setMinimumRetailPriceOfComponentsByTechnologyCurrency(minimumRetailPriceOfComponentsByTechnologyCurrency);
		result.setMaximumRetailPriceOfComponentsByTechnologyCurrency(maximumRetailPriceOfComponentsByTechnologyCurrency);
		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setAverageRetailPriceOfToolsByCurrency(averageRetailPriceOfToolsByCurrency);
		result.setDeviationRetailPriceOfToolsByCurrency(deviationRetailPriceOfToolsByCurrency);
		result.setMinimumRetailPriceOfToolsByCurrency(minimumRetailPriceOfToolsByCurrency);
		result.setMaximumRetailPriceOfToolsByCurrency(maximumRetailPriceOfToolsByCurrency);
		result.setTotalNumberOfPatronagesByStatus(mapTotalNumberOfPatronagesByStatus);
		result.setAveragePatronagesBudgetByStatus(averagePatronagesBudgetByStatus);
		result.setDeviationPatronagesBudgetByStatus(deviationPatronagesBudgetByStatus);
		result.setMinimumPatronagesBudgetByStatus(minimumPatronagesBudgetByStatus);
		result.setMaximumPatronagesBudgetByStatus(maximumPatronagesBudgetByStatus);

		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashBoard> request, final AdministratorDashBoard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"totalNumberOfComponents", "averageRetailPriceOfComponentsByTechnologyCurrency", "deviationRetailPriceOfComponentsByTechnologyCurrency", // 
			"minimumRetailPriceOfComponentsByTechnologyCurrency", "maximumRetailPriceOfComponentsByTechnologyCurrency", //
			"totalNumberOfTools", "averageRetailPriceOfToolsByCurrency", //
			"deviationRetailPriceOfToolsByCurrency", "minimumRetailPriceOfToolsByCurrency", //
			"maximumRetailPriceOfToolsByCurrency", "totalNumberOfPatronagesByType", //
			"averagePatronagesBudgetByStatus", "deviationPatronagesBudgetByStatus", //
			"minimumPatronagesBudgetByStatus", "maximumPatronagesBudgetByStatus");
	}

}
