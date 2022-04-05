package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {
	
	@Query("SELECT t FROM Toolkit t")
	Collection<Toolkit> findMany();

}
