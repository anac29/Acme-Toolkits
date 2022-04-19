package acme.features.any;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountListService implements AbstractListService<Any,UserAccount>{
	
	@Autowired
	protected AnyUserAccountRepository repository;
	
	@Override
	public boolean authorise(final Request<UserAccount> request) 
	{
		assert request != null;
		return true;
	}
	
	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request)
	{
		assert request != null;
		
		Collection<UserAccount> res;
		
		res = this.repository.findAllPrincipals();
		return res.stream().collect(Collectors.toSet());
	}
	
	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		model.setAttribute("roles",entity.getAuthorityString());
		
		request.unbind(entity, model,"username");
		
	}

}
