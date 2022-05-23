package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.features.spam.SpamDetectorService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyChirpRepository repository;
	
	@Autowired
	protected SpamDetectorService spamService;

	// AbstractCreateService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;

		return true;
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;

		Chirp result;

		result = new Chirp();

		return result;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors,"title", "author", "body", "email");
		entity.setCreationMoment(moment);
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final boolean confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "any.chirp.form.label.confirmation");

		if(!errors.hasErrors("title")) {
			final boolean isTitleSpam = this.spamService.isSpam(entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "any.chirp.form.error.spam");
		}
		
		if(!errors.hasErrors("author")) {
			final boolean isAuthorSpam = this.spamService.isSpam(entity.getAuthor());
			errors.state(request, !isAuthorSpam, "author", "any.chirp.form.error.spam");
		}
		
		if(!errors.hasErrors("body")) {
			final boolean isBodySpam = this.spamService.isSpam(entity.getBody());
			errors.state(request, !isBodySpam, "body", "any.chirp.form.error.spam");
		}
		
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "author", "body", "email");
		model.setAttribute("confirmation", false);
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
