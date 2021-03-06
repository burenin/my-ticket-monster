package org.jboss.examples.ticketmonster.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.examples.ticketmonster.rest.VenueService;
import javax.inject.Inject;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {

	/**
	 * Alias the persistence context
	 */
	@Produces
	@PersistenceContext
	private EntityManager em;

	/**
	 * Provider injectable loggers based around Java Util Logging.
	 * @param injectionPoint
	 * @return
	 */
	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
				.getName());
	}

}
