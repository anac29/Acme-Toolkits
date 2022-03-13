package acme.entities.toolkits;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity {
	
	protected static final long serialVersionUID = 1L;
	
	@NotNull
	@NotBlank(message="Code ismandatory")
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	private String code;
	
	@Range(min=0, max=101)
	@NotBlank
	@NotNull
	private String title;
	
	@Range(min=0, max=256)
	@NotBlank
	@NotNull
	private String description;
	
	@Range(min=0, max=256)
	@NotBlank
	@NotNull
	private String assemblyNotes;
	
	@URL
	private String link;
	

}
