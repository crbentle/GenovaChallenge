package com.genova.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.genova.beans.Person;

/**
 * Tests methods of the TabFileReader class
 * 
 * @author Chris Bentley
 */
public class TabFileReaderTest {

	/**
	 * Test method for {@link com.genova.data.TabFileReader#readTabDelimitedFile(java.lang.String)}.
	 */
	@Test
	public void testReadTabDelimitedFile() {
		String filename = null;
		List<Person> personList = null;
		personList = TabFileReader.readTabDelimitedFile( filename );
		assertTrue( "empty personList expected for null filename", personList.isEmpty() );
		
		filename = "";
		personList = TabFileReader.readTabDelimitedFile( filename );
		assertTrue( "empty personList expected for empty filename", personList.isEmpty() );
		
		filename = "badFile.txt";
		personList = TabFileReader.readTabDelimitedFile( filename );
		assertTrue( "empty personList expected for non-existent filename", personList.isEmpty() );
		
		filename = "test/TestData.txt";
		personList = TabFileReader.readTabDelimitedFile( filename );
		assertNotNull( "Valid personList expected for valid filename", personList );
		assertEquals( "Expected 9 Persons from TestData.txt", 9, personList.size() );
		assertFalse( "Header should not be converted into a Person", "First Name".equals( personList.get( 0 ).getFirstName() ) );
	}

	/**
	 * Test method for {@link com.genova.data.TabFileReader#getPersonFromLine(java.lang.String)}.
	 */
	@Test
	public void testGetPersonFromLine() throws Exception {
		String line = null;
		Person person = TabFileReader.getPersonFromLine( line );
		assertNull( "null person expected for null input", person );
		
		line = "";
		person = TabFileReader.getPersonFromLine( line );
		assertNull( "null person expected for empty input", person );
		
		line = "firstName";
		person = TabFileReader.getPersonFromLine( line );
		assertNull( "null person expected for 1 element input", person );
		
		line = "firstName	lastName";
		person = TabFileReader.getPersonFromLine( line );
		assertNull( "null person expected for 2 element input", person );
		
		line = "firstName	lastName	35	Other";
		person = TabFileReader.getPersonFromLine( line );
		assertNull( "null person expected for 4 element input", person );
		
		line = "firstName lastName 3";
		person = TabFileReader.getPersonFromLine( line );
		assertNull( "null person expected for input with spaces instead of tabs", person );
		
		line = "firstName	lastName	Str";
		person = TabFileReader.getPersonFromLine( line );
		assertNull( "null person expected for input with non-numeric age", person );
		
		line = "firstName	lastName	35";
		person = TabFileReader.getPersonFromLine( line );
		assertNotNull( "NotNull person expected for valid input", person );
		assertEquals( "firstName did not match expected.", "firstName", person.getFirstName() );
		assertEquals( "lastName did not match expected.", "lastName", person.getLastName() );
		assertEquals( "age did not match expected.", 35, person.getAge() );
	}

}
