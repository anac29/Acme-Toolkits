package acme.features.patron.patronage;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("SELECT p FROM Patronage p WHERE p.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("SELECT p FROM Patronage p WHERE p.code LIKE :code")
	Patronage findOnePatronageByCode(String code);
	
	@Query("SELECT p FROM Patronage p WHERE p.patron.id = :patronid")
	Collection<Patronage> findMyPatronages(Integer patronid);
	
    @Query("select i from Inventor i")
    Collection<Inventor> findInventors();

    @Query("select inventor from Inventor inventor WHERE inventor.id=:id")
    Optional<Inventor> findInventorById(int id);
	
    @Query("select patron from Patron patron WHERE patron.id=:id")
    Optional<Patron> findPatronById(int id);
    
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select c.defaultCurrency from SystemConfiguration c")
	String defaultCurrency();
	
	@Query("select patronageReport from  PatronageReport patronageReport where patronageReport.patronage.id = :patronageId")
	Collection<PatronageReport> findManyPatronageReportsByPatronageId(int patronageId);
}


