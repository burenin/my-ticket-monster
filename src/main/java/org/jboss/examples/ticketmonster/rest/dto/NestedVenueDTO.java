package org.jboss.examples.ticketmonster.rest.dto;

import java.io.Serializable;
import org.jboss.examples.ticketmonster.model.Venue;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.jboss.examples.ticketmonster.rest.dto.AddressDTO;
public class NestedVenueDTO implements Serializable {

	private Long id;
	private String name;
	private String description;
	private int capacity;
	private AddressDTO address;

	public NestedVenueDTO() {
	}

	public NestedVenueDTO(final Venue entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.name = entity.getName();
			this.description = entity.getDescription();
			this.capacity = entity.getCapacity();
			this.address = new AddressDTO(entity.getAddress());
		}
	}

	public Venue fromDTO(Venue entity, EntityManager em) {
		if (entity == null) {
			entity = new Venue();
		}
		if (this.id != null) {
			TypedQuery<Venue> findByIdQuery = em.createQuery(
					"SELECT DISTINCT v FROM Venue v WHERE v.id = :entityId",
					Venue.class);
			findByIdQuery.setParameter("entityId", this.id);
			try {
				entity = findByIdQuery.getSingleResult();
			} catch (javax.persistence.NoResultException nre) {
				entity = null;
			}
			return entity;
		}
		entity.setName(this.name);
		entity.setDescription(this.description);
		entity.setCapacity(this.capacity);
		if (this.address != null) {
			entity.setAddress(this.address.fromDTO(entity.getAddress(), em));
		}
		entity = em.merge(entity);
		return entity;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(final int capacity) {
		this.capacity = capacity;
	}

	public AddressDTO getAddress() {
		return this.address;
	}

	public void setAddress(final AddressDTO address) {
		this.address = address;
	}
}