package acme.entities.tool;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
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
@Table(name = "tools", 
		uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class Tool extends AbstractEntity{

	protected static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Name is mandatory")
	@Length(max = 101)
	protected String name;
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank(message = "Technology is mandatory")
	@Length(max = 101)
	protected String technology;
	
	@NotBlank(message = "Description is mandatory")
	@Length(max = 256)
	protected String description;
	

	protected Money retailPrice;

	
	@URL
	protected String info;
	
	@OneToOne(optional=false)
	protected Toolkit toolkit;
	
}
