package acme.features.inventor.items;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findOneItemById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :inventorid")
	Collection<Item> findMyItems(Integer inventorid);

}
