package acme.features.administrator.announcement;

import org.springframework.data.jpa.repository.Query;

import acme.entities.configuration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorAnnouncementRepository extends AbstractRepository{
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

}
