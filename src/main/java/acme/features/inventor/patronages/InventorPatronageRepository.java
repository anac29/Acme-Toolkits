package acme.features.inventor.patronages;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {
	
	@Query("select p from Patronage p where p.id = :masterId")
	Patronage findOnePatronageById(int masterId);
	
	@Query("select p from Patronage p where p.inventor.id = :inventorId and p.id = :masterId")
	Patronage findOnePatronageByInventorId(int masterId, int inventorId);
	
	@Query("select p from Patronage p where p.inventor.id = :inventorId")
	Collection<Patronage> findPatronagesByInventorId(int inventorId);
	
	@Query("select p from Patronage p where p.inventor.id = :inventorId and p.status=1")
	Collection<Patronage> findPatronagesAcceptedByInventorId(int inventorId);
	
	@Query("select p from Patronage p where p.inventor.id = :inventorId and p.status=0")
	Collection<Patronage> findPatronagesPendingByInventorId(int inventorId);
	
}
