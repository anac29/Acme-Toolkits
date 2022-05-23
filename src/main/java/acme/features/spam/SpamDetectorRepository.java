package acme.features.spam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;

@Repository
public interface SpamDetectorRepository extends CrudRepository<SystemConfiguration, Integer>{

	@Query("SELECT sc FROM SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
}
