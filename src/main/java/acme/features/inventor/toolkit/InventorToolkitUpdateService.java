package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.spam_detector.SpamDetector;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor,Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repo;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyService;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		final Toolkit toolkit = this.repo.findOneToolkitById(request.getModel().getInteger("id"));
		final int a = request.getPrincipal().getActiveRoleId();
		final int b = this.repo.findInventorByToolkitId(request.getModel().getInteger("id")).getId();
		return  !toolkit.isPublished() && a == b;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setPublished(this.repo.findOneToolkitById(request.getModel().getInteger("id")).isPublished());
		entity.setInventor(this.repo.findOneInventorByUserAccountId(request.getPrincipal().getAccountId()));
		
		request.bind(entity, errors, "code", "title", "description","assemblyNotes","link");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String defaultCurrency = this.repo.defaultCurrency();

		final Collection<Quantity> collectedMoneys = this.repo.collectPrices(entity.getId());
		final Double totalPrice = collectedMoneys.stream()
				.mapToDouble(q -> this.moneyService.computeMoneyExchange(q.getItem().getRetailPrice(), defaultCurrency)
						.getTarget().getAmount() * q.getNumber())
				.sum();
		final Money money = new Money();
		money.setAmount(totalPrice);
		money.setCurrency(defaultCurrency);

		model.setAttribute("totalPrice", money);
		
		request.unbind(entity, model, "code", "title", "description","assemblyNotes","link","published");
		
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		return this.repo.findOneToolkitById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final SystemConfiguration sc = this.repo.findSystemConfiguration();
		final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
		
		if(!errors.hasErrors("description")) {
			final boolean isDescSpam = sd.isSpam(entity.getDescription());
			errors.state(request, !isDescSpam, "description", "inventor.toolkit.form.error.spam");
		}

		if(!errors.hasErrors("title")) {
			final boolean isTitleSpam = sd.isSpam(entity.getTitle());
			errors.state(request, !isTitleSpam, "title", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("assemblyNotes")) {
			final boolean isANSpam = sd.isSpam(entity.getAssemblyNotes());
			errors.state(request, !isANSpam, "assemblyNotes", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("code")) {
			Toolkit existing;
			existing = this.repo.findOneToolkitByCode(entity.getCode()).orElse(null);
			if(existing!=null) {
				errors.state(request, existing.getId() == entity.getId(), "code", "inventor.item.form.error.duplicated-code");
			}
		}
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repo.save(entity);
		
	}

}
