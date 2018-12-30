/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * FactoryProducer.java 
 * Abstract class to create the factories as singleton factories
 */

public abstract class FactoryProducer {

	public static AbstractFactory getFactory(String factoryType){
		if(factoryType == null){
			return null;
		}
		
		if(factoryType.equalsIgnoreCase("SHAPE")){
			return SingletonShapeFactory.getInstance();
		}
		else if(factoryType.equalsIgnoreCase("COLOR")){
			return SingletonColorFactory.getInstance();
		}
		else if(factoryType.equalsIgnoreCase("TEXTURE")){
			return SingletonTextureFactory.getInstance();
		}
		
		return null;
    }
}
