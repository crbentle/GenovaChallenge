package com.genova.properties.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Utility class to load and read property values.
 * 
 * @author Chris Bentley
 */
public class PropertyManager {

	// The property file to read
	private static final String PROPERTY_FILE = "com/genova/properties/challenge.properties";

	// The keys listed in the properties file
	public static final String INPUT_FILE_KEY = "input.files";
	public static final String OUTPUT_FILE_KEY = "output.file";

	protected static Properties properties;

	// Load the properties file
	static {
		loadProperties( PROPERTY_FILE );
	}

	/**
	 * Returns the property value for the input key or {@code null} if the
	 * property is not found.
	 * 
	 * @param propertyKey
	 *            The property key
	 * @return The property value or {@code null}
	 */
	public static String getProperty( String propertyKey ) {
		String value = properties.getProperty( propertyKey );
		if ( value != null ) {
			value = value.trim();
		}
		return value;
	}

	/**
	 * Gets a comma-separated property values and converts it into a list of
	 * Strings
	 * 
	 * @param propertyKey
	 *            The property key
	 * @return The List of property values or an empty list if no values found
	 *         for the input key
	 */
	public static List<String> getListProperty( String propertyKey ) {
		List<String> propertyList = new ArrayList<String>();

		String propStr = getProperty( propertyKey );
		if ( propStr != null ) {
			String[] propArray = propStr.split( "," );
			if ( propArray != null && propArray.length > 0 ) {
				for ( String prop : propArray ) {
					propertyList.add( prop.trim() );
				}
			}
		}
		return propertyList;
	}

	/**
	 * Load the properties file into the Properties object
	 * 
	 * @param propFileName
	 *            The class path of the properties file to load
	 */
	private static void loadProperties( String propFileName ) {
		if ( properties == null ) {
			properties = new Properties();
		}

		InputStream is = PropertyManager.class.getClassLoader()
				.getResourceAsStream( PROPERTY_FILE );
		
		if ( is != null ) {
			try {
				properties.load( is );
			} catch ( IOException e ) {
				System.out.println( "Error loading properties file." );
			}
		}
	}

}
