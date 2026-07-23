package org.cerner.patientportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cerner.patientportal.bean.Patient;

import util.DataBaseConnection;

/**
 * Date:1/31/2018 This is class which handles the register the data of patient
 * in database.
 * 
 * @author KK055944 version:0.0.1
 *
 */
/**
 * @author KK055944
 * @author RK056555
 *
 */
public class PatientDAO {
	Connection con = null;
	DataBaseConnection connection = new DataBaseConnection();
	PreparedStatement statement;
	static Logger logger = Logger.getLogger(PatientDAO.class);
	PatientDAO patient;

	public String registerPatient(Patient data) throws Exception {
		patient = new PatientDAO();
		String patientDataQuery = "insert into patient(FirstName,LastName,DateOfBirth,Gender,PhoneNo,Email_Id) values(?,?,?,?,?,?)";
		/**
		 * con variable will be declared with all the properties of database connection.
		 */
		con = connection.getConnection();
		String response = null;
		try {
			/**
			 * In this try block, It will execute the query of few details of patient. once
			 * it stored in database it will generate the PatientId.
			 * 
			 * @throws exceptions
			 *             if any.
			 */

			statement = con.prepareStatement(patientDataQuery);
			statement.setString(1, data.getFirstName());
			statement.setString(2, data.getLastName());
			statement.setString(3, data.getDateOfBirth());
			statement.setString(4, data.getGender());
			statement.setString(5, data.getPhoneNo());
			statement.setString(6, data.getEmailId());
			statement.executeUpdate();
			logger.info("successfully stored the patient details");
		} catch (SQLException e) {
			response = patient.getErrorMessage(e.getErrorCode());
		}
		return response;
	}

	/**
	 * This below statement as well as try block will get the latest patientId from
	 * the table as it is used to store the address in Address table in db.
	 * 
	 * @throws exceptions
	 *             if any.
	 */
	public int fetchPatientId() throws SQLException {
		con = connection.getConnection();
		int id = 0;
		String fetchId = "select max(PatientId) from patient";
		try {
			Statement stmt = con.createStatement();
			ResultSet id_set = stmt.executeQuery(fetchId);
			if (id_set.next()) {
				id = id_set.getInt(1);
			}
		} finally {
			con.close();
		}
		logger.info("successfully fetched the patient details" + id);
		return id;
	}

	/**
	 * This below statement and try block will store the patient address data into
	 * the table using PatientId of Patient table as reference.
	 * 
	 * @throws exceptions
	 *             if any.
	 */
	public String registerPatientAddress(Patient data) {
		String patientAddressQuery = "insert into address(PatientId,AddressLine,Street,City,State,Country) values(?,?,?,?,?,?)";
		String response = null;
		patient = new PatientDAO();
		Connection con = null;
		try {
			DataBaseConnection connection = new DataBaseConnection();
			/**
			 * connect variable will be declared with all the properties of database
			 * connection.
			 */
			int id = patient.fetchPatientId();
			con = connection.getConnection();
			PreparedStatement statement = con.prepareStatement(patientAddressQuery);
			statement.setInt(1, id);
			statement.setString(2, data.getAddressLine());
			statement.setString(3, data.getStreet());
			statement.setString(4, data.getCity());
			statement.setString(5, data.getState());
			statement.setString(6, data.getCountry());
			statement.executeUpdate();
			response = "Successfully registered the patient Details! Registration Id is:" + id;

			logger.info("Patient Address Stored");

		} catch (SQLException e) {
			logger.error("Caught an exception: " + e.getMessage());
			response = patient.getErrorMessage(e.getErrorCode());
		}
		return response;
	}

	/**
	 * @param errorCode
	 * @return the error response
	 */
	public String getErrorMessage(int errorCode) {
		String response = null;
		switch (errorCode) {
		case 6530:
			response = "Accessing empty values!";
			break;
		case 1062:
			response = "Already patient details has been registerd!";
			break;
		case 1049:
			response = "No such Database exists!";
			break;
		case 1048:
			response = "Please enter value to store as You forgot to give!";
			break;
		case 2002:
			response = "OOPS! Cant connect to database server! ";
			break;
		}
		return response;
	}

	/**
	 * the below method will execute the query to search the patient passed
	 * accordingly in param
	 * 
	 * @param data
	 * @return ResultSet of Patient's list
	 * @throws exceptions
	 *             if any.
	 */
	public List<Patient> getPatientList(Patient data) throws Exception {
		Connection con = null;
		ResultSet patientList = null;
		List<Patient> patientResultList = null;
		try {
			logger.info("fetching patient details");
			int patientId = data.getPatientId();
			String firstName = data.getFirstName();
			String lastName = data.getLastName();
			String dateOfBirth = data.getDateOfBirth();
			DataBaseConnection connection = new DataBaseConnection();
			con = connection.getConnection();
			PreparedStatement executeStatement;

			String patientSearchQuery = "select p.PatientId,FirstName,LastName,DateOfBirth,Gender,PhoneNo,Email_Id,AddressLine,Street,City,State,Country from patient as p, Address as a where p.PatientId = a.PatientId";

			if ((firstName == null) && (lastName == null) && (dateOfBirth == null) && (patientId != 0)) {
				patientSearchQuery += " and p.PatientId = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setInt(1, patientId);

			}

			else if ((firstName == null) && (lastName == null) && (dateOfBirth != null) && (patientId == 0)) {
				patientSearchQuery += " and DateOfBirth = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, dateOfBirth);
			}

			else if ((firstName == null) && (lastName == null) && (dateOfBirth != null) && (patientId != 0)) {
				patientSearchQuery += " and p.PatientId = ? and DateOfBirth = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setInt(1, patientId);
				executeStatement.setString(2, dateOfBirth);
			}

			else if ((firstName == null) && (lastName != null) && (dateOfBirth == null) && (patientId == 0)) {
				patientSearchQuery += " and LastName = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, lastName);
			}

			else if ((firstName == null) && (lastName != null) && (dateOfBirth == null) && (patientId != 0)) {
				patientSearchQuery += " and LastName = ? and p.PatientId = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, lastName);
				executeStatement.setInt(2, patientId);
			}

			else if ((firstName == null) && (lastName != null) && (dateOfBirth != null) && (patientId == 0)) {
				patientSearchQuery += " and LastName = ? and DateOfBirth = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, lastName);
				executeStatement.setString(2, dateOfBirth);
			}

			else if ((firstName == null) && (lastName != null) && (dateOfBirth != null) && (patientId != 0)) {
				patientSearchQuery += " and LastName = ? and DateOfBirth = ? and p.PatientId = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, lastName);
				executeStatement.setString(2, dateOfBirth);
				executeStatement.setInt(3, patientId);
			}

			else if ((firstName != null) && (lastName == null) && (dateOfBirth == null) && (patientId == 0)) {
				patientSearchQuery += " and FirstName = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, firstName);
			}

			else if ((firstName != null) && (lastName == null) && (dateOfBirth == null) && (patientId != 0)) {
				patientSearchQuery += " and FirstName = ? and p.PatientId = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, firstName);
				executeStatement.setInt(2, patientId);
			}

			else if ((firstName != null) && (lastName == null) && (dateOfBirth != null) && (patientId == 0)) {
				patientSearchQuery += " and FirstName = ? and DateOfBirth = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, firstName);
				executeStatement.setString(2, dateOfBirth);
			}

			else if ((firstName != null) && (lastName == null) && (dateOfBirth != null) && (patientId != 0)) {
				patientSearchQuery += " and FirstName = ? and DateOfBirth = ? and p.PatientId = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, firstName);
				executeStatement.setString(2, dateOfBirth);
				executeStatement.setInt(3, patientId);
			}

			else if ((firstName != null) && (lastName != null) && (dateOfBirth == null) && (patientId == 0)) {
				patientSearchQuery += " and FirstName = ? and LastName = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, firstName);
				executeStatement.setString(2, lastName);
			}

			else if ((firstName != null) && (lastName != null) && (dateOfBirth == null) && (patientId != 0)) {
				patientSearchQuery += " and FirstName = ? and LastName = ? and p.PatientId = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, firstName);
				executeStatement.setString(2, lastName);
				executeStatement.setInt(3, patientId);
			}

			else if ((firstName != null) && (lastName != null) && (dateOfBirth != null) && (patientId == 0)) {
				patientSearchQuery += " and FirstName = ? and LastName = ? and DateOfBirth = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, firstName);
				executeStatement.setString(2, lastName);
				executeStatement.setString(3, dateOfBirth);
			}

			else if ((firstName != null) && (lastName != null) && (dateOfBirth != null) && (patientId != 0)) {
				patientSearchQuery += " and FirstName = ? and LastName = ? and DateOfBirth = ? and p.PatientId = ?";
				executeStatement = con.prepareStatement(patientSearchQuery);
				executeStatement.setString(1, firstName);
				executeStatement.setString(2, lastName);
				executeStatement.setString(3, dateOfBirth);
				executeStatement.setInt(4, patientId);
			}

			else {
				executeStatement = con.prepareStatement(patientSearchQuery);
			}

			patientList = executeStatement.executeQuery();

			patientResultList = new ArrayList<Patient>();

			patientList.beforeFirst();

			while (patientList.next()) {
				data.setPatientId(patientList.getInt("PatientId"));
				data.setFirstName(patientList.getString("FirstName"));
				data.setLastName(patientList.getString("LastName"));
				data.setDateOfBirth(patientList.getString("DateOfBirth"));
				data.setPhoneNo(patientList.getString("PhoneNo"));
				data.setGender(patientList.getString("Gender"));
				data.setEmailId(patientList.getString("Email_Id"));
				data.setAddressLine(patientList.getString("AddressLine"));
				data.setStreet(patientList.getString("Street"));
				data.setCity(patientList.getString("City"));
				data.setState(patientList.getString("State"));
				data.setCountry(patientList.getString("Country"));

				patientResultList.add(data);
				data = new Patient();
			}
		} catch (Exception e) {
			logger.debug("Caught an exception" + e.getMessage());
		}
		return patientResultList;
	}
}
