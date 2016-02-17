package org.jboss.examples.ticketmonster.service;

import org.jboss.examples.ticketmonster.model.Seat;
import org.jboss.examples.ticketmonster.model.SectionAllocation;
import java.util.List;
public class AllocatedSeats {

	private final SectionAllocation sectionAllocation;

	private final List<Seat> seats;
	
	public AllocatedSeats(SectionAllocation sectionAllocation, List<Seat> seats) {
		this.sectionAllocation = sectionAllocation;
		this.seats = seats;
	}

	public SectionAllocation getSectionAllocation() {
		return sectionAllocation;
	}

	public List<Seat> getSeats() {
		return seats;
	}
	
	public void markOccupied() {
        sectionAllocation.markOccupied(seats);
    }
}