package org.jboss.examples.ticketmonster.rest.dto;

import java.io.Serializable;
import org.jboss.examples.ticketmonster.model.Show;
import javax.persistence.EntityManager;
import org.jboss.examples.ticketmonster.rest.dto.NestedEventDTO;
import org.jboss.examples.ticketmonster.rest.dto.NestedVenueDTO;
import java.util.Set;
import java.util.HashSet;
import org.jboss.examples.ticketmonster.rest.dto.NestedPerformanceDTO;
import org.jboss.examples.ticketmonster.model.Performance;
import java.util.Iterator;
import org.jboss.examples.ticketmonster.rest.dto.NestedTicketPriceDTO;
import org.jboss.examples.ticketmonster.model.TicketPrice;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ShowDTO implements Serializable {

	private Long id;
	private NestedEventDTO event;
	private NestedVenueDTO venue;
	private Set<NestedPerformanceDTO> performances = new HashSet<NestedPerformanceDTO>();
	private Set<NestedTicketPriceDTO> ticketPrices = new HashSet<NestedTicketPriceDTO>();
	private String displayTitle;


	public ShowDTO() {
	}

	public ShowDTO(final Show entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.event = new NestedEventDTO(entity.getEvent());
			this.venue = new NestedVenueDTO(entity.getVenue());
			this.displayTitle = entity.toString();
			Iterator<Performance> iterPerformances = entity.getPerformances()
					.iterator();
			while (iterPerformances.hasNext()) {
				Performance element = iterPerformances.next();
				this.performances.add(new NestedPerformanceDTO(element));
			}
			Iterator<TicketPrice> iterTicketPrices = entity.getTicketPrices()
					.iterator();
			while (iterTicketPrices.hasNext()) {
				TicketPrice element = iterTicketPrices.next();
				this.ticketPrices.add(new NestedTicketPriceDTO(element));
			}
		}
	}

	public Show fromDTO(Show entity, EntityManager em) {
		if (entity == null) {
			entity = new Show();
		}
		if (this.event != null) {
			entity.setEvent(this.event.fromDTO(entity.getEvent(), em));
		}
		if (this.venue != null) {
			entity.setVenue(this.venue.fromDTO(entity.getVenue(), em));
		}
		Iterator<Performance> iterPerformances = entity.getPerformances()
				.iterator();
		while (iterPerformances.hasNext()) {
			boolean found = false;
			Performance performance = iterPerformances.next();
			Iterator<NestedPerformanceDTO> iterDtoPerformances = this
					.getPerformances().iterator();
			while (iterDtoPerformances.hasNext()) {
				NestedPerformanceDTO dtoPerformance = iterDtoPerformances
						.next();
				if (dtoPerformance.getId().equals(performance.getId())) {
					found = true;
					break;
				}
			}
			if (found == false) {
				iterPerformances.remove();
			}
		}
		Iterator<NestedPerformanceDTO> iterDtoPerformances = this
				.getPerformances().iterator();
		while (iterDtoPerformances.hasNext()) {
			boolean found = false;
			NestedPerformanceDTO dtoPerformance = iterDtoPerformances.next();
			iterPerformances = entity.getPerformances().iterator();
			while (iterPerformances.hasNext()) {
				Performance performance = iterPerformances.next();
				if (dtoPerformance.getId().equals(performance.getId())) {
					found = true;
					break;
				}
			}
			if (found == false) {
				Iterator<Performance> resultIter = em
						.createQuery("SELECT DISTINCT p FROM Performance p",
								Performance.class).getResultList().iterator();
				while (resultIter.hasNext()) {
					Performance result = resultIter.next();
					if (result.getId().equals(dtoPerformance.getId())) {
						entity.getPerformances().add(result);
						break;
					}
				}
			}
		}
		Iterator<TicketPrice> iterTicketPrices = entity.getTicketPrices()
				.iterator();
		while (iterTicketPrices.hasNext()) {
			boolean found = false;
			TicketPrice ticketPrice = iterTicketPrices.next();
			Iterator<NestedTicketPriceDTO> iterDtoTicketPrices = this
					.getTicketPrices().iterator();
			while (iterDtoTicketPrices.hasNext()) {
				NestedTicketPriceDTO dtoTicketPrice = iterDtoTicketPrices
						.next();
				if (dtoTicketPrice.getId().equals(ticketPrice.getId())) {
					found = true;
					break;
				}
			}
			if (found == false) {
				iterTicketPrices.remove();
			}
		}
		Iterator<NestedTicketPriceDTO> iterDtoTicketPrices = this
				.getTicketPrices().iterator();
		while (iterDtoTicketPrices.hasNext()) {
			boolean found = false;
			NestedTicketPriceDTO dtoTicketPrice = iterDtoTicketPrices.next();
			iterTicketPrices = entity.getTicketPrices().iterator();
			while (iterTicketPrices.hasNext()) {
				TicketPrice ticketPrice = iterTicketPrices.next();
				if (dtoTicketPrice.getId().equals(ticketPrice.getId())) {
					found = true;
					break;
				}
			}
			if (found == false) {
				Iterator<TicketPrice> resultIter = em
						.createQuery("SELECT DISTINCT t FROM TicketPrice t",
								TicketPrice.class).getResultList().iterator();
				while (resultIter.hasNext()) {
					TicketPrice result = resultIter.next();
					if (result.getId().equals(dtoTicketPrice.getId())) {
						entity.getTicketPrices().add(result);
						break;
					}
				}
			}
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

	public NestedEventDTO getEvent() {
		return this.event;
	}

	public void setEvent(final NestedEventDTO event) {
		this.event = event;
	}

	public NestedVenueDTO getVenue() {
		return this.venue;
	}

	public void setVenue(final NestedVenueDTO venue) {
		this.venue = venue;
	}

	public Set<NestedPerformanceDTO> getPerformances() {
		return this.performances;
	}

	public void setPerformances(final Set<NestedPerformanceDTO> performances) {
		this.performances = performances;
	}

	public Set<NestedTicketPriceDTO> getTicketPrices() {
		return this.ticketPrices;
	}

	public void setTicketPrices(final Set<NestedTicketPriceDTO> ticketPrices) {
		this.ticketPrices = ticketPrices;
	}

	public String getDisplayTitle() {
		return displayTitle;
	}
}