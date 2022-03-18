package acme.entities.patronage;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import acme.datatypes.Period;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	private PatronageStatus status;

	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	private String code;

	@Length(min = 0, max = 256)
	@NotBlank(message = "legal stuff is mandatory")
	@NotNull
	protected String legalStuff;

	protected Money budget;

	@URL
	protected String info;

	protected Period period;

	// Relationships -------------------------------------------------------------

	@ManyToOne
	protected Inventor inventor;

	@ManyToOne
	protected Patron patron;

}
