package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorItemListComponentToolkitService  implements AbstractListService<Inventor, Item>  {
	

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	// AbstractListService<Authenticated, Item> interface --------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;

		Collection<Item> result;
		final int toolkitId= request.getModel().getInteger("id");
		result = this.repository.findComponentByToolkit( toolkitId);

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Collection<Item> entities, final Model model) {
		assert request != null;
		assert entities != null;
		assert model != null;
		
		final int masterId = request.getModel().getInteger("id");
		model.setAttribute("masterId", masterId);
		model.setAttribute("type", 1);
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int masterId = request.getModel().getInteger("id");
		final Integer amount = this.repository.findQuantity(masterId,entity.getId());
		model.setAttribute("amount", amount);
		request.unbind(entity, model, "name", "code", "technology","retailPrice","description","link");
	}
	

}
