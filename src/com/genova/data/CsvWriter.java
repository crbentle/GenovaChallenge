package com.genova.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.genova.beans.Person;
import com.genova.sorter.PersonComparator;

/**
 * Writes output to the console and to a CSV file.
 * 
 * @author Chris Bentley
 */
public class CsvWriter {

	// Newline character to append to each line in the CSV file
	static String newLine = "\r\n";

	/**
	 * Generates the output and writes the data to the console and to a CSV file
	 * 
	 * @param filePath
	 *            The path to the CSV output file to create
	 * @param personList
	 *            The list of Persons for which to generate output
	 */
	public static void writeCsvFile( String filePath, List<Person> personList ) {
		
		if( filePath == null ) {
			System.out.println( "Cannot output to a null file." );
			return;
		}
		
		if( personList == null || personList.isEmpty() ) {
			System.out.println( "Cannot process empty person list." );
			return;
		}

		// Sort the list by lastName ASC, firstName ASC, Age ASC
		Collections.sort( personList, new PersonComparator() );

		// The number of times a first name is duplicated
		int duplicateNames = 0;

		// The combined age of all Persons in personList
		int totalAge = 0;

		// Temporary list to hold unique first names
		List<String> firstNames = new ArrayList<String>();

		try {
			BufferedWriter bw = new BufferedWriter( new FileWriter( filePath ) );

			// Output the header row
			String header = getHeader();
			System.out.println( header );
			bw.write( header + newLine );

			for ( Person person : personList ) {

				// Output the person details
				String personStr = getPersonOutput( person );
				System.out.println( personStr );
				bw.write( personStr + newLine );

				// Check if this Person's first name matches anyone else's
				/*
				 * I am not 100% clear on this requirement.
				 * I decided to increment the duplicate names counter every time a first name is found that matches a previous first name.
				 * For example:
				 * 	1) 	Tom
				 * 		Tom
				 * 		Emily
				 * 		Emily
				 * 	  Will result in a count of 2
				 *  2) 	Tom
				 *  	Tom
				 *  	Tom
				 *  	Emily
				 *  	Emily
				 *    Will result in a count of 3
				 *  3)	Tom
				 *  	Tom
				 *  	Emily
				 *  	Emily
				 *  	Joe
				 *  	Joe
				 *    Will result in a count of 3
				 */
				if ( firstNames.contains( person.getFirstName() ) ) {
					duplicateNames++;
				} else {
					firstNames.add( person.getFirstName() );
				}

				totalAge += person.getAge();
			}

			// Add an empty line between the user details and the result counts
			System.out.println( "" );
			bw.write( newLine );

			// Output the first name duplicate count
			String duplicates = String.format( "There were %d incidences of duplicate first names",
					duplicateNames );
			System.out.println( duplicates );
			bw.write( duplicates + newLine );

			// Output the average age
			String avgAge = getAverageAge( totalAge, personList.size() );
			String avgAgeStr = String.format( "The average age for both files was %s", avgAge );
			System.out.println( avgAgeStr );
			bw.write( avgAgeStr + newLine );

			bw.flush();
			bw.close();

		} catch ( IOException e ) {
			System.out.println( "Error creating output file." );
			e.printStackTrace();
		}
	}

	/**
	 * Prints the output header to the console and then returns the header
	 * 
	 * @return The output header
	 */
	protected static String getHeader() {
		return "Lastname, firstname, age";
	}

	/**
	 * Prints the formatted Person to the console and then returns the formatted
	 * Person
	 * 
	 * @param person
	 *            The person to output
	 * @return The formatted person String
	 */
	protected static String getPersonOutput( Person person ) {
		return person.getLastName() + ", " + person.getFirstName() + ", " + person.getAge();
	}

	/**
	 * Calculates the average age
	 * 
	 * @param totalAge The total combined age of all Persons
	 * @param personCount The number of Persons
	 * @return The average age
	 */
	protected static String getAverageAge( int totalAge, int personCount ) {
		double avg = 0;
		if( personCount != 0 ) {
			avg = (double)totalAge / personCount;
		}
		return new DecimalFormat("#.##").format( avg );
	}

}
