package acme.features.inventor.quantity;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor,Quantity>{
	
	@Autowired
	protected InventorQuantityRepository repo;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final int masterId = request.getModel().getInteger("masterId");
		final int type = request.getModel().getInteger("type");
		
		final long itemsLeft = this.repo.findItemsLeftByToolkitAndType(masterId, ItemType.values()[type]);
		final Toolkit toolkit = this.repo.findToolkitById(masterId);
		return !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor()) && itemsLeft > 0;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request !=null;
		assert entity !=null;
		assert errors != null;
		
		final Toolkit toolkit = this.repo.findToolkitById(request.getModel().getInteger("masterId"));
		entity.setToolkit(toolkit);
		
		final Item item = this.repo.findItemById(request.getModel().getInteger("item"));
		entity.setItem(item);
		
		if(request.getModel().getInteger("type") == 0) {
			entity.setNumber(1);
			request.bind(entity, errors);
		
		} else {
			request.bind(entity, errors, "number");
		}
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request !=null;
		assert entity !=null;
		assert model != null;
		
		final int masterid = request.getModel().getInteger("masterId");
		final int type = request.getModel().getInteger("type");
		model.setAttribute("type", type);
		model.setAttribute("masterId", masterid);
		
		final List<Item> items = this.repo.findItemByType(ItemType.values()[type]);
		final List<Item>itemsAdded = this.repo.findItemByToolkitAndType(masterid,ItemType.values()[type]);
		items.removeAll(itemsAdded);
		model.setAttribute("items", items);
		
		if(type == 0) {
			request.unbind(entity, model,"item");
		} else {
			request.unbind(entity, model,"item","number");
		}
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request !=null;
		return new Quantity();
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request !=null;
		assert entity !=null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request !=null;
		assert entity !=null;
		this.repo.save(entity);
	}

}
