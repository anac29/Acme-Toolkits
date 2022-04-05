package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("SELECT p FROM Patronage p WHERE p.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("SELECT p FROM Patronage p")
	Collection<Patronage> findAllPatronages();
}
