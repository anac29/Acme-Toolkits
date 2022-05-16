package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyService;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findOnePatronageById(id);
		final int myId = request.getPrincipal().getActiveRoleId();
		
		result = (patronage.getPatron().getId() == myId || patronage.getInventor().getId() == myId);
		
		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		Patronage result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		final String defaultCurrency= this.repository.defaultCurrency();
		final Double defaultPrice= this.moneyService.computeMoneyExchange(entity.getBudget(),defaultCurrency).getTarget().getAmount();
		final Money budgetDefault= new Money();
		budgetDefault.setAmount(defaultPrice);
		budgetDefault.setCurrency(defaultCurrency);
		
		model.setAttribute("budgetDefault", budgetDefault);
		

		model.setAttribute("inventors", this.repository.findInventors());
		model.setAttribute("inventId", entity.getInventor().getId());
		model.setAttribute("inventorName", entity.getInventor().getUserAccount().getIdentity().getName());
		model.setAttribute("inventorSurname", entity.getInventor().getUserAccount().getIdentity().getSurname());
		model.setAttribute("inventorEmail", entity.getInventor().getUserAccount().getIdentity().getEmail());
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMomentDate","startMomentDate","finalMomentDate","link","published");

	}

	
}
