package org.jboss.examples.ticketmonster.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.jboss.examples.ticketmonster.model.Event;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.jboss.examples.ticketmonster.model.Venue;

/**
 * <p>
 * A show is an instance of an event taking place at a particular venue. A show can have multiple performances.
 * </p>
 */
@Entity
@Table(name = "Appearance", uniqueConstraints = @UniqueConstraint(columnNames = {"event_id", "venue_id"}))
public class Show {
	/* Declaration of fields */
	/**
	 * The synthetic id of the object.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * <p>
	 * The event of which this show is an instance. The <code>@ManyToOne<code> JPA mapping establishes this relationship.
	 * </p>
	 *
	 * <p>
	 * The <code>@NotNull</code> Bean Validation constraint means that the event must be specified.
	 * </p>
	 */
	@ManyToOne
	@NotNull
	private Event event;

	/**
	* <p>
	* The venue where this show takes place. The <code>@ManyToOne<code> JPA mapping establishes this relationship.
	* </p>
	*
	* <p>
	* The <code>@NotNull</code> Bean Validation constraint means that the venue must be specified.
	* </p>
	*/
	@ManyToOne
	@NotNull
	private Venue venue;

	/* Boilerplate getters and setters */
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	/* toString(), equals() and hashCode() for Show, using the natural identity of the
	object */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Show show = (Show) o;
		if (event != null ? !event.equals(show.event) : show.event != null)
			return false;
		if (venue != null ? !venue.equals(show.venue) : show.venue != null)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		int result = event != null ? event.hashCode() : 0;
		result = 31 * result + (venue != null ? venue.hashCode() : 0);
		return result;
	}
	@Override
	public String toString() {
		return event + " at " + venue;
	}

	public void setEvent(final Event event) {
		this.event = event;
	}

	public Venue getVenue() {
		return this.venue;
	}

	public void setVenue(final Venue venue) {
		this.venue = venue;
	}
}