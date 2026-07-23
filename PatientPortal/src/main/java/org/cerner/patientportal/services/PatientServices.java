package org.cerner.patientportal.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.cerner.patientportal.bean.Patient;
import org.cerner.patientportal.dao.PatientDAO;

/**
 * Date:1/31/2018 This is class which Provides several types of services.
 * 
 * @author KK055944 version:0.0.1
 *
 */
/**
 * @author RK056555
 *
 */
public class PatientServices {
	static Logger logger = Logger.getLogger(PatientServices.class);
	PatientDAO patientDAO = new PatientDAO();
	PatientServices patientService;

	/**
	 * This method will validate the patient data for important fields. throws
	 * exception and will make {@validData} as false
	 */
	public Boolean validatePatientData(Patient data) {
		Boolean validItem = true;
		Pattern pattern = Pattern.compile("[^A-Za-z]");
		if (data.getFirstName() == null || data.getLastName() == null || data.getLastName().isEmpty()
				|| data.getFirstName().isEmpty()) {
			validItem = false;
		} else {
			Matcher matchFirstName = pattern.matcher(data.getFirstName());
			Matcher matchLastName = pattern.matcher(data.getLastName());

			if (!matchFirstName.find() && !matchLastName.find()) {
				validItem = true;
			} else
				validItem = false;
		}
		logger.info("validated successfully:" + validItem);

		return validItem;
	}

	/**
	 * This Services method will bind all the services of patient
	 * 
	 * @param data
	 *            which comes from UI as Json format of patient data.
	 * @throws Exception
	 *             if any.
	 */
	public String registerPatientData(Patient data) throws Exception {
		patientService = new PatientServices();
		logger.info("entered Services class from controller.");
		/**
		 * from this method, the control will call the particular service method to
		 * perform the job. here, the register of patient method is calling.
		 */
		String response = null;
		if (patientService.validatePatientData(data)) {
			response = patientDAO.registerPatient(data);
			if (response == null) {
				response = patientDAO.registerPatientAddress(data);
			}
		} else {
			response = "Invalid Data!";
		}
		return response;
	}

	/**
	 * @param data
	 * @return String object containing patient's data
	 * @throws Exception
	 */
	public List<Patient> searchPatients(Patient data) throws Exception {
		/**
		 * from this method, the control will call the particular service method to
		 * perform the job. here, the search of patient method is getting called.
		 */
		List<Patient> patientData = null;
		try {
			logger.info("providing search service");
			patientData = patientDAO.getPatientList(data);
		} catch (Exception e) {
			logger.debug("Caught exception" + e.getMessage());
		}
		return patientData;
	}
}