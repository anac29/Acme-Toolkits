package acme.features.authenticated.configuration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedConfigurationRepository extends AbstractRepository{
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

}
