package com.lab.daytwo;

import java.util.Comparator;

/**
*
* @author tamarabrinckman
*/
public class NameComparator implements Comparator {

	@Override
	public int compare(Object a, Object b) {
		
		//You need to cast objects (Object to Person)
		Person p1 = (Person) a;
		Person p2 = (Person) b;
		
		//compareTo() returns 0 if both FirstNames are equal
		if(p1.getFirstName().compareTo(p2.getFirstName()) == 0) {
			//If same first name compare person last names
			return p1.getLastName().compareTo(p2.getLastName());
		}
		
		return p1.getFirstName().compareTo(p2.getFirstName());
	}

}
