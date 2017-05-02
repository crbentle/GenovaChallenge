package com.genova.sorter;

import java.util.Comparator;

import com.genova.beans.Person;

/**
 * A custom Comparator used to sort a list of Person objects
 * 
 * @author Chris Bentley
 */
public class PersonComparator implements Comparator<Person> {

	/**
	 * Compares two Person objects and returns -1, 0, or 1 as the first argument
	 * is less than, equal to, or greater than the second.
	 * <p>
	 * 
	 * The Persons are first compared by lastName. If the lastName is equal then
	 * firstNames are compared. If firstNames are also equal then age is
	 * compared.
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare( Person p1, Person p2 ) {
		int result = 0;
		
		if ( p1 != null ) {
			if ( p2 != null ) {
				// Compare last names
				String p1LastName = p1.getLastName();
				String p2LastName = p2.getLastName();
				result = p1LastName.compareToIgnoreCase( p2LastName );
				
				// Last names are equals
				if ( result == 0 ) {
					// Compare first names
					String p1FirstName = p1.getFirstName();
					String p2FirstName = p2.getFirstName();
					result = p1FirstName.compareToIgnoreCase( p2FirstName );
					
					// First names are equal
					if ( result == 0 ) {
						// Compare age
						int p1Age = p1.getAge();
						int p2Age = p2.getAge();
						result = Integer.compare( p1Age, p2Age );
					}
				}
			} else {
				result = 1;
			}
		} else if ( p2 != null ) {
			result = -1;
		}

		return result;
	}

}
