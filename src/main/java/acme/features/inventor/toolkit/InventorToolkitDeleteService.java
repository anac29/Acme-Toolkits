package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolkitDeleteService implements AbstractDeleteService<Inventor,Toolkit>{

	@Autowired
	protected InventorToolkitRepository repo;
	
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		Toolkit toolkit;
		toolkit = this.repo.findOneToolkitById(request.getModel().getInteger("id"));
		return !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor());
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model);
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		return this.repo.findOneToolkitById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		for(final Quantity q:this.repo.findQuantityToolkit(entity.getId())) 
		{
			this.repo.delete(q);
		}
		this.repo.delete(entity);
	}

	
}
