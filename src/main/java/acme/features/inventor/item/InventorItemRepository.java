package acme.features.inventor.item;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.id = :id and i.itemType = 0")
	Item findOneToolById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findOneById(int id);
	
	@Query("select i from Item i where i.inventor.id = :id")
	Item findOneInventorById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :inventorid and i.itemType = 0")
	Collection<Item> findMyTools(Integer inventorid);

	@Query("SELECT i FROM Item i WHERE i.inventor.id = :inventorid and i.itemType = 1")
	Collection<Item> findMyComponents(Integer inventorid);
	
	@Query("select q.item from Quantity q WHERE q.toolkit.id = :toolkitId and q.item.itemType = 0")
	Collection<Item> findToolsByToolkit(int toolkitId);
	
	
	@Query("select q.item from Quantity q WHERE q.toolkit.id = :toolkitId and q.item.itemType = 1")
	Collection<Item> findComponentByToolkit(int toolkitId);
	
	
	@Query("select inventor from Inventor inventor WHERE inventor.id=:id")
	Optional<Inventor> findInventorById(int id);
	
	@Query("select item from Item item where item.code=:code")
	Optional<Item> findOneByCode(String code);
	
	 @Query("select sc from SystemConfiguration sc")
	 SystemConfiguration findSystemConfiguration();
	 
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :inventorid ")
	Collection<Item> findMyItems(Integer inventorid);

	 
	 
	 
	 

	
	

}
