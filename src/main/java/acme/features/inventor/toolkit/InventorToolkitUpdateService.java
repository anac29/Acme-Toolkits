package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.spam_detector.SpamDetector;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor,Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repo;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		final Toolkit toolkit = this.repo.findOneToolkitById(request.getModel().getInteger("id"));
		final int a = request.getPrincipal().getActiveRoleId();
		final int b = this.repo.findInventorByToolkitId(request.getModel().getInteger("id")).getId();
		return  !toolkit.isPublished() && a == b;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setPublished(this.repo.findOneToolkitById(request.getModel().getInteger("id")).isPublished());
		entity.setInventor(this.repo.findOneInventorByUserAccountId(request.getPrincipal().getAccountId()));
		
		request.bind(entity, errors, "code", "title", "description","assemblyNotes","link");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description","assemblyNotes","link");
		
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
		
		final SystemConfiguration sc = this.repo.findSystemConfiguration();
		final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
		
		if(!errors.hasErrors("description")) {
			final boolean isDescSpam = sd.isSpam(entity.getDescription());
			errors.state(request, !isDescSpam, "description", "inventor.toolkit.form.error.spam");
		}

		if(!errors.hasErrors("title")) {
			final boolean isTitleSpam = sd.isSpam(entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("assemblyNotes")) {
			final boolean isANSpam = sd.isSpam(entity.getAssemblyNotes());
			errors.state(request, !isANSpam, "assemblyNotes", "inventor.toolkit.form.error.spam");
		}
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repo.save(entity);
		
	}

}
