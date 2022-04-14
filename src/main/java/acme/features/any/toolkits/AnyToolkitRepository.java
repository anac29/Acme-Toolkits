package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {
	
	@Query("SELECT t FROM Toolkit t")
	Collection<Toolkit> findMany();
	
	@Query("SELECT t FROM Toolkit t WHERE t.id = :id")
	Toolkit findOneToolkitById(int id);
	

	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :id")
	Collection<Quantity> collectPrices(int id);
	
	@Query("SELECT q.item FROM Quantity q WHERE q.toolkit.id = :id")
	Collection<Item> finItemsByToolkit(int id);
	
	@Query("select c.defaultCurrency from SystemConfiguration c")
	String defaultCurrency();

}
