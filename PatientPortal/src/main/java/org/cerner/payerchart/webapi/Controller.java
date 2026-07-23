package org.cerner.payerchart.webapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/patients")
public class Controller {
	
	String message="";
	
	@GET
	@Path("/fhirurl")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFhirData() throws Exception {
		/*
		 * create property class for fhir url
		 * call the url
		 */
		return message;
	}
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertData() throws Exception {
		String hash="";
		/*
		 * create property class for insert url and fetching url
		 * call the insert url by passing message received in fhir function
		 * call the fetch hash url
		 */
		return hash;
	}
}
