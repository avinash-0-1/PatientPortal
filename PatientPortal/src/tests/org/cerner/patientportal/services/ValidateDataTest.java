package org.cerner.patientportal.services;

import static org.junit.Assert.assertEquals;

import org.cerner.patientportal.bean.Patient;
import org.cerner.patientportal.services.PatientServices;
import org.junit.Before;
import org.junit.Test;

/**
 * Date:3/05/2018
 * 
 * @author KK055944 In this class, there are tests which are defined to check
 *         the firstName, lastName and email-Id are valid data or not.
 */
public class ValidateDataTest {
	Patient entity;
	PatientServices service;
	boolean validData;

	@Before
	public void create() throws Exception {
		entity = new Patient();
		service = new PatientServices();
	}

	/**
	 * This test method will validate the patient data by firstName and lastName
	 * here we have given the firstName as invalid data. So it should return as
	 * false.
	 */
	@Test
	public void validateDataByFirstname() {
		entity.setFirstName("A1");
		entity.setLastName("Elgar");
		validData = service.validatePatientData(entity);
		assertEquals(validData, false);
	}

	/**
	 * This test method will validate the patient data by firstName and lastName
	 * here we have given the lastName as invalid data. So it should return as
	 * false.
	 */
	@Test
	public void validateDataTestByLastname() {
		entity.setFirstName("Ashok");
		entity.setLastName("El23gar");
		validData = service.validatePatientData(entity);
		assertEquals(validData, false);
	}

	/**
	 * This test method will validate the patient data by firstName and lastName
	 * here we have given the firstName as null. So it should return as false.
	 */
	@Test
	public void validateDataByNullValue() {
		entity.setFirstName(null);
		entity.setLastName("Elgar");
		validData = service.validatePatientData(entity);
		assertEquals(validData, false);
	}

	/**
	 * This test method will validate the patient data by firstName and lastName
	 * here we have given the firstName and lastName as null values. So it should
	 * return as false.
	 */
	@Test
	public void validateDataTestByNullValues() {
		entity.setFirstName(null);
		entity.setLastName(null);
		validData = service.validatePatientData(entity);
		assertEquals(validData, false);
	}

	/**
	 * This test method will validate the patient data by firstName and lastName
	 * here we have given the firstName, lastName as valid data. So it will pass. It
	 * will pass the test case.
	 */
	@Test
	public void validateDataTest() {
		entity.setFirstName("Dean");
		entity.setLastName("Elgar");
		validData = service.validatePatientData(entity);
		assertEquals(validData, true);
	}
}
