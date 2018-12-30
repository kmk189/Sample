/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * Fine.java 
 * Concrete class Fine that implements Texture interface
 */

import java.util.Random;

public class Fine implements Texture {

	@Override
	public void feel() {
		Random rand = new Random();
		int id = rand.nextInt(10000);       

		System.out.printf("Fine ID: %d\n\n", id);
	}
}