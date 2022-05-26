
package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyItemShowService implements AbstractShowService<Any, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService	moneyService;


	// AbstractShowService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		Item item;
		int id;

		id = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(id);

		return item.isPublished();
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String defaultCurrency = this.repository.defaultCurrency();
		final Double defaultPrice = this.moneyService.computeMoneyExchange(entity.getRetailPrice(), defaultCurrency).getTarget().getAmount();
		final Money retailPriceDefault = new Money();
		retailPriceDefault.setAmount(defaultPrice);
		retailPriceDefault.setCurrency(defaultCurrency);

		model.setAttribute("retailPriceDefault", retailPriceDefault);
		model.setAttribute("acceptedCurrency", this.acceptedCurrencyChecker(entity));

		model.setAttribute("itemId", entity.getId());
		model.setAttribute("inventorName", entity.getInventor().getUserAccount().getIdentity().getName());
		model.setAttribute("inventorSurname", entity.getInventor().getUserAccount().getIdentity().getSurname());
		model.setAttribute("inventorEmail", entity.getInventor().getUserAccount().getIdentity().getEmail());
		request.unbind(entity, model, "name", "code", "technology", "description", "itemType", "retailPrice", "link", "inventor.userAccount.username");
	}
	
	public Boolean acceptedCurrencyChecker(final Item entity) {

		return this.repository.findSystemConfiguration().getAcceptedCurrencies().matches("(.*)" + entity.getRetailPrice().getCurrency() + "(.*)");

	}

}
