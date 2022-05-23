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
		
		final int masterId = request.getModel().getInteger("masterId");
		final Toolkit toolkit = this.repo.findToolkitById(masterId);
		final int type = request.getModel().getInteger("type");
		
		final int itemsIn = this.repo.findItemByToolkitAndType(masterId, ItemType.values()[type]).size();
		
		return !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor()) && itemsIn > 0;
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
		final int type = request.getModel().getInteger("type");
		final List<Item> items = this.repo.findItemByToolkitAndType(masterId,ItemType.values()[type]);
		model.setAttribute("items", items);
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
		model.setAttribute("type", type);
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
