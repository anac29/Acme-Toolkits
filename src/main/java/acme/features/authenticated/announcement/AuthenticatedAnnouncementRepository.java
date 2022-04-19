package acme.features.authenticated.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcement.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository {

	@Query("SELECT a FROM Announcement a WHERE a.creationMoment > :deadline")
	Collection<Announcement> findRecentAnnouncements(Date deadline);
	
	@Query("SELECT a FROM Announcement a WHERE a.id = :id")
	Announcement findOneAnnouncementById(int id);
}
