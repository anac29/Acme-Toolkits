package acme.features.inventor.quantity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorQuantityRepository extends AbstractRepository 
{
	@Query("SELECT t FROM Toolkit t WHERE t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("SELECT t FROM Item t WHERE t.id = :id")
	Item findItemById(Integer id);
	
	@Query("SELECT t FROM Item t WHERE t.itemType = 0 and t.published = true")
	List<Item> findTools();
	
	@Query("SELECT t FROM Item t WHERE t.itemType = 1 and t.published = true")
	List<Item> findComponents();
	
	@Query("select q.item from Quantity q WHERE q.toolkit.id = :toolkitId and q.item.itemType = 0")
	List<Item> findToolsByToolkit(int toolkitId);
	
	@Query("select q.item from Quantity q WHERE q.toolkit.id = :toolkitId and q.item.itemType = 1")
	List<Item> findComponentsByToolkit(int toolkitId);

	@Query("SELECT q FROM Quantity q WHERE q.item.id = :id and q.toolkit.id = :tid")
	Quantity findQuantityTool(int tid,int id);
	
	@Query("SELECT q FROM Quantity q")
	List<Quantity> findQuantities();
}
