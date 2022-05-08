package acme.features.patron.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronage.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronPatronageController extends AbstractController<Patron, Patronage> {
	
	@Autowired
	protected PatronPatronageListService listService;
	
	@Autowired
	protected PatronPatronageCreateService createService;
	
	@Autowired
	protected PatronPatronageShowService showService;
	
	@Autowired
	protected PatronPatronageUpdateService	updateService;
	
	@Autowired
	protected PatronPatronageDeleteService	deleteService;
	
	@Autowired
	protected PatronPatronagePublishService	publishService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish", "update", this.publishService);
	}

}
