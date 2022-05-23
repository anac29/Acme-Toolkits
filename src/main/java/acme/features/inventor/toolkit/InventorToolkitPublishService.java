package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit> {

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyService;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;
		Inventor inventor;

		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(masterId);
		inventor = toolkit.getInventor();
		result = !toolkit.isPublished() && request.isPrincipal(inventor);

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "code", "title", "description", "assemblyNotes");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String defaultCurrency = this.repository.defaultCurrency();

		final Collection<Quantity> collectedMoneys = this.repository.collectPrices(entity.getId());
		final Double totalPrice = collectedMoneys.stream()
				.mapToDouble(q -> this.moneyService.computeMoneyExchange(q.getItem().getRetailPrice(), defaultCurrency)
						.getTarget().getAmount() * q.getNumber())
				.sum();
		final Money money = new Money();
		money.setAmount(totalPrice);
		money.setCurrency(defaultCurrency);

		model.setAttribute("totalPrice", money);
		model.setAttribute("toolkitId", entity.getId());
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "published");

	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		return this.repository.findOneToolkitById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final List<Quantity> q = this.repository.findQuantityToolkit(entity.getId());

		final Integer tipos = q.stream().map(x -> x.getItem().getItemType()).collect(Collectors.toSet()).size();

		errors.state(request, tipos == 2, "code", "inventor.toolkit.item.error");

	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		this.repository.save(entity);

	}

}
