/*
 * MoneyExchange.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.forms;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyExchange {

	// Query attributes -------------------------------------------------------

	@NotNull
	@Valid
	public Money	source;

	@NotBlank
	public String	targetCurrency;

	// Response attributes ----------------------------------------------------

	@Valid
	public Money	target;

	public Date		date;

}
