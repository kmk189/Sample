/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * Rectangle.java 
 * Concrete class Rectangle that implements Shape interface
 */

import java.util.Random;

public class Rectangle implements Shape {

	@Override
	public void draw() {
		Random rand = new Random();
		int id = rand.nextInt(10000);       

		System.out.printf("Rectangle ID: %d\n\n", id);
	}
}