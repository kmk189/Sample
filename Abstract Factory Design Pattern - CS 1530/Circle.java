/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * Circle.java 
 * Concrete class Circle which implements Shape interface
 */

import java.util.Random;

public class Circle implements Shape {

	@Override
	public void draw() {
		Random rand = new Random();
		int id = rand.nextInt(10000);       

		System.out.printf("Circle ID: %d\n\n", id);
	}
}