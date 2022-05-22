package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
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
	assert request != null;

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
	
	final Collection<Quantity> collectedMoneys= this.repository.collectPrices(entity.getId());
	final Double totalPrice=collectedMoneys.stream().mapToDouble(q->this.moneyService.computeMoneyExchange(q.getItem().getRetailPrice(),defaultCurrency ).getTarget().getAmount()*q.getNumber()).sum();
	final Money money= new Money();
	money.setAmount(totalPrice);
	money.setCurrency(defaultCurrency);
	
	model.setAttribute("totalPrice", money);
	model.setAttribute("toolkitId", entity.getId());
	
	request.unbind(entity, model, "code", "title", "description","assemblyNotes","link","published");
	
}}
