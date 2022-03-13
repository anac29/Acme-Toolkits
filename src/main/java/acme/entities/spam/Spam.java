package acme.entities.spam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Spam extends AbstractEntity {

    /**
     * 
     */
    protected static final long serialVersionUID = 1L;

    @NotBlank(message = "Name is mandatory")
    @Column(unique=true)
    protected String name;

    protected SpamType type;
}
