package acme.datatypes;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.datatypes.AbstractDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Period extends AbstractDatatype{
	
	protected static final long	serialVersionUID	= 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationMomentDate=Date.from(Instant.now());
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date finalMoment;

	@Override
	public String toString() {
		return creationMomentDate  +" "+ finalMoment;
	}
	
	
	
	

}
