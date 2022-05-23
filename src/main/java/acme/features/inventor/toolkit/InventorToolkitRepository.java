package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {
	
	@Query("SELECT t FROM Toolkit t where t.inventor.id = :inventorId ")
	Collection<Toolkit> findManyToolkitsByInventorId(int inventorId);
	
	@Query("SELECT t FROM Toolkit t WHERE t.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("SELECT t FROM Toolkit t WHERE t.code = :code")
	Optional<Toolkit> findOneToolkitByCode(String code);
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :id")
	Collection<Quantity> collectPrices(int id);
	
	@Query("select c.defaultCurrency from SystemConfiguration c")
	String defaultCurrency();
	
	@Query("SELECT t.inventor FROM Toolkit t WHERE t.id = :id")
	Inventor findInventorByToolkitId(int id);
	
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findOneInventorByUserAccountId(int id);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select q FROM Quantity q WHERE q.toolkit.id = :id")
	List<Quantity> findQuantityToolkit(int id);
}
