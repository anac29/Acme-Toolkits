package acme.entities.toolkits;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank()
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;

	@Length(min = 1, max = 100)
	@NotBlank
	protected String title;

	@Length(min = 1, max = 255)
	@NotBlank
	protected String description;

	@Length(min = 1, max = 255)
	@NotBlank
	protected String assemblyNotes;

	@URL
	protected String link;
	
	

}
