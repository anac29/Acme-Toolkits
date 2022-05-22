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
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorQuantityDeleteService implements AbstractDeleteService<Inventor,Quantity>{

	@Autowired
	protected InventorQuantityRepository repo;
	
	@Override
	public boolean authorise(final Request<Quantity> request) 
	{
		assert request != null;
		
		int masterId;
		final Toolkit toolkit;
		
		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repo.findToolkitById(masterId);
		
		return !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor());
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert errors != null;
		assert entity != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert model != null;
		assert entity != null;
		
		
		final int masterId = request.getModel().getInteger("masterId");
		if(request.getModel().getInteger("type") == 0) 
		{
			final List<Item> items = this.repo.findToolsByToolkit(masterId);
			model.setAttribute("items", items);
		}else 
		{
			final List<Item> items = this.repo.findComponentsByToolkit(masterId);
			model.setAttribute("items", items);
		}
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
		request.unbind(entity, model, "item");
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		return new Quantity();
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert errors != null;
		assert entity != null;
	}

	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		final int masterId = request.getModel().getInteger("masterId");
		final int itemId = request.getModel().getInteger("item");
		final Quantity q = this.repo.findQuantityTool(masterId,itemId);
		this.repo.delete(q);
		
	}
	
}
