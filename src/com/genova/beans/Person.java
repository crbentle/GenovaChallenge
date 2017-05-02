package com.genova.beans;

/**
 * A bean to represent the NameList data
 * 
 * @author Chris Bentley
 */
public class Person {
	
	private String firstName, lastName;
	private int age;
	
	/**
	 * Constructor
	 * 
	 * @param firstName The Person's first name
	 * @param lastName The Person's last name
	 * @param age The Person's age
	 */
	public Person( String firstName, String lastName, int age ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	/**
	 * Gets the firstName
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Sets the firstName
	 * @param firstName the firstName to set
	 */
	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the lastName
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Sets the lastName
	 * @param lastName the lastName to set
	 */
	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the age
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * Sets the age
	 * @param age the age to set
	 */
	public void setAge( int age ) {
		this.age = age;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nlastName=" + lastName + ", firstName=" + firstName + ", age=" + age;
	}
}
