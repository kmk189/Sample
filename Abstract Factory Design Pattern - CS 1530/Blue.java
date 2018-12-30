/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * Blue.java 
 * Concrete class Blue which implements Color interface
 */

import java.util.Random;

public class Blue implements Color {

	@Override
	public void fill() {
		Random rand = new Random();
		int id = rand.nextInt(10000);       

		System.out.printf("Blue ID: %d\n\n", id);
	}
}