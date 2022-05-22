
package acme.features.inventor.patronageReports;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
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

		boolean result;
		int patronageId;
		final Patronage patronage;

		patronageId = request.getModel().getInteger("masterId");
		patronage = this.repository.findOnePatronageById(patronageId);
		result = patronage!= null && patronage.getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;

		result = new PatronageReport();

		result.setPatronage(this.repository.findOnePatronageById(request.getModel().getInteger("masterId")));

		Integer serialNumber = this.repository.findMaximumSerialNumberByPatronage(request.getModel().getInteger("masterId"));

		if (serialNumber != null) {
			serialNumber = serialNumber + 1;

		} else {
			serialNumber = 1;
		}
		result.setSerialNumber(serialNumber);
		result.setAutomaticSequenceNumber(this.repository.findOnePatronageById(request.getModel().getInteger("masterId")).getCode() + ":" + String.format("%04d", serialNumber));  
		
		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		entity.setCreationMoment(moment);

		entity.setPatronage(this.repository.findOnePatronageById(Integer.valueOf(request.getModel().getAttribute("masterId").toString())));
		request.bind(entity, errors, "memorandum", "link");
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "inventor.patronage-report.form.label.confirmation");

	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "memorandum", "link");

		model.setAttribute("automaticSequenceNumber", entity.getAutomaticSequenceNumber());
		model.setAttribute("patronageCode", this.repository.findOnePatronageById(request.getModel().getInteger("masterId")).getCode());
		model.setAttribute("confirmation", false);
		model.setAttribute("patronageId", request.getModel().getInteger("masterId").toString());
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
