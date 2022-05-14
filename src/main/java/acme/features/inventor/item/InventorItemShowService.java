package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService  implements AbstractShowService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyService;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		
		final int id = request.getModel().getInteger("id");
		final Item item = this.repository.findOneById(id);
		final int myId = request.getPrincipal().getActiveRoleId();
		
		result = (item.getId() == myId || item.getInventor().getId() == myId);
		
		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		Item result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		 final String defaultCurrency= this.repository.defaultCurrency();
	        final Double defaultPrice= this.moneyService.computeMoneyExchange(entity.getRetailPrice(),defaultCurrency).getTarget().getAmount();
	        final Money retailPriceDefault= new Money();
	        retailPriceDefault.setAmount(defaultPrice);
	        retailPriceDefault.setCurrency(defaultCurrency);
	        
	        model.setAttribute("retailPriceDefault", retailPriceDefault);

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "itemType", "link", "published");
	}
}