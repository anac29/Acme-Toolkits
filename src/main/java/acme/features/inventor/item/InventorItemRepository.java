package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.id = :id and i.itemType = 0")
	Item findOneToolById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findOneById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :inventorid and i.itemType = 0")
	Collection<Item> findMyTools(Integer inventorid);

	@Query("SELECT i FROM Item i WHERE i.inventor.id = :inventorid and i.itemType = 1")
	Collection<Item> findMyComponents(Integer inventorid);
	
	@Query("select q.item from Quantity q WHERE q.toolkit.id = :toolkitId and q.item.itemType = 0")
	Collection<Item> findToolsByToolkit(int toolkitId);
	
	
	@Query("select q.item from Quantity q WHERE q.toolkit.id = :toolkitId and q.item.itemType = 1")
	Collection<Item> findComponentByToolkit(int toolkitId);

}
