package acme.features.authenticated.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedConfigurationShowService implements AbstractShowService<Authenticated,SystemConfiguration>{

	@Autowired
	protected AuthenticatedConfigurationRepository repo;
	
	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		return true;
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		return this.repo.getSystemConfiguration();
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("link", "https://exchangeratesapi.io/documentation/");
		
		request.unbind(entity, model, "acceptedCurrencies","defaultCurrency");
		
	}

}
