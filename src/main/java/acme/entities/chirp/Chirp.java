package acme.entities.chirp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Chirp extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------


	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationMoment;

	@NotBlank(message = "Title is mandatory")
	@Length(min = 0, max = 101)
	protected String title;

	@NotBlank(message = "Author is mandatory")
	@Length(min = 0, max = 101)
	protected String author;

	@NotBlank(message = "Body is mandatory")
	@Length(min = 0, max = 256)
	protected String body;

	@Email
	protected String email;
}