package acme.features.any.useraccount;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository{

	@Query("SELECT ua FROM UserAccount ua WHERE ua.id = :id")
	UserAccount findOneUserAccountById(int id);
}
