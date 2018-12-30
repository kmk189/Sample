/** 
 * Kevin Krause - kmk189
 * November 11, 2018
 * CS 1530 - 1070 - HW 3
 * 
 * AbstractFactoryTutorial.java 
 * Main file which runs code to test the singleton abstract factory producers
 */
 
 
public class AbstractFactoryTutorial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int loop = 1;loop < 3;loop++){
            // TODO code application logic here
            System.out.println("New set:");
            AbstractFactory factory0 = FactoryProducer.getFactory("SHAPE");
            factory0.showMessage();
            Shape shape0 = factory0.getShape("SQUARE");
            //call draw method of Square
            shape0.draw();

            AbstractFactory factory1 = FactoryProducer.getFactory("SHAPE");
            factory1.showMessage();
            Shape shape1 = factory1.getShape("CIRCLE");
            //call draw method of Circle
            shape1.draw();

            AbstractFactory factory2 = FactoryProducer.getFactory("SHAPE");
            factory2.showMessage();
            Shape shape2 = factory2.getShape("RECTANGLE");
            //call draw method of Rectangle
            shape2.draw();

            AbstractFactory factory3 = FactoryProducer.getFactory("COLOR");
            factory3.showMessage();
            Color color1 = factory3.getColor("RED");
            //call FILL method of Red
            color1.fill();

            AbstractFactory factory4 = FactoryProducer.getFactory("COLOR");
            factory4.showMessage();
            Color color2 = factory4.getColor("GREEN");
            //call FILL method of Green
            color2.fill();

            AbstractFactory factory5 = FactoryProducer.getFactory("COLOR");
            factory5.showMessage();
            Color color3 = factory5.getColor("BLUE");
            //call FILL method of Blue
            color3.fill();

            AbstractFactory factory6 = FactoryProducer.getFactory("TEXTURE");
            factory6.showMessage();
            Texture texture1 = factory6.getTexture("COARSE");
            //call FEEL method of Coarse
            texture1.feel();

            AbstractFactory factory7 = FactoryProducer.getFactory("TEXTURE");
            factory7.showMessage();
            Texture texture2 = factory7.getTexture("FINE");
            //call FEEL method of Fine
            texture2.feel();
        }
    }
}