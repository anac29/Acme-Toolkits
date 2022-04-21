package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {
	
	@Query("SELECT t FROM Toolkit t where t.inventor.id = :inventorId ")
	Collection<Toolkit> findManyToolkitsByInventorId(int inventorId);
	
	@Query("SELECT t FROM Toolkit t WHERE t.id = :id")
	Toolkit findOneToolkitById(int id);
	

	@Query("SELECT q.item.retailPrice FROM Quantity q WHERE q.toolkit.id = :id")
	Collection<Money> collectPrices(int id);
	
	@Query("SELECT q.item FROM Quantity q WHERE q.toolkit.id = :id")
	Collection<Item> finItemsByToolkit(int id);
	
	@Query("select c.defaultCurrency from SystemConfiguration c")
	String defaultCurrency();
	
	@Query("SELECT t.inventor FROM Toolkit t WHERE t.id = :id")
	Toolkit findInventorByToolkitId(int id);
}
