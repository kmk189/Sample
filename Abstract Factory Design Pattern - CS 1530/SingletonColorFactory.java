/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * SingletonColorFactory.java 
 * Factory for the Color interface that only allows one 
 * 		Color factory to be instantiated
 */

import java.util.Random;

public class SingletonColorFactory extends AbstractFactory{
	//create an object of SingleObject
	private static SingletonColorFactory instance = new SingletonColorFactory();
	private static int factoryID;

	//make the constructor private so that this class cannot be
	//instantiated
	private SingletonColorFactory(){
		Random rand = new Random();
		factoryID = rand.nextInt(10000);       
	}

	//Get the only object available
	public static SingletonColorFactory getInstance(){
		return instance;
	}

	public void showMessage(){
		System.out.printf("Color Factory ID: %d\n", factoryID);
	}    
   
	//use getShape method to get object of type shape 
	@Override
	public Color getColor(String colorType){
		if(colorType == null){
			return null;
		}		
		if(colorType.equalsIgnoreCase("RED")){
			return new Red();      
		} else if(colorType.equalsIgnoreCase("BLUE")){
			return new Blue();
		} else if(colorType.equalsIgnoreCase("GREEN")){
			return new Green();
		}
		return null;
    }

	@Override
	public Shape getShape(String shapeType){
		return null;
	} 
	
	@Override
	public Texture getTexture(String textureType){
		return null;
	}
}
