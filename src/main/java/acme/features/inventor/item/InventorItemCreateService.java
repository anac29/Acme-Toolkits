package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.features.spam.SpamDetectorService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	@Autowired
	protected SpamDetectorService spamService;

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

		entity.setPublished(false);

		request.bind(entity, errors, "name", "code", "itemType", "technology", "description", "retailPrice", "link");
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("name")) {
			final boolean isNameSpam = this.spamService.isSpam(entity.getName());
			errors.state(request, !isNameSpam, "name", "item.inventor.form.error.spam");
		}
		if (!errors.hasErrors("technology")) {
			final boolean isTechnologySpam = this.spamService.isSpam(entity.getTechnology());
			errors.state(request, !isTechnologySpam, "technology", "item.inventor.form.error.spam");
		}

		if (!errors.hasErrors("description")) {
			final boolean isDescriptionSpam = this.spamService.isSpam(entity.getDescription());
			errors.state(request, !isDescriptionSpam, "description", "item.inventor.form.error.spam");
		}

		final Item equalCode = this.repository.findOneByCode(entity.getCode()).orElse(null);

		if (!errors.hasErrors("code")) {
			errors.state(request, equalCode == null, "code", "inventor.item.form.error.duplicated-code");
		}

		if (!errors.hasErrors("retailPrice")) {

			final Boolean acceptedCurrency = this.repository.findSystemConfiguration().getAcceptedCurrencies()
					.matches("(.*)" + entity.getRetailPrice().getCurrency() + "(.*)");
			
			
			if(entity.getItemType().equals(ItemType.TOOL)) {	
			errors.state(request,entity.getRetailPrice().getAmount()>=0, "retailPrice",
					"inventor.item.form.error.zero-negative-salary");
			}
			else {
			errors.state(request, entity.getItemType().equals(ItemType.COMPONENT) && entity.getRetailPrice().getAmount() > 0, "retailPrice",
					"inventor.item.form.error.negative-salary");
			}
			errors.state(request, acceptedCurrency, "retailPrice", "inventor.item.form.error.non-accepted-currency");

		}

	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "itemType", "technology", "description", "retailPrice", "link",
				"published");
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
