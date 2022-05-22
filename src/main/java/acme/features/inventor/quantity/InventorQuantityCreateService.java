package acme.features.inventor.quantity;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
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
		Toolkit toolkit;
		toolkit = this.repo.findToolkitById(request.getModel().getInteger("masterId"));
		return !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor());
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
		if(request.getModel().getInteger("type") == 0) 
		{entity.setNumber(1);request.bind(entity, errors);}
		else 
		{
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
		if(type == 0) 
		{
			final List<Item> items = this.repo.findTools();
			final List<Item>itemsAdded = this.repo.findToolsByToolkit(masterid);
			items.removeAll(itemsAdded);
			model.setAttribute("items", items);
			request.unbind(entity, model,"item");
		}else 
		{
			final List<Item>items = this.repo.findComponents();
			final List<Item> itemsAdded = this.repo.findComponentsByToolkit(masterid);
			items.removeAll(itemsAdded);
			model.setAttribute("items", items);
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
