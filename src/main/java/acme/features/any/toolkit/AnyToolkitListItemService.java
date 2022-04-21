package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListItemService  implements AbstractListService<Any, Toolkit>{
	
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyToolkitRepository repository;

		// AbstractListService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		
		
		assert request != null;

		Collection<Toolkit> result;
		final Integer itemId=request.getModel().getInteger("id");

		result = this.repository.findQuantityByItem(itemId);

		return result;
		

	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity,final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description");
	}
	
	

}
