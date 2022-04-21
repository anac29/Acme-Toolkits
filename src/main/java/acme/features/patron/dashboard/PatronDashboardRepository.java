package acme.features.patron.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {
	
	@Query("SELECT status,count(p) FROM Patronage p WHERE p.patron.id = :patronId GROUP BY p.status")
	List<Object[]> findTotalNumberOfPatronagesByStatus(int patronId);
	
	@Query("SELECT p.budget.currency,p.status,avg(p.budget.amount),stddev(p.budget.amount),min(p.budget.amount),max(p.budget.amount) FROM Patronage p WHERE p.patron.id = :patronId GROUP BY p.budget.currency,p.status")
	List<Object[]> findOtherMetrics(int patronId);

}
