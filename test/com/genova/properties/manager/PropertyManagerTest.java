package com.genova.properties.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * @author Chris Bentley
 *
 */
public class PropertyManagerTest {
	
	@Test
	public void testReadProperties() throws Exception {
		assertNotNull( "properites should be initialized on class load", PropertyManager.properties );
		assertTrue( "Property file does not contain any property keys", PropertyManager.properties.keySet().size() > 0 );
	}

	/**
	 * Test method for {@link com.genova.properties.manager.PropertyManager#getProperty(java.lang.String)}.
	 */
	@Test
	public void testGetProperty() {
		String value = PropertyManager.getProperty( PropertyManager.OUTPUT_FILE_KEY );
		assertEquals( "files/Output.csv", value );
		
		value = PropertyManager.getProperty( "bad_key" );
		assertNull( "property value should be null for non-existent key", value );
	}

	/**
	 * Test method for {@link com.genova.properties.manager.PropertyManager#getListProperty(java.lang.String)}.
	 */
	@Test
	public void testGetListProperty() {
		List<String> propertyList = PropertyManager.getListProperty( PropertyManager.INPUT_FILE_KEY );
		assertNotNull( "propertyList should contain values", propertyList );
		assertEquals( "input files propertyList should contain values", 2, propertyList.size() );
		assertEquals( "files/NameList1.txt", propertyList.get( 0 ) );
		assertEquals( "files/NameList2.txt", propertyList.get( 1 ) );
		
		propertyList = PropertyManager.getListProperty( "bad_key" );
		assertNotNull( "propertyList should be empty for non-existent key", propertyList );
		assertTrue( "propertyList should be empty for non-existent key", propertyList.isEmpty() );
		
	}

}
