package acme.features.any.toolkits;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyToolkitController  extends AbstractController<Any, Toolkit> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyToolkitListService		listService;

	

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.listService);
		
		}

	}
