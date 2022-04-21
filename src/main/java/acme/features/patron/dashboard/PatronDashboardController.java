package acme.features.patron.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.PatronDashboard;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronDashboardController extends AbstractController<Patron, PatronDashboard> {

	@Autowired
	protected PatronDashboardShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}
}
