/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * Coarse.java 
 * Concrete class Coarse which implements Texture interface
 */

import java.util.Random;

public class Coarse implements Texture {

	@Override
	public void feel() {
		Random rand = new Random();
		int id = rand.nextInt(10000);       

		System.out.printf("Coarse ID: %d\n\n", id);
	}
}