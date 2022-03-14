package acme.entities.patronage;

import java.time.Period;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Patronage extends AbstractEntity
{
	private PatronageStatus status;
	
	protected static final long serialVersionUID = 1L;
	
	@Column(unique=true)
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	private String code;
	
	@Length(min=0,max=256)
	@NotBlank(message="legal stuff is mandatory")
	@NotNull
	protected String legalStuff;
	
	@Min(0)
	protected Integer budget;
	
	@URL
	protected String info;
	
	protected Period period;
	
	@ManyToOne
	protected Inventor inventor;
	
	@ManyToOne
	protected Patron patron;
	
}
