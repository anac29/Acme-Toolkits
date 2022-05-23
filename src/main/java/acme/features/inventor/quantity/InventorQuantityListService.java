package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.ItemType;
import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;


@Service
public class InventorQuantityListService implements AbstractListService<Inventor, Quantity>  {
	

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	// AbstractListService<Authenticated, Item> interface --------------

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;

		Collection<Quantity> result;
		final int toolkitId = request.getModel().getInteger("masterId");
		final int type = request.getModel().getInteger("type");
		result = this.repository.findAllQuantitiesByToolkitAndType(toolkitId, ItemType.values()[type]);
		
		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null;
		assert entities != null;
		assert model != null;
		
		final int masterId = request.getModel().getInteger("masterId");
		model.setAttribute("masterId", masterId);
		final Toolkit toolkit = this.repository.findToolkitById(masterId);
		model.setAttribute("published", toolkit.isPublished());
		
		final int type = request.getModel().getInteger("type");
		model.setAttribute("type", type);
		
		final long itemsLeft = this.repository.findItemsLeftByToolkitAndType(masterId,ItemType.values()[type]);
		model.setAttribute("itemsLeft", itemsLeft);
		
		final int itemsIn = this.repository.findItemByToolkitAndType(masterId, ItemType.values()[type]).size();
		model.setAttribute("itemsIn", itemsIn);
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"item.name", "item.code", "item.technology","item.retailPrice","item.description","item.link","number");
		
	}
	

}