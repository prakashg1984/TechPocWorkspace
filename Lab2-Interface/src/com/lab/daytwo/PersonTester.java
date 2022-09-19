package com.lab.daytwo;

import java.util.Arrays;

/**
*
* @author tamarabrinckman
*/
public class PersonTester {

	public static void main(String[] args) {
		new PersonTester();

	}
	
	public PersonTester() {
		
		//Create an array, peopleList, of Person type which hold at least 10 people objects.
		Person[] peopleList = new Person[10];
		
		peopleList[0] = new Person("Giya","Gulati",34);
		peopleList[1] = new Person("Syed","Gosal",23);
		peopleList[2] = new Person("Syed","Armani",56);
		peopleList[3] = new Person("Yuvi","Birk",12);
		peopleList[4] = new Person("Mark","Berry",44);
		peopleList[5] = new Person("Joe","Fernandes",20);
		peopleList[6] = new Person("Mark","Master",89);
		peopleList[7] = new Person("Joe","Eules",32);
		peopleList[8] = new Person("Alex","Wright",41);
		peopleList[9] = new Person("Alex","Bill",62);
		
		System.out.println("###Unsorted people list###");
		for(int i=0 ; i < peopleList.length ; i++) {
			System.out.println(peopleList[i]);
		}
		
		System.out.println("\n");
		System.out.println("###Sorted people list by their age###");
		//Sort the array of people with the Arrays.sort method based on their ages
		Arrays.sort(peopleList, new AgeComparator());
		for(int i=0 ; i < peopleList.length ; i++) {
			System.out.println(peopleList[i]);
		}
		
		System.out.println("\n");
		System.out.println("###Sorted people list by their Name###");
		//Sort the array of people with the Arrays.sort method based on their Names
		Arrays.sort(peopleList, new NameComparator());
		for(int i=0 ; i < peopleList.length ; i++) {
			System.out.println(peopleList[i]);
		}

	}

}
