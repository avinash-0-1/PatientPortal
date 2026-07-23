package org.cerner.patientportal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.cerner.patientportal.bean.Patient;
import org.cerner.patientportal.services.PatientServices;

import com.google.gson.Gson;

/**
 * Date:1/31/2018
 * This is control class from here the server will run.
 * @author KK055944
 * version:0.0.1
 *
 */
/**
 * Root resource (exposed at "patients" path)
 */
@Path("/patients")
public class PatientController {
	PatientServices service = new PatientServices();
	String message;

	static Logger logger = Logger.getLogger(PatientController.class);

	/**
	 * Method handles HTTP POST requests. This method will takes data as json format
	 * and sent that object to @Services method.
	 * 
	 * @param data:It
	 *            ll take the data from UI and sent it calling methods to do
	 *            actions.
	 * @throws Exception
	 */
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createPatient(Patient data) throws Exception {
		logger.info("hit the Post request in server with: " + data);
		// If data is present then only it will call the services function.
		if (data != null)
			message = service.registerPatientData(data);
		else
			// Or else the appropriate message will be sent
			message = "Sorry Unable to store the data";
		// This below line of code will get the key value of that message so that it is
		// used to do i18n.
		message = setMessageKey(message);
		return message;
	}

	/**
	 * @param message
	 * @return the key of appropriate message to front end java controller.
	 * @throws IOException
	 */
	private String setMessageKey(String message) throws IOException {
		InputStream inputStream = null;
		inputStream = this.getClass().getClassLoader().getResourceAsStream("message.properties");
		Properties property = new Properties();
		property.load(inputStream);
		String key = null;
		if (message.equals("Sorry Unable to store the data")) {
			key = property.getProperty("NullValuesFromFrontEnd");
		} else if (message.equals("Invalid Data!")) {
			key = property.getProperty("invalidData");
		} else if (message.equals("Already patient details has been registerd!")) {
			key = property.getProperty("DuplicateFound");
		} else if (message.equals("Please enter value to store as You forgot to give!")) {
			key = property.getProperty("MissingValues");
		} else if (message.equals("OOPS! Cant connect to database server!")) {
			key = property.getProperty("ConnectionMessage");
		} else {
			property.setProperty("Success", "SuccessMessage");
			key = property.getProperty("Success");
		}
		return key;
	}

	/**
	 * Method handles HTTP GET requests. This method will takes parameters sent from
	 * client and calls searchPatients service
	 * 
	 * @param patientId
	 * @param firstName
	 * @param lastName
	 * @param dateOfBirth
	 * @return list of the patients to the client
	 * @throws Exception
	 */
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchPatient(@QueryParam("patientId") int patientId, @QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName, @QueryParam("dateOfBirth") String dateOfBirth) throws Exception {
		List<Patient> patientData = null;
		Patient searchValue = new Patient(firstName, lastName, dateOfBirth, patientId);
		String searchResponse = null;
		try {
			logger.debug("hit the Get request in server");
			patientData = service.searchPatients(searchValue);
			Gson gson = new Gson();
			searchResponse = gson.toJson(patientData).toString();
			logger.info("Sending the data to the client- " + searchResponse);
		} catch (Exception e) {
			logger.debug("Caught exception" + e.getMessage());
			searchResponse = e.getMessage();
		}
		return searchResponse;
	}
}