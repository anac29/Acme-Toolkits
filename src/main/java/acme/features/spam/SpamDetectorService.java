package acme.features.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.spam_detector.SpamDetector;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;

@Service
public class SpamDetectorService {

	@Autowired
	private SpamDetectorRepository repo;
	
	public boolean isSpam(final String text) {
		final SystemConfiguration sc = this.repo.findSystemConfiguration();
		final SpamDetector sd = new SpamDetector(sc.getStrongSpamTerms(), sc.getWeakSpamTerms(), sc.getStrongThreshold(), sc.getWeakThreshold());
		
		return sd.isSpam(text);
	}
}
