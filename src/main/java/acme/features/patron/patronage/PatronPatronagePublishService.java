package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.spam_detector.SpamDetector;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractUpdateService<Patron, Patronage> -------------------------------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int patronageId;
		Patronage patronage;

		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		result = (patronage != null && !patronage.isPublished() && request.isPrincipal(patronage.getPatron()));

		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setInventor(this.repository.findInventorById(Integer.valueOf(request.getModel().getAttribute("inventorId").toString())).orElse(null));


		request.bind(entity, errors, "code", "legalStuff", "budget", "startMomentDate", "finalMomentDate","link");
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Patronage existing;

			existing = this.repository.findOnePatronageByCode(entity.getCode());
			if(existing!=null) {
			errors.state(request, existing.getId()==entity.getId() , "code", "patron.patronage.form.error.duplicated");
			}
		}
		
		if(!errors.hasErrors("startMomentDate")) {
			final Date minimumStartDate=DateUtils.addMonths(entity.getCreationMomentDate(), 1);

			
			errors.state(request, entity.getStartMomentDate().after(minimumStartDate), "startMomentDate", "patron.patronage.form.error.too-close-start-date");
			
		}
		if(!errors.hasErrors("finalMomentDate")) {
			final Date minimumFinishDate=DateUtils.addMonths(entity.getStartMomentDate(), 1);

			errors.state(request, entity.getFinalMomentDate().after(minimumFinishDate), "finalMomentDate", "patron.patronage.form.error.one-month");
			
		}
		
		
		if (!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative-budget");
		}
		
		if(!errors.hasErrors("legalStuff")) {
			final SystemConfiguration sc = this.repository.findSystemConfiguration();
			final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
			final boolean isLegalStuffSpam = sd.isSpam(entity.getLegalStuff());
			errors.state(request, !isLegalStuffSpam, "legalStuff", "patron.patronage.form.error.spam");
		}

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "legalStuff", "budget", "startMomentDate", "finalMomentDate","link","published");
		model.setAttribute("inventors", this.repository.findInventors());
		model.setAttribute("inventId", entity.getInventor().getId());
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		entity.setPublished(true);

		this.repository.save(entity);

	}

	
}
