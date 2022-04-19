package acme.features.administrator.configuration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorConfigurationRepository extends AbstractRepository {

	@Query("select c from SystemConfiguration c")
	SystemConfiguration findSystemConfiguration();
}
