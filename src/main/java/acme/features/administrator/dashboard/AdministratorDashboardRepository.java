/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT count(i) FROM Item i WHERE i.itemType = 'COMPONENT'")
	Long totalNumberOfComponents();

	@Query("SELECT i.technology, i.retailPrice.currency ,avg(i.retailPrice.amount),stddev(i.retailPrice.amount),min(i.retailPrice.amount),max(i.retailPrice.amount) FROM Item i WHERE i.itemType = acme.entities.item.ItemType.COMPONENT GROUP BY  i.retailPrice.currency,  i.technology")
	List<Object[]> findMetricsComponentsByTechnologyCurrency();

	@Query("SELECT count(i) FROM Item i WHERE i.itemType = 'TOOL'")
	Long totalNumberOfTools();

	@Query("SELECT i.retailPrice.currency ,avg(i.retailPrice.amount),stddev(i.retailPrice.amount),min(i.retailPrice.amount),max(i.retailPrice.amount) FROM Item i WHERE i.itemType = acme.entities.item.ItemType.TOOL GROUP BY i.retailPrice.currency")
	List<Object[]> findMetricsToolsByCurrency();

	@Query("SELECT p.status,count(p) FROM Patronage p GROUP BY p.status")
	List<Object[]> totalNumberOfPatronagesByStatus();
	
	@Query("SELECT p.status, avg(p.budget.amount),stddev(p.budget.amount),min(p.budget.amount),max(p.budget.amount) FROM Patronage p GROUP BY p.status")
	List<Object[]> findMetricsPatronagesBudgetByStatus();



}
