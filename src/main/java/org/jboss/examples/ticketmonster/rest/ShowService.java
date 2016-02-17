package org.jboss.examples.ticketmonster.rest;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

import org.jboss.examples.ticketmonster.model.Show;

/**
* <p>
* A JAX-RS endpoint for handling {@link Show}s. Inherits the actual
* methods from {@link BaseEntityService}.
* </p>
*/
@Path("/show")
/**
* <p>
* This is a stateless service, so a single shared instance can be used in this case.
* </p>
*/
@Stateless
public class ShowService extends BaseEntityService<Show>{
	
	public ShowService(){
		super(Show.class);
	}
}