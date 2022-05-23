package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.features.spam.SpamDetectorService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor,Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repo;
	
	@Autowired
	protected SpamDetectorService spamService;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
		
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("description")) {
			final boolean isDescSpam = this.spamService.isSpam(entity.getDescription());
			errors.state(request, !isDescSpam, "description", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("title")) {
			final boolean isTitleSpam = this.spamService.isSpam(entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("assemblyNotes")) {
			final boolean isAnSpam = this.spamService.isSpam(entity.getAssemblyNotes());
			errors.state(request, !isAnSpam, "assemblyNotes", "inventor.toolkit.form.error.spam");
		}
		
		if (!errors.hasErrors("code")) {
			final Toolkit equalCode = this.repo.findOneToolkitByCode(entity.getCode()).orElse(null);
			errors.state(request, equalCode == null, "code", "inventor.toolkit.form.error.duplicated-code");
		}
		
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repo.save(entity);
		
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;
		final Toolkit toolkit = new Toolkit();
		final Inventor inventor = this.repo.findOneInventorByUserAccountId(request.getPrincipal().getAccountId());
		toolkit.setInventor(inventor);
		toolkit.setPublished(false);
		return toolkit;
	}

}
