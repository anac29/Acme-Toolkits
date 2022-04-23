package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any, Item>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemShowService			showService;
	
	@Autowired
	protected AnyItemListToolService		listToolService;
	
	@Autowired
	protected AnyItemListComponentService	listComponentService;
	
	@Autowired
	protected AnyItemListToolToolkitService listToolToolkitService ;
	
	@Autowired
	protected AnyItemListComponentToolkitService	listComponentToolkitService;
	
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-tool","list", this.listToolService);
		super.addCommand("list-component","list", this.listComponentService);
		super.addCommand("list-tool-toolkit","list", this.listToolToolkitService);
		super.addCommand("list-component-toolkit","list", this.listComponentToolkitService);


		

	}
}
