package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.spam_detector.SpamDetector;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor,Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repo;
	
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
		
		final boolean confirmation = request.getModel().getBoolean("confirm");
		errors.state(request, confirmation, "confirm", "javax.validation.constraints.AssertTrue.message");

		if(!errors.hasErrors("description")) {
			final SystemConfiguration sc = this.repo.findSystemConfiguration();
			final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
			final boolean isDescSpam = sd.isSpam(entity.getDescription());
			errors.state(request, !isDescSpam, "description", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("title")) {
			final SystemConfiguration sc = this.repo.findSystemConfiguration();
			final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
			final boolean isTitleSpam = sd.isSpam(entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("assemblyNotes")) {
			final SystemConfiguration sc = this.repo.findSystemConfiguration();
			final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
			final boolean isAnSpam = sd.isSpam(entity.getAssemblyNotes());
			errors.state(request, !isAnSpam, "assemblyNotes", "inventor.toolkit.form.error.spam");
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
