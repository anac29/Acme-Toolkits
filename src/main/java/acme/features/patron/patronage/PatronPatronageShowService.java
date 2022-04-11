package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return request.getPrincipal().getActiveRole().isAssignableFrom(Patron.class);
		// Comprobar que el id del patrocinio es del patrocinador.
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		Patronage result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		int inventorId;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMomentDate","startMomentDate","finalMomentDate","link");
		model.setAttribute("readonly", true);
		
		inventorId = entity.getInventor().getUserAccount().getId();
		model.setAttribute("inventorId", inventorId);
		
	}

	
}
