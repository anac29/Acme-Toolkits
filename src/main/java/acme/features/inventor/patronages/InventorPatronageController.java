package acme.features.inventor.patronages;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronage.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageController extends AbstractController<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageListAcceptedService		listAcceptedService;
	
	@Autowired
	protected InventorPatronageListPendingService		listPendingService;

	@Autowired
	protected InventorPatronageShowService		showService;
	
	@Autowired
	protected InventorPatronageUpdateService	updateService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list-accepted","list", this.listAcceptedService);
		super.addCommand("list-pending","list", this.listPendingService);
		super.addCommand("update", this.updateService);
		super.addCommand("show", this.showService);
	}
	
}
