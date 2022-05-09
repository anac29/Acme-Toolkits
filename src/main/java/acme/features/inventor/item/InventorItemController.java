package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item> {
	
	@Autowired
	protected InventorToolListService		listToolService;
	
	@Autowired
	protected InventorComponentListService	listComponentService;
	
	@Autowired
	protected InventorItemShowService showService;
	
	@Autowired
	protected InventorItemListToolToolkitService listToolToolkitService ;
	
	@Autowired
	protected InventorItemListComponentToolkitService	listComponentToolkitService;
	
	@Autowired
	protected InventorToolCreateService createToolService;
	
	@Autowired
	protected InventorComponentCreateService createComponentService;
	
	
	@Autowired
	protected InventorItemUpdateService updateItemService;
	@Autowired
	protected InventorItemDeleteService deleteItemService;
	
	@Autowired
	protected InventorItemPublishService publishService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-tool","list", this.listToolService);
		super.addCommand("list-component","list", this.listComponentService);
		super.addCommand("list-tool-toolkit","list", this.listToolToolkitService);
		super.addCommand("list-component-toolkit","list", this.listComponentToolkitService);
		
		super.addCommand("create-component","create", this.createComponentService);
		super.addCommand("create-tool","create", this.createToolService);
		super.addCommand("update", this.updateItemService);
		super.addCommand("delete", this.deleteItemService);
		super.addCommand("publish","update", this.publishService);



	}
}
