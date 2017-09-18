public class Car{

	private String VIN;
	private String make;
	private String model;
	private int price;
	private int mileage;
	private String color;
	
	
	public Car(String VIN_in, String make_in, String model_in, int price_in, int mileage_in, String color_in){
		this.VIN = VIN_in;
		this.make = make_in;
		this.model = model_in;
		this.price = price_in;
		this.mileage = mileage_in;
		this.color = color_in;
	}
	
	public void setVIN(String VIN_in){
		this.VIN = VIN_in;
	}
	
	public String getVIN(){
		return VIN;
	}
	
	public void setMake(String make_in){
		this.make = make_in;
	}
	
	public String getMake(){
		return make;
	}
	
	public void setModel(String model_in){
		this.model = model_in;
	}
	
	public String getModel(){
		return model;
	}
	
	public void setPrice(int price_in){
		this.price = price_in;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setMileage(int mileage_in){
		this.mileage = mileage_in;
	}
	
	public int getMileage(){
		return mileage;
	}
	
	public void setColor(String color_in){
		this.color = color_in;
	}
	
	public String getColor(){
		return color;
	}
	
	

}