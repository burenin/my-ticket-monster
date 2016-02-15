package org.jboss.examples.ticketmonster.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.jboss.examples.ticketmonster.model.MediaItem;
import javax.persistence.ManyToOne;
@Entity
public class Event implements Serializable {
	/* Declaration of fields */
	/**
	 * The synthetic ID of the object.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	/**
	 * <p>
	 * The name of the event.
	 * </p>
	 *
	 * <p>
	 * The name of the event forms it's natural identity and cannot be shared between events.
	 * </p>
	Ticket Monster Tutorial 121 / 353
	 *
	 * <p>
	 * Two constraints are applied using Bean Validation
	 * </p>
	 *
	 * <ol>
	 * <li><code>@NotNull</code> &mdash; the name must not be null.</li>
	 * <li><code>@Size</code> &mdash; the name must be at least 5 characters and no more
	 * than 50 characters. This allows for
	 * better formatting consistency in the view layer.</li>
	 * </ol>
	 */
	@Column(unique = true)
	@NotNull
	@Size(min = 5, max = 50, message = "An event's name must constrain between 5 and 50 characters")
	private String name;

	/**
	 * <p>
	 * A description of the event.
	 * </p>
	 *
	 * <p>
	 * Two constraints are applied using Bean Validation
	 * </p>
	 *
	 * <ol>
	 * <li><code>@NotNull</code> &mdash; the description must not be null.</li>
	 * <li><code>@Size</code> &mdash; the name must be at least 20 characters and no more
	than 1000 characters. This allows for
	 * better formatting consistency in the view layer, and also ensures that event
	organisers provide at least some description
	 * - a classic example of a business constraint.</li>
	 * </ol>
	 */
	@Column
	@NotNull
	@Size(min = 20, max = 1000, message = "An event's description must contain between 20 and 1000 characters")
	private String description;

	@ManyToOne
	private MediaItem mediaItem;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	/* toString(), equals() and hashCode() for Event, using the natural identity of the
	object */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Event event = (Event) o;
		if (name != null ? !name.equals(event.name) : event.name != null)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MediaItem getMediaItem() {
		return this.mediaItem;
	}

	public void setMediaItem(final MediaItem mediaItem) {
		this.mediaItem = mediaItem;
	}

}