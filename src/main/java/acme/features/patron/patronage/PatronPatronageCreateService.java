package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractCreateService<Employer, Duty> interface -------------------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;

		Patronage result;


		result = new Patronage();
		result.setPatron(this.repository.findPatronById(request.getPrincipal().getActiveRoleId()).orElse(null));


		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMomentDate(moment);
		entity.setStatus(PatronageStatus.PROPOSED);
		entity.setPublished(false);
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
			errors.state(request, existing == null, "code", "patron.patronage.form.error.duplicated");
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

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "legalStuff", "budget", "startMomentDate", "finalMomentDate","link");
		model.setAttribute("inventors", this.repository.findInventors());
		
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		

		this.repository.save(entity);
	}
	
}
