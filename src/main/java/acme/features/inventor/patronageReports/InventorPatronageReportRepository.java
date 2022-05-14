package acme.features.inventor.patronageReports;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findOnePatronageReportById(int id);
	
	@Query("select p from Patronage p")
	List<Patronage> findAllPatronages();
	
	@Query("SELECT p FROM Patronage p WHERE p.id = :id")
    Patronage findOnePatronageById(int id);
	
	@Query("select p from PatronageReport p where p.patronage.inventor.id = :id")
	Collection<PatronageReport> findManyPatronageReportsByInventorId(int id);

	@Query("SELECT p FROM Patronage p WHERE p.published = 1 and p.inventor.id = :id")
	Collection<Patronage> findAcceptedPatronagesByInventorId(int id);
	
	@Query("select inventor from Inventor inventor WHERE inventor.id=:id")
    Inventor findInventorById(int id);
}
