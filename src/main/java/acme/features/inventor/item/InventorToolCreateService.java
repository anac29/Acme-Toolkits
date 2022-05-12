package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.spam_detector.SpamDetector;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolCreateService implements AbstractCreateService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;
	
	
	

	// AbstractCreateService<Inventor, Item> interface -------------------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;



		return true;
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;

		Item result;
	

		result = new Item();
		result.setInventor(this.repository.findInventorById(request.getPrincipal().getActiveRoleId()).orElse(null));

		
		

		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setItemType(ItemType.TOOL);
		entity.setPublished(false);
		
		
		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice","link");
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		  if(!errors.hasErrors("name")) {
	            final SystemConfiguration sc = this.repository.findSystemConfiguration();
	            final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
	            final boolean isNameSpam = sd.isSpam(entity.getName());
	            errors.state(request, !isNameSpam, "name", "item.inventor.form.error.spam");
	        }
	      if(!errors.hasErrors("technology")) {
	            final SystemConfiguration sc = this.repository.findSystemConfiguration();
	            final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
	            final boolean isTechnologySpam = sd.isSpam(entity.getTechnology());
	            errors.state(request, !isTechnologySpam, "technology", "item.inventor.form.error.spam");
	        }
	      
	   
	      if(!errors.hasErrors("description")) {
	            final SystemConfiguration sc = this.repository.findSystemConfiguration();
	            final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
	            final boolean isDescriptionSpam = sd.isSpam(entity.getDescription());
	            errors.state(request, !isDescriptionSpam, "description", "item.inventor.form.error.spam");
	        }
		
		final Item equalCode=this.repository.findOneByCode(entity.getCode()).orElse(null);
		
		if(!errors.hasErrors("code")) {
			errors.state(request, equalCode==null, "code", "inventor.item.form.error.duplicated-code");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			
			final String[] currencies=this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			Boolean acceptedCurrency=false;
			for(int i=0;i<currencies.length;i++) {
				if(entity.getRetailPrice().getCurrency().equals(currencies[i].trim())) {
					acceptedCurrency=true;
				}
			}
			
			
			errors.state(request, entity.getRetailPrice().getAmount() > 0 , "retailPrice", "inventor.item.form.error.negative-salary");
			errors.state(request, acceptedCurrency, "retailPrice", "inventor.item.form.error.non-accepted-currency");

		}

	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		

		request.unbind( entity,model,"name", "code", "technology", "description","retailPrice", "link","published");

	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		

		this.repository.save(entity);
	}

}
