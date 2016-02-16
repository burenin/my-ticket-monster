package org.jboss.examples.ticketmonster.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
* <p>
* A show is an instance of an event taking place at a particular venue. A show can have multiple performances.
* </p>
*
* <p>
* A show contains a set of performances, and a set of ticket prices for each section of the venue for this show.
* </p>
*
* <p>
* The event and venue form the natural id of this entity, and therefore must be unique. JPA requires us to use the class level
* <code>@Table</code> constraint.
* </p>
*
*/
/*
* We suppress the warning about not specifying a serialVersionUID, as we are still developing this app, and want the JVM to
* generate the serialVersionUID for us. When we put this app into production, we'll generate and embed the serialVersionUID
*/
@Entity
@Table(name = "Appearance", uniqueConstraints = @UniqueConstraint(columnNames = {
		"event_id", "venue_id"}))
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

	/**
	* <p>
	* The set of performances of this show.
	* </p>
	*
	* <p>
	* The <code>@OneToMany<code> JPA mapping establishes this relationship. Collection members
	* are fetched eagerly, so that they can be accessed even after the entity has become detached.
	* This relationship is bi-directional (a performance knows which show it is part of), and the <code>mappedBy</code>
	* attribute establishes this.
	* </p>
	*
	*/
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "show", cascade = CascadeType.ALL)
	@OrderBy("date")
	private Set<Performance> performances = new HashSet<Performance>();
	
	/**
	* <p>
	* The set of ticket prices available for this show.
	* </p>
	*
	* <p>
	* The <code>@OneToMany<code> JPA mapping establishes this relationship.
	* This relationship is bi-directional (a ticket price category knows which show it is part of), and the <code>mappedBy</code>
	* attribute establishes this. We cascade all persistence operations to the set of performances, so, for example if a show
	* is removed, then all of it's ticket price categories are also removed.
	* </p>
	*/
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TicketPrice> ticketPrices = new HashSet<TicketPrice>();
	
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
	public void setEvent(final Event event) {
		this.event = event;
	}

	public Venue getVenue() {
		return this.venue;
	}

	public void setVenue(final Venue venue) {
		this.venue = venue;
	}

	public Set<Performance> getPerformances() {
		return performances;
	}

	public void setPerformances(Set<Performance> performances) {
		this.performances = performances;
	}
	
	public Set<TicketPrice> getTicketPrices() {
		return ticketPrices;
	}
	public void setTicketPrices(Set<TicketPrice> ticketPrices) {
		this.ticketPrices = ticketPrices;
	}
}