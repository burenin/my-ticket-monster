package org.jboss.examples.ticketmonster.rest.dto;

import java.io.Serializable;
import org.jboss.examples.ticketmonster.model.Performance;
import javax.persistence.EntityManager;
import java.util.Date;
import org.jboss.examples.ticketmonster.rest.dto.NestedShowDTO;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class PerformanceDTO implements Serializable {

	private Long id;
	private Date date;
	private NestedShowDTO show;
	private String displayTitle;

	public PerformanceDTO() {
	}

	public PerformanceDTO(final Performance entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.date = entity.getDate();
			this.show = new NestedShowDTO(entity.getShow());
			this.displayTitle = entity.toString();
		}
	}

	public Performance fromDTO(Performance entity, EntityManager em) {
		if (entity == null) {
			entity = new Performance();
		}
		entity.setDate(this.date);
		if (this.show != null) {
			entity.setShow(this.show.fromDTO(entity.getShow(), em));
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public NestedShowDTO getShow() {
		return this.show;
	}

	public void setShow(final NestedShowDTO show) {
		this.show = show;
	}

	public String getDisplayTitle() {
		return displayTitle;
	}
}