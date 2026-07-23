package org.cerner.patientportal.bean;


/**
 * Date:1/31/2018
 * This is class which will initializes all the patient data entities..
 * @author KK055944
 * version:0.0.1
 *
 */
public class Patient {
	/**
	 * Initializing all the required fields of a patient.
	 */
	private int patientId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String phoneNo;
	private String emailId;
	private String addressLine;
	private String street;
	private String city;
	private String state;
	private String country;
	
	public Patient(String firstName,String lastName,String dateOfBirth,int patientId) {
		this.setPatientId(patientId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
	}
	
	public Patient() {
		}
	
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lasstName) 
	{
		this.lastName = lasstName;
	}
	public String getDateOfBirth() 
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) 
	{
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() 
	{
		return gender;
	}
	public void setGender(String gender) 
	{
		this.gender = gender;
	}
	public String getPhoneNo() 
	{
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) 
	{
		this.phoneNo = phoneNo;
	}
	public String getEmailId() 
	{
		return emailId;
	}
	public void setEmailId(String emaialId) 
	{
		this.emailId = emaialId;
	}
	public String getAddressLine() 
	{
		return addressLine;
	}
	public void setAddressLine(String addressLine) 
	{
		this.addressLine = addressLine;
	}
    
	public String getStreet() 
	{
		return street;
	}
	public void setStreet(String street) 
	{
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
	public String getState() 
	{
		return state;
	}
	public void setState(String state) 
	{
		this.state = state;
	}
	public String getCountry() 
	{
		return country;
	}
	public void setCountry(String country) 
	{
		this.country = country;
	}
}
