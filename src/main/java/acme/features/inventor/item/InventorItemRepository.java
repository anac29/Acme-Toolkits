package acme.features.inventor.item;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository {

	@Query("SELECT i FROM Item i WHERE i.id = :id and i.itemType = 0")
	Item findOneToolById(int id);

	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findOneById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.code = :code")
	Optional<Item> findOneByCode(String code);

	@Query("select inventor from Inventor inventor WHERE inventor.id=:id")
	Optional<Inventor> findInventorById(int id);

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

	@Query("SELECT i FROM Item i WHERE i.inventor.id = :inventorid ")
	Collection<Item> findMyItems(Integer inventorid);
	
  @Query("select c.defaultCurrency from SystemConfiguration c")
  String defaultCurrency();
    
	@Query("select q from  Quantity q where q.item.id = :itemId")
	Collection<Quantity> findManyQuantityByItemId(int itemId);

	@Query("select c.defaultCurrency from SystemConfiguration c")
	String defaultCurrency();

	@Query("SELECT q.number FROM Quantity q WHERE q.toolkit.id = :masterId AND q.item.id = :id")
	Integer findQuantity(int masterId, int id);

}
