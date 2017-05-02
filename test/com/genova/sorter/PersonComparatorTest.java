package com.genova.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.genova.beans.Person;

/**
 * @author Chris Bentley
 *
 */
public class PersonComparatorTest {

	/**
	 * Test method for {@link com.genova.sorter.PersonComparator#compare(com.genova.beans.Person, com.genova.beans.Person)}.
	 */
	@Test
	public void testCompare() {
		PersonComparator comparator = new PersonComparator();
		
		// lastName compare
		Person p1 = new Person( "aFirstName", "aLastName", 25 );
		Person p2 = new Person( "bFirstName", "bLastName", 25 );
		assertEquals( "p1 should be before p2 because lastName", -1, comparator.compare( p1, p2 ) );
		assertEquals( "p1 should be before p2 because lastName", 1, comparator.compare( p2, p1 ) );
		
		// firstName compare
		p1 = new Person( "aFirstName", "aLastName", 25 );
		p2 = new Person( "aFirstName", "bLastName", 25 );
		assertEquals( "p1 should be before than p2 because firstName", -1, comparator.compare( p1, p2 ) );
		assertEquals( "p1 should be before than p2 because firstName", 1, comparator.compare( p2, p1 ) );
		
		// age compare
		p1 = new Person( "aFirstName", "aLastName", 25 );
		p2 = new Person( "aFirstName", "aLastName", 35 );
		assertEquals( "p1 should be before than p2 because age", -1, comparator.compare( p1, p2 ) );
		assertEquals( "p1 should be before than p2 because age", 1, comparator.compare( p2, p1 ) );
		
		p1 = new Person( "aFirstName", "aLastName", 35 );
		p2 = new Person( "aFirstName", "aLastName", 35 );
		assertEquals( "p1 should be = to p2", 0, comparator.compare( p1, p2 ) );
		assertEquals( "p2 should be = to p1", 0, comparator.compare( p2, p1 ) );
	}

}
