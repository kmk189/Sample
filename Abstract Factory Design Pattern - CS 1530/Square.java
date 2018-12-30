/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * Square.java 
 * Concrete class Square that implements Shape interface
 */

import java.util.Random;

public class Square implements Shape {

	@Override
	public void draw() {
		Random rand = new Random();
		int id = rand.nextInt(10000);       

		System.out.printf("Square ID: %d\n\n", id);
	}
}