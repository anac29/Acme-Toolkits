package acme.entities.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// ------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	// ------------------------------------------------------------------
	
	@NotNull
	protected String strongSpamTerms;
	
	@NotNull
	protected String weakSpamTerms;
	
	@NotBlank
	protected String acceptedCurrencies;
	
	@NotBlank
	protected String defaultCurrency;
	
	@Range(min=0,max=1)
	protected double weakThreshold;
	
	@Range(min=0,max=1)
	protected double strongThreshold;
	
}
