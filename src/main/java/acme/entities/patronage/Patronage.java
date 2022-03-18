package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

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

	protected PatronageStatus status;

	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;

	@Length(min = 1, max = 255)
	@NotBlank()
	@NotNull
	protected String legalStuff;

	protected Money budget;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date creationMomentDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date startMomentDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date finalMomentDate;

	@URL
	protected String link;

	// Relationships -------------------------------------------------------------

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Inventor inventor;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Patron patron;

}
