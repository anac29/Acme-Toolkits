package acme.features.administrator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationRepository repository;

	// AbstractShowService<Administrator, SystemConfiguration> interface ----------------


	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "strongSpamTerms", "weakSpamTerms", "acceptedCurrencies","defaultCurrency","weakThreshold", "strongThreshold");
	}
	
	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;

		SystemConfiguration result;
		
		result = this.repository.findSystemConfiguration();

		return result;
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "strongSpamTerms", "weakSpamTerms", "acceptedCurrencies","defaultCurrency","weakThreshold", "strongThreshold");
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("defaultCurrency")) {
			final String[] currencies=this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
            Boolean defaultCurrency=false;
            for(int i=0;i<currencies.length;i++) {
                if(entity.getDefaultCurrency().equals(currencies[i].trim())) {
                    defaultCurrency=true;
                }
            }
			
			errors.state(request, defaultCurrency, "defaultCurrency", "administrator.configuration.form.error.non-accepted-currency");
		}
		
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
	
}
