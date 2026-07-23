package org.cerner.patientportal.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.cerner.patientportal.bean.Patient;
import org.cerner.patientportal.services.PatientServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class PatientServicesTest {
	Patient entity;
	@Mock
	PatientServices service;

	PatientServices seviceWithOutMock = new PatientServices();

	@Before
	public void create() throws Exception {
		entity = new Patient();
		service = mock(PatientServices.class);
	}

	/**
	 * This test will store the data and return null. Here we are asserting to some
	 * other message So it will fail.
	 */
	@Test
	public void testPatientData() throws Exception {
		entity.setFirstName("Ashok");
		entity.setLastName("Kumar");
		entity.setDateOfBirth("12-21-2012");
		entity.setGender("Male");
		entity.setPhoneNo("9949850519");
		entity.setEmailId("ashok@cerner.com");
		entity.setAddressLine("10-21");
		entity.setStreet("rr street");
		entity.setCity("banglore");
		entity.setState("ka");
		entity.setCountry("India");
		when(service.registerPatientData(entity)).thenReturn("Successfully registerd the patient Details!");
		assertEquals(service.registerPatientData(entity), "Successfully registerd the patient Details!");
	}

	@Test
	public void testPatientDatabyInvalidData() throws Exception {
		entity.setFirstName("As123hok");
		entity.setLastName("Ku123mar");
		entity.setDateOfBirth("12-21-2012");
		entity.setGender("Male");
		entity.setPhoneNo("9949850519");
		entity.setEmailId("ashok@cerner.com");
		entity.setAddressLine("10-21");
		entity.setStreet("rr street");
		entity.setCity("banglore");
		entity.setState("ka");
		entity.setCountry("India");
		assertEquals(seviceWithOutMock.registerPatientData(entity), "Invalid Data!");
	}

	@Test
	public void testPatientDatabyNullValue() throws Exception {
		entity.setFirstName(null);
		entity.setLastName("Ku123mar");
		entity.setDateOfBirth("12-21-2012");
		entity.setGender("Male");
		entity.setPhoneNo("9949850519");
		entity.setEmailId("ashok@cerner.com");
		entity.setAddressLine("10-21");
		entity.setStreet("rr street");
		entity.setCity("banglore");
		entity.setState("ka");
		entity.setCountry("India");
		assertEquals(seviceWithOutMock.registerPatientData(entity), "Invalid Data!");
	}
}