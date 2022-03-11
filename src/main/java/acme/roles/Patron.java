package acme.roles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import acme.framework.roles.UserRole;

public class Patron extends UserRole {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Size(min=1,max=101)
	protected String			company;

	@NotBlank
	@Size(min=1,max=256)
	protected String			statement;
	
	protected String			link;
}

