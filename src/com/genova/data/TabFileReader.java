package com.genova.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.genova.beans.Person;

/**
 * Reads a tab delimited file and returns a list of Person objects created from
 * the file contents. The file format is: FirstName{tab}LastName{tab}Age
 * 
 * @author Chris Bentley
 */
public class TabFileReader {

	/**
	 * Returns a list of Person objects created by reading each line of the
	 * input file and parsing the data into a Person.
	 * <p>
	 * If a problem occurred while reading the file or the file is empty an
	 * empty List will be returned.
	 * 
	 * @param filename
	 *            The absolute path the the input file
	 * @return The List of Person objects contained in the file
	 */
	public static List<Person> readTabDelimitedFile( String filename ) {

		List<Person> personList = new ArrayList<Person>();

		if ( filename == null || filename.trim().length() == 0
				|| !( new File( filename ) ).exists() ) {
			System.out.println( "Cannot read input file: " + filename );
			return personList;
		} else {
			BufferedReader br = null;
			try {
				br = new BufferedReader( new FileReader( filename ) );
				String line;
				Person person = null;
				boolean header = true;

				// Read each line of the file
				while ( ( line = br.readLine() ) != null ) {
					// The first line will be the header and can be skipped
					if ( header ) {
						header = false;
						continue;
					}

					person = getPersonFromLine( line );
					if ( person != null ) {
						personList.add( person );
						person = null;
					}
				}
			} catch ( IOException e ) {
				System.out.println( "Cannot read input file: " + filename );
				e.printStackTrace();
			} finally {
				if ( br != null ) {
					try {
						br.close();
					} catch ( IOException e ) {
						System.out.println( "Error closing BufferedReader." );
						e.printStackTrace();
					}
				}
			}
		}
		return personList;
	}

	/**
	 * Splits a line on tabs and generates a Person object from the pieces
	 * 
	 * @param line
	 *            The line to split
	 * @return The Person created from the input line
	 */
	protected static Person getPersonFromLine( String line ) {
		Person person = null;

		if ( line != null ) {
			String[] split = line.split( "\t" );
			if ( split != null && split.length == 3 ) {
				String firstName = split[0].trim();
				String lastName = split[1].trim();
				try {
					int age = Integer.parseInt( split[2].trim() );
					person = new Person( firstName, lastName, age );
				} catch( NumberFormatException e ) {
					System.out.println( "Cannot convert String to int age: " + split[2] );
				}
			}
		}
		return person;
	}

	/*
	 * Main method used for testing
	 */
	public static void main( String[] args ) {
		String file = "C:\\Genova coding challenge\\NameList2.txt";
		List<Person> personList = TabFileReader.readTabDelimitedFile( file );
		System.out.println( personList );
	}

}
