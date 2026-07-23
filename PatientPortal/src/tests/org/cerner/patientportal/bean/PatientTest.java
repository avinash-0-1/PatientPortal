package org.cerner.patientportal.bean;

import static org.junit.Assert.assertEquals;

import org.cerner.patientportal.bean.Patient;
import org.junit.Test;

public class PatientTest {
	Patient entity = new Patient();

	@Test
	public void testfirstname() {

		String first_name = "Ashok";
		entity.setFirstName("Ashok");
		assertEquals(entity.getFirstName(), first_name);
	}

	@Test
	public void testlastname() {
		String last_name = "Kumar";
		entity.setLastName("Kumar");
		assertEquals(entity.getLastName(), last_name);
	}

	@Test
	public void testDateOfBirth() {
		String Dateofbirth = "23-3-2019";
		entity.setDateOfBirth("23-3-2019");
		assertEquals(entity.getDateOfBirth(), Dateofbirth);
	}

	@Test
	public void testGender() {
		String Gender = "Male";
		entity.setGender("Male");
		assertEquals(entity.getGender(), Gender);
	}

	@Test
	public void testMobileno() {
		String Mobile = "99494850519";
		entity.setPhoneNo("99494850519");
		assertEquals(entity.getPhoneNo(), Mobile);
	}

}
