package acme.currencies;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Currency extends AbstractEntity {

	protected static final long serialVersionUID = 1L;
	@NotNull
	@Digits(integer=Integer.MAX_VALUE, fraction=2)
	private Double amount;
	@NotNull
	private CurrencyType currencyType;
}
