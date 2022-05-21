package acme.features.administrator.announcement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.spam_detector.SpamDetector;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.entities.configuration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator,Announcement>{

	@Autowired
	protected AdministratorAnnouncementRepository repo;
	
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Date moment= new Date(System.currentTimeMillis()-1);
		
		entity.setCreationMoment(moment);
		
		request.bind(entity, errors, "title","body","flag","link");
		
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"title","body","flag","link");
		
		model.setAttribute("confirmation", false);
		
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;
		
		Announcement res;
		
		res = new Announcement();
		res.setFlag(false);
		return res;
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final boolean confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		if(!errors.hasErrors("body")) {
			final SystemConfiguration sc = this.repo.findSystemConfiguration();
			final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
			final boolean isBodySpam = sd.isSpam(entity.getBody());
			errors.state(request, !isBodySpam, "body", "administrator.announcement.form.error.spam");
		}
		
		if(!errors.hasErrors("title")) {
			final SystemConfiguration sc = this.repo.findSystemConfiguration();
			final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
			final boolean isTitleSpam = sd.isSpam(entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "administrator.announcement.form.error.spam");
		}
		
		
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;
		
		this.repo.save(entity);
		
	}

}
