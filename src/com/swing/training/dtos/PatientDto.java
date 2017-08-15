package com.swing.training.dtos;

import java.util.Date;

public class PatientDto {

	private String patientId;
	private String name;
	private String phoneNumber;
	private Date birthday;
	private String gender;
	private String address;
	private String employmentStatus;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the employmentStatus
	 */
	public String getEmploymentStatus() {
		return employmentStatus;
	}

	/**
	 * @param employmentStatus
	 *            the employmentStatus to set
	 */
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	/**
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId
	 *            the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Override
	public String toString() {
		return ("PatientId:" + this.getPatientId() + ", Patient name:"
				+ this.getName() + ", Phone :" + this.getPhoneNumber()
				+ ", Birthday :" + this.getBirthday() + ", Gender :"
				+ this.getGender() + ", Address :" + this.getAddress()
				+ ", Emp Status :" + this.getEmploymentStatus());
	}

}
