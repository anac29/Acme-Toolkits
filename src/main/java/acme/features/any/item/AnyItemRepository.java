package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository {

	@Query("select i from Item i")
	Collection<Item> findMany();
	@Query("select i from Item i WHERE i.itemType = 0 and i.published= 1")
	Collection<Item> findManyToolsPublished();
	
	@Query("select i from Item i WHERE i.itemType = 1 and i.published= 1 ")
	Collection<Item> findManyComponentsPublished();
	
	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);
	
	@Query("select q.item from Quantity q WHERE q.toolkit.id = :toolkitId and q.item.itemType = 0")
	Collection<Item> findToolsByToolkit(int toolkitId);
	
	
	@Query("select q.item from Quantity q WHERE q.toolkit.id = :toolkitId and q.item.itemType = 1")
	Collection<Item> findComponentByToolkit(int toolkitId);
	
	
	
	
	
}
