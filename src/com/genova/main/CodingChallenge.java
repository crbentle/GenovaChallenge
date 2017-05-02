package com.genova.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.genova.beans.Person;
import com.genova.data.CsvWriter;
import com.genova.data.TabFileReader;
import com.genova.properties.manager.PropertyManager;

/**
 * Main entry point for the Genova Diagnostics coding challenge.
 * 
 * The challenge is to read in two provided tab delimited files and:
 * <ol>
 * <li>Sort the two lists into one list in alphabetical order by last name</li>
 * <li>Count the number of duplicate first names</li>
 * <li>Give the average age of both lists combined</li>
 * </ol>
 * <br />
 * The results will be output to screen and to a csv file in the following
 * format:
 * 
 * <pre>
 * Lastname, firstname, age
.
.
.

There were x incidences of duplicate first names
The average age for both files was x
 * </pre>
 * 
 * @author Chris Bentley
 *
 */
public class CodingChallenge {

	/**
	 * Reads the input files, sorts the combined list, and outputs the data to
	 * console and a CSV file.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main( String[] args ) {

		// Get the list of files to read from a property file
		List<String> inputFiles = PropertyManager.getListProperty( PropertyManager.INPUT_FILE_KEY );
		List<Person> combinedList = new ArrayList<Person>();

		if ( inputFiles != null && !inputFiles.isEmpty() ) {
			for ( String file : inputFiles ) {
				// Add the data from the input file to the combinedList
				combinedList.addAll( TabFileReader.readTabDelimitedFile( file ) );
			}

			// Write the output to console and a CSV file
			CsvWriter.writeCsvFile( PropertyManager.getProperty( PropertyManager.OUTPUT_FILE_KEY ), combinedList );
		} else {
			System.out.println( "No data read from input files." );
		}
	}

}
