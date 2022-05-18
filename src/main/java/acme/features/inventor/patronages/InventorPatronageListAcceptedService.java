package acme.features.inventor.patronages;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronageListAcceptedService implements AbstractListService<Inventor,Patronage>{

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request!=null;
		return true;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		assert request!=null;
		final Integer myId = request.getPrincipal().getActiveRoleId();
		Collection<Patronage> result;
		result = this.repository.findPatronagesAcceptedByInventorId(myId);
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "budget", "creationMomentDate", "startMomentDate", "finalMomentDate");
		
	}

}
