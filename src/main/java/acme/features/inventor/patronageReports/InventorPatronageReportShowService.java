package acme.features.inventor.patronageReports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportShowService implements AbstractShowService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractShowService<Employer, Application> interface -------------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		boolean result;
		int patronageReportId;
		PatronageReport patronageReport;

		patronageReportId = request.getModel().getInteger("id");
		patronageReport = this.repository.findOnePatronageReportById(patronageReportId);
		result = patronageReport != null && patronageReport.getPatronage().getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageReportById(id);

		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "automaticSequenceNumber", "creationMoment", "memorandum", "link", "patronage.code");
		model.setAttribute("id", entity.getPatronage().getInventor().getId());
		model.setAttribute("patronageCode", entity.getPatronage().getCode());
		model.setAttribute("patronageId", request.getModel().getInteger("id"));
	}

}
