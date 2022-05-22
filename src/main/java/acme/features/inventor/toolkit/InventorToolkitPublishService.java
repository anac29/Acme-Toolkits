package acme.features.inventor.toolkit;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor,Toolkit>{

	@Autowired
	protected  InventorToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit item;
		Inventor inventor;

		masterId = request.getModel().getInteger("id");
		item = this.repository.findOneToolkitById(masterId);
		inventor = item.getInventor();
		result =  !item.isPublished() && request.isPrincipal(inventor);

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors,"code", "title", "description","assemblyNotes");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model,"code", "title", "description","assemblyNotes");
		
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {

		return this.repository.findOneToolkitById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<Quantity> q = this.repository.findQuantityToolkit(entity.getId());
		
		final Integer tipos = q.stream().map(x -> x.getItem().getItemType()).collect(Collectors.toSet()).size();
		
		errors.state(request, tipos==2, "code", "inventor.toolkit.item.error");
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		

		entity.setPublished(true);
		this.repository.save(entity);
		
	}

}
