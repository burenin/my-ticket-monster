package org.jboss.examples.ticketmonster.test.rest;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.examples.ticketmonster.rest.BookingService;
import org.jboss.examples.ticketmonster.rest.ShowService;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BookingServiceTest {

	@Deployment
	public static WebArchive deployment() {
		return RESTDeployment.deployment();
	}
	
	@Inject
	private BookingService bookingService;
	
	@Inject
	private ShowService	showService;
	
	
}
