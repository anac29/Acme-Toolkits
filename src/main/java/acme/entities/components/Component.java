package acme.entities.components;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import acme.entities.toolkits.Toolkit;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Component extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank(message = "name is mandatory")
	@Length(min = 0, max = 101)
	protected String name;

	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;

	@NotBlank(message = "technology is mandatory")
	@Length(min = 0, max = 101)
	protected String technology;

	@NotBlank(message = "description is mandatory")
	@Length(min = 0, max = 256)
	protected String description;

	protected Money retailPrice;

	@URL
	protected String info;

	// Relationships -------------------------------------------------------------

	@ManyToOne(optional = false)
	protected Toolkit toolkit;

}
