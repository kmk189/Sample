/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * AbstractFactory.java 
 * Abstract class to get factories for Color, Texture, and Shape Objects
 */
 
public abstract class AbstractFactory {
	//  private static long VIN; 
	public abstract Shape getShape(String colorType);
    public abstract Color getColor(String shapeType); 
    public abstract Texture getTexture(String textureType);
	public void showMessage(){};
}
