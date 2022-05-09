
package acme.features.inventor.patronageReports;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractCreateService<Administrator, Shout> interface --------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;

		result = new PatronageReport();

		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "title", "author", "body", "email");
		entity.setCreationMoment(moment);
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

//		if (!errors.hasErrors("body")) {
//			final SystemConfiguration sc = this.repository.findSystemConfiguration();
//			final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
//			final boolean isBodySpam = sd.isSpam(entity.getBody());
//			errors.state(request, !isBodySpam, "body", "any.chirp.form.error.spam");
//		}

	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "automaticSequenceNumber", "creationMoment", "memorandum", "link", "patronage.code");
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
