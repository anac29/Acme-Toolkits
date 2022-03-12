package acme.components;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.currencies.Currency;
import acme.framework.entities.AbstractEntity;
import acme.toolkits.Toolkit;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Component extends AbstractEntity {
	
	protected static final long serialVersionUID = 1L;
	
	
	@NotBlank(message="name is mandatory")
	@Range(min=0,max=101)
	@NotNull
	private String name;
	
	
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@NotNull
	private String code;
	
	
	@NotBlank(message="tecnology is mandatory")
	@Range(min=0,max=101)
	@NotNull
	private String tecnology;
	
	@NotBlank(message="description is mandatory")
	@Range(min=0,max=256)
	@NotNull
	private String description;
	
	@Min(0)
	@NotNull
	private Currency retailPrice;
	
	@URL
	private String link;
	
	
	@ManyToOne(optional=false)
	private Toolkit toolkit;
	
	
	
	
	
	

}
