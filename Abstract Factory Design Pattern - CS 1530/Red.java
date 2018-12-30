/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * Red.java 
 * Concrete class Red for the interface Color
 */

import java.util.Random;

public class Red implements Color {

	@Override
	public void fill() {
		Random rand = new Random();
		int id = rand.nextInt(10000);       

		System.out.printf("Red ID: %d\n\n", id);
	}
}