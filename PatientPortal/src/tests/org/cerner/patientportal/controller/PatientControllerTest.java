package org.cerner.patientportal.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.cerner.patientportal.bean.Patient;
import org.cerner.patientportal.controller.PatientController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class PatientControllerTest {

	@Mock
	Patient entity;
	PatientController controller;

	@Before
	public void create() throws Exception {
		entity = new Patient();
		controller = mock(PatientController.class);
	}

	@Test
	public void testPatientControllerByNull() throws Exception {
		entity = null;
		when(controller.createPatient(entity)).thenReturn("Sorry! Unable to store the data");
		assertEquals(controller.createPatient(entity), "Sorry! Unable to store the data");
	}

	@Test
	public void testPatientControllerByFirstName() throws Exception {
		entity.setFirstName(null);
		entity.setLastName("Kumar");
		entity.setDateOfBirth("12-21-2012");
		entity.setGender("Male");
		entity.setPhoneNo("9949850519");
		entity.setEmailId("ashok@cerner.com");
		when(controller.createPatient(entity)).thenReturn("Invalid Data!");
		assertEquals(controller.createPatient(entity), "Invalid Data!");
	}

	@Test
	public void testPatientControllerByLastName() throws Exception {
		entity.setFirstName("Ash");
		entity.setLastName(null);
		entity.setDateOfBirth("12-21-2012");
		entity.setGender("Male");
		entity.setPhoneNo("9949850519");
		entity.setEmailId("ashok@cerner.com");
		when(controller.createPatient(entity)).thenReturn("Invalid Data!");
		assertEquals(controller.createPatient(entity), "Invalid Data!");
	}

	@Test
	public void testPatientControllerbyValidData() throws Exception {
		entity.setFirstName("Ashok");
		entity.setLastName("Kumar");
		entity.setDateOfBirth("12-21-2012");
		entity.setGender("Male");
		entity.setPhoneNo("9949850519");
		entity.setEmailId("ashok@cerner.com");
		when(controller.createPatient(entity)).thenReturn("Successfully registerd the patient Details!");
		assertEquals(controller.createPatient(entity), "Successfully registerd the patient Details!");
	}

	@Test
	public void testSearchPatientForPatientId() throws Exception {
		String match = null;
		assertEquals(controller.searchPatient(90, null, null, null), match);
	}

	@Test
	public void testSearchPatientForFirstName() throws Exception {
		String match = null;
		assertEquals(controller.searchPatient(0, "Sneha2", null, null), match);
	}

	@Test
	public void testSearchPatientForLastName() throws Exception {
		String match = null;
		assertEquals(controller.searchPatient(0, null, "Mouse1", null), match);
	}

	@Test
	public void testSearchPatientForDateOfBirth() throws Exception {
		String match = null;
		assertEquals(controller.searchPatient(0, null, null, "1997-09-05"), match);
	}

	@Test
	public void testSearchPatientForNull() throws Exception {
		String match = null;
		assertEquals(controller.searchPatient(99, null, null, null), match);
	}
}
