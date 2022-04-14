package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyService;

	// AbstractListService<Any, Toolkit> interface --------------

@Override
public boolean authorise(final Request<Toolkit> request) {
//	assert request != null;
//
//	boolean result;
//	int toolkitId;
//	final Toolkit toolkit;
//	Inventor inventor;
//
//
//	toolkitId = request.getModel().getInteger("id");
//	inventor = this.repository.findInventorByToolkitId(toolkitId);
//	employer = job.getEmployer();
//	principal = request.getPrincipal();
//	currentMoment = new Date();
//	result = (
//		employer.getUserAccount().getId() == principal.getAccountId() ||
//		!job.isDraftMode() && job.getDeadline().after(currentMoment) 
//	);

	return true;
}

@Override
public Toolkit findOne(final Request<Toolkit> request) {
	assert request != null;

	Toolkit result;
	int id;

	id = request.getModel().getInteger("id");
	result = this.repository.findOneToolkitById(id);

	return result;
}

@Override
public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {

	assert request != null;
	assert entity != null;
	assert model != null;
	
	final String defaultCurrency= this.repository.defaultCurrency();
	
	final Collection<Money> collectedMoneys= this.repository.collectPrices(entity.getId());
	final Double totalPrice=collectedMoneys.stream().mapToDouble(m->this.moneyService.computeMoneyExchange(m,defaultCurrency ).getTarget().getAmount()).sum();
	final Money money= new Money();
	money.setAmount(totalPrice);
	money.setCurrency(defaultCurrency);
	
	model.setAttribute("totalPrice", money);
	model.setAttribute("toolkitId", entity.getId());
	
	request.unbind(entity, model, "code", "title", "description","assemblyNotes","link");
	
}}
