package acme.entities.patronagereports;

import java.util.Date;

import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.patronage.Patronage;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronageReport extends AbstractEntity{

	protected static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationMoment;
	
	protected String code = this.getPatronage().getCode() + ":0001";
	
	@NotBlank(message="memorandum is mandatory")
	@NotNull
	@Length(min=0,max=256)
	protected String memorandum;
	
	@URL
	protected String info;
	
	@OneToOne
	protected Patronage patronage;
}
