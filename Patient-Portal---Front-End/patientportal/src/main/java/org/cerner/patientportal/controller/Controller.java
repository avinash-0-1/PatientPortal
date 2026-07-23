package org.cerner.patientportal.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.cerner.patientportal.entity.Patient;

import com.google.gson.Gson;

import util.DateFormatter;

@Path("/patient")
public class Controller {
	String output, response_message;
	static Logger logger = Logger.getLogger(Controller.class);
	HttpURLConnection connection;
	BufferedReader reader;
	DateFormatter formatter = new DateFormatter();
	Properties urlProperty = new Properties();
	InputStream input = null;
	String propFile = "serverurl.properties";

	@Path("/register")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getPostData(Patient data) throws Exception {
		Controller create = new Controller();
		try {
			logger.info("Hit client register controller");

			data.setDateOfBirth(formatter.formatDate(data.getDateOfBirth()));

			input = Controller.class.getClassLoader().getResourceAsStream(propFile);
			urlProperty.load(input);

			String registerUrl = urlProperty.getProperty("registerUrl");

			URL url = new URL(registerUrl);

			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			Gson gson = new Gson();
			String input = gson.toJson(data);

			logger.debug("Sending data to server: " + input);

			OutputStream outStream = connection.getOutputStream();
			outStream.write(input.getBytes());
			outStream.flush();

			reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			while ((output = reader.readLine()) != null) {
				response_message = output;
			}

		} catch (FileNotFoundException e) {

			response_message = e.getMessage();

		} catch (MalformedURLException e) {

			response_message = e.getMessage();

		} catch (IOException e) {

			response_message = e.getMessage();

		} catch (Exception e) {

			response_message = e.getMessage();

		} finally {
			connection.disconnect();
		}
		response_message = create.internationalisedMessage(response_message);
		return response_message;
	}

	@Path("/search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getSearchData(@QueryParam("patientId") int patientId, @QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName, @QueryParam("dateOfBirth") String dateOfBirth) throws IOException {

		Controller create = new Controller();
		logger.info("Hit the search controller");
		String response = null;
		try {
			if (dateOfBirth != null) {
				dateOfBirth = formatter.formatDate(dateOfBirth);
			}
			URL myurl = new URL(create.createURL(firstName, lastName, patientId, dateOfBirth));
			connection = (HttpURLConnection) myurl.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			StringBuilder output = new StringBuilder();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				output.append(line);
			}
			reader.close();
			response = output.toString();
		} catch (Exception e) {
			response = e.getMessage();
		} finally {
			connection.disconnect();
		}
		return response;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param patientId
	 * @param dateOfBirth
	 * @return the string url of search a patient
	 */
	String createURL(String firstName, String lastName, int patientId, String dateOfBirth) throws Exception {
		input = Controller.class.getClassLoader().getResourceAsStream(propFile);
		urlProperty.load(input);
		String searchUrl = urlProperty.getProperty("searchUrl");

		if (firstName != null && (!firstName.equals(""))) {
			searchUrl += "&firstName=" + firstName;
		}
		if (lastName != null && (!lastName.equals(""))) {
			searchUrl += "&lastName=" + lastName;
		}
		if (dateOfBirth != null && (!dateOfBirth.equals(""))) {
			searchUrl += "&dateOfBirth=" + dateOfBirth;
		}
		if (patientId != 0) {
			searchUrl += "&patientId=" + patientId;
		}
		logger.info("The URL for get request is: " + searchUrl);
		return searchUrl;
	}

	public String internationalisedMessage(String message) {
		Locale defaultLocale = Locale.getDefault();
		ResourceBundle bundle;
		if (defaultLocale.getLanguage().equals("en")) {
			bundle = ResourceBundle.getBundle("DefaultBundle", defaultLocale);
			if (message.equals("DuplicateEntryMessage")) {
				message = bundle.getString("DuplicateEntryMessage");
			} else if (message.equals("NullMessage")) {
				message = bundle.getString("NullMessage");
			} else if (message.equals("MissingValueMessage")) {
				message = bundle.getString("MissingValueMessage");
			} else if (message.equals("ServerConnectionMessage")) {
				message = bundle.getString("ServerConnectionMessage");
			} else
				message = bundle.getString("SuccessMessage");
		} else if (defaultLocale.getLanguage().equals("fr")) {
			bundle = ResourceBundle.getBundle("bundle_FR", defaultLocale);
			if (message.equals("DuplicateEntryMessage")) {
				message = bundle.getString("DuplicateEntryMessage");
			} else if (message.equals("NullMessage")) {
				message = bundle.getString("NullMessage");
			} else if (message.equals("MissingValueMessage")) {
				message = bundle.getString("MissingValueMessage");
			} else if (message.equals("ServerConnectionMessage")) {
				message = bundle.getString("ServerConnectionMessage");
			} else
				message = bundle.getString("SuccessMessage");
		} else if (defaultLocale.getLanguage().equals("ms")) {
			bundle = ResourceBundle.getBundle("bundle_FR", defaultLocale);
			if (message.equals("DuplicateEntryMessage")) {
				message = bundle.getString("DuplicateEntryMessage");
			} else if (message.equals("NullMessage")) {
				message = bundle.getString("NullMessage");
			} else if (message.equals("MissingValueMessage")) {
				message = bundle.getString("MissingValueMessage");
			} else if (message.equals("ServerConnectionMessage")) {
				message = bundle.getString("ServerConnectionMessage");
			} else
				message = bundle.getString("SuccessMessage");
		} else {
			bundle = ResourceBundle.getBundle("bundle_AR", defaultLocale);
			if (message.equals("DuplicateEntryMessage")) {
				message = bundle.getString("DuplicateEntryMessage");
			} else if (message.equals("NullMessage")) {
				message = bundle.getString("NullMessage");
			} else if (message.equals("MissingValueMessage")) {
				message = bundle.getString("MissingValueMessage");
			} else if (message.equals("ServerConnectionMessage")) {
				message = bundle.getString("ServerConnectionMessage");
			} else
				message = bundle.getString("SuccessMessage");
		}
		return message;
	}
}