package acme.tool;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

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

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Name is mandatory")
	@Length(max = 101)
	private String name;
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	private String code;
	
	@NotBlank(message = "Technology is mandatory")
	@Length(max = 101)
	private String technology;
	
	@NotBlank(message = "Description is mandatory")
	@Length(max = 256)
	private String description;
	
	@Min(0)
	private Money retailPrice;
	
	@URL
	private String moreInfo;
}
