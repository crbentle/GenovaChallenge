package com.genova.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.genova.beans.Person;

/**
 * Tests methods in the CsvWriter class
 * 
 * @author Chris Bentley
 */
public class CsvWriterTest {

	/**
	 * Test method for {@link com.genova.data.CsvWriter#writeCsvFile(java.lang.String, java.util.List<com.genova.beans.Person>)}.
	 */
	@Test
	public void testWriteCsvFile() {
		String filePath = null;
		List<Person> personList = null;
		
		try {
			CsvWriter.writeCsvFile( filePath, personList );
			
			filePath = "files/testOutput.csv";
			CsvWriter.writeCsvFile( filePath, personList );
			
			personList = new ArrayList<Person>();
			CsvWriter.writeCsvFile( filePath, personList );
			
			personList.add( new Person("First", "Last", 35 ) );
			CsvWriter.writeCsvFile( filePath, personList );
			
			File file = new File("files/testOutput.csv");
			assertTrue( "Output file was not created.", file.exists() );
			
		} catch( Exception e) {
			fail( "Exception was expected to be caught. " + e );
		}
	}

	/**
	 * Test method for {@link com.genova.data.CsvWriter#getHeader()}.
	 */
	@Test
	public void testGetHeader() {
		String header = CsvWriter.getHeader();
		assertEquals( "header did not match expected value.", "Lastname, firstname, age", header );
	}

	/**
	 * Test method for {@link com.genova.data.CsvWriter#getPersonOutput(com.genova.beans.Person)}.
	 */
	@Test
	public void testGetPersonOutput() throws Exception {
		Person person = new Person( "First", "Last", 25 );
		String personStr = CsvWriter.getPersonOutput( person );
		assertEquals( "Simple personStr does not match expected value.", "Last, First, 25", personStr );
		

		person = new Person( "First space", "Last space", 25 );
		personStr = CsvWriter.getPersonOutput( person );
		assertEquals( "personStr with spaces does not match expected value.", "Last space, First space, 25", personStr );
	}

	/**
	 * Test method for {@link com.genova.data.CsvWriter#getAverageAge(java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testgGetAverageAge() throws Exception {
		
		String avg = CsvWriter.getAverageAge( 550, 15 );
		assertEquals( "2 decimal avg does not equal expected value.", "36.67", avg );
		
		avg = CsvWriter.getAverageAge( 42, 4 );
		assertEquals( "1 decimal avg does not equal expected value.", "10.5", avg );
		
		avg = CsvWriter.getAverageAge( 40, 4 );
		assertEquals( "0 decimal avg does not equal expected value.", "10", avg );
		
		avg = CsvWriter.getAverageAge( 40, 0 );
		assertEquals( "Divide by zero does not equal expected value.", "0", avg );
	}
}
