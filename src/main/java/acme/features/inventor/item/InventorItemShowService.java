package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService  implements AbstractShowService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		
		final int id = request.getModel().getInteger("id");
		final Item item = this.repository.findOneToolById(id);
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
		result = this.repository.findOneToolById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		int inventorId;
		
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "itemType", "link");
		model.setAttribute("readonly", true);
		
		inventorId = entity.getInventor().getUserAccount().getId();
		model.setAttribute("inventorId", inventorId);
	}
}