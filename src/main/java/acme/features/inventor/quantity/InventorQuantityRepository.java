package acme.features.inventor.quantity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
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
	
	@Query("SELECT i FROM Item i WHERE i.itemType = :type AND i.published = true")
	List<Item> findItemByType(ItemType type);
	
	@Query("SELECT q FROM Quantity q WHERE q.item.id = :id and q.toolkit.id = :tid")
	Quantity findQuantityTool(int tid, int id);
	
	@Query("SELECT q FROM Quantity q")
	List<Quantity> findQuantities();
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :toolkitId AND q.item.itemType = :type")
	List<Quantity> findAllQuantitiesByToolkitAndType(int toolkitId, ItemType type);
	
	@Query("SELECT count(i) FROM Item i WHERE i.itemType = :type AND i.published = true AND i NOT IN (SELECT q.item FROM Quantity q WHERE q.toolkit.id = :toolkitId AND q.item.itemType = :type)")
	Long findItemsLeftByToolkitAndType(int toolkitId, ItemType type);
	
	@Query("SELECT q.item FROM Quantity q WHERE q.toolkit.id = :toolkitId AND q.item.itemType = :type")
	List<Item> findItemByToolkitAndType(int toolkitId, ItemType type);
}
