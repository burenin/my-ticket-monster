package org.jboss.examples.ticketmonster.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class EventCategory implements Serializable {

	private static final long serialVersionUID = -5541334231050173852L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	/**
	* <p>
	* The description of the of event category.
	* </p>
	*
	* <p>
	* The description forms the natural id of the event category, and so must be unique.
	* </p>
	* 
	* 
	* <p>
	* The description must not be null and must be one or more characters, the Bean
	* Validation constraint <code>@NotEmpty</code>
	* enforces this.
	* </p>
	*
	*/
	@Column(unique = true)
	@NotEmpty
	private String description;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventCategory other = (EventCategory) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
}