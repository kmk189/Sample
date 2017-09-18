import java.util.Scanner;

public class CarTracker{
	
	
	private static CarHeap carPrices = new CarHeap('p');
	private static CarHeap carMileage = new CarHeap('m');
	
	private static CarTrie pricesTrie = new CarTrie();
	private static CarTrie mileageTrie = new CarTrie();
	
	private static HashTable carGroups = new HashTable();	
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		
		//Scanner sc = new Scanner(System.in);
		String menuChoice = "0";
		
		while(!menuChoice.equals("8")){
			System.out.println("");
			System.out.println("Please choose an option from the menu:");
			System.out.println("1. Add a Car");
			System.out.println("2. Update a Car");
			System.out.println("3. Remove a Car");
			System.out.println("4. Retrieve the Lowest Priced Car");
			System.out.println("5. Retrieve the Lowest Mileage Car");
			System.out.println("6. Retrieve the Lowest Priced Car by Make and Model");
			System.out.println("7. Retrieve the Lowest Mileage Car by Make and Model");
			System.out.println("8. Quit");
			
			menuChoice = sc.nextLine();
			
			switch(menuChoice){
				case "1":
					System.out.println("Add car");
					addCar();
					break;
				case "2":
					System.out.println("Update car");
					updateCar();
					break;
				case "3":
					System.out.println("Remove car");
					removeCar();
					break;
				case "4":
					System.out.println("Lowest price");
					lowestPrice();
					break;
				case "5":
					System.out.println("Lowest mileage");
					lowestMileage();
					break;
				case "6":
					System.out.println("Lowest price make and model");
					lowPriceMM();
					break;
				case "7":
					System.out.println("Lowest mileage make and model");
					lowMileMM();
					break;
				case "8":
					System.out.println("See ya later, alligator!");
					break;
				default:
					System.out.println("Not a valid option.");
					
			}
			
		}
		
	}
	
	public static void addCar(){
		
		String VIN, make, model, color;
		int price, mileage;
		
		System.out.println("Please enter the VIN of the new car:");
		VIN = sc.nextLine().toUpperCase();
		
		while(VIN.length() != 17 || !VIN.matches("[0-9A-Z&&[^IOQ]]*")){
			System.out.println("VIN must be 17 characters long and may not contain I, O, or Q");
			System.out.println("Please enter the VIN of the new car:");
			VIN = sc.nextLine().toUpperCase();
		}
		
		System.out.println("Please enter the new car's make (e.g. Ford, Nissan): ");
		make = sc.nextLine();
		
		System.out.println("Please enter the new car's model (e.g. Fiesta, Sentra):");
		model = sc.nextLine();
		
		System.out.println("Please enter the new car's price in whole dollars:");
		price = sc.nextInt();
		
		System.out.println("Please enter the new car's mileage:");
		mileage = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Please enter the new car's color:");
		color = sc.nextLine();
		
		Car c = new Car(VIN, make, model, price, mileage, color);
		carPrices.addCar(c, pricesTrie);
		carMileage.addCar(c, mileageTrie);
		carGroups.insertCar(c);
		System.out.println("New car added!");
	}
	
	public static void updateCar(){
		System.out.println("Please enter the VIN of the car to update:");
		String VIN = sc.nextLine().toUpperCase();
		
		while(VIN.length() != 17 || !VIN.matches("[0-9A-Z&&[^IOQ]]*")){
			System.out.println("VIN must be 17 characters long and may not contain I, O, or Q");
			System.out.println("Please enter the VIN of the car to update:");
			VIN = sc.nextLine().toUpperCase();
		}

		int pricesHeap = pricesTrie.getHeapIndex(VIN);
		int milesHeap = mileageTrie.getHeapIndex(VIN);
		
		if (pricesHeap < 0 || milesHeap < 0) {
			System.out.println("The car with VIN, " + VIN + ", does not exist in the system");
			return;
		}

		
		System.out.println("Select a part of the car to update:");
		System.out.println("1. Update the price.");
		System.out.println("2. Update the mileage.");
		System.out.println("3. Update the color.");
		System.out.println("4. Nevermind, don't update anything");
		int choice = sc.nextInt();
		
		switch(choice){
			case 1:
				System.out.println("Please enter the car's updated price in whole dollars:");
				int price = sc.nextInt();
				Car car = carPrices.getCar(pricesHeap);
				car.setPrice(price);
				carPrices.setCar(car, pricesHeap);
				carPrices.checkCarUpdates(pricesHeap, pricesTrie);
				carMileage.setCar(car, milesHeap);
				carGroups.updatePrice(car, price);
				System.out.println("The car's price was updated!");
				break;
			case 2:
				System.out.println("Please enter the car's updated mileage:");
				int miles = sc.nextInt();
				car = carMileage.getCar(milesHeap);
				car.setMileage(miles);
				carMileage.setCar(car, milesHeap);
				carMileage.checkCarUpdates(milesHeap, mileageTrie);
				carPrices.setCar(car, pricesHeap);
				carGroups.updateMileage(car, miles);				
				System.out.println("The car's mileage was updated!");
				break;
			case 3:
				System.out.println("Please input the car's updated color:");
				String color = sc.nextLine();
				car = carPrices.getCar(pricesHeap);
				car.setColor(color);
				carPrices.setCar(car, pricesHeap);
				carMileage.setCar(car, milesHeap);
				carGroups.updateColor(car, color);
				System.out.println("The car's color has been updated!");
				break;
			case 4:
				System.out.println("Returning to main menu!");
				return;
			default:
				System.out.println(choice + " was an invalid choice.");
				return;
		}
	}
	
	public static void removeCar(){
		System.out.println("Please enter the VIN of the car to remove:");
		String VIN = sc.nextLine().toUpperCase();
		
		while(VIN.length() != 17 || !VIN.matches("[0-9A-Z&&[^IOQ]]*")){
			System.out.println("VIN must be 17 characters long and may not contain I, O, or Q");
			System.out.println("Please enter the VIN of the car to remove:");
			VIN = sc.nextLine().toUpperCase();
		}
	
		int pricesHeap = pricesTrie.getHeapIndex(VIN);
		int milesHeap = mileageTrie.getHeapIndex(VIN);
		
		if (pricesHeap < 0 || milesHeap < 0) {
			System.out.println("The car with VIN, " + VIN + ", is not in the system.");
			return;
		}
		
		Car car = carPrices.getCar(pricesHeap);
		pricesTrie.removeHeapIndex(VIN);
		carPrices.removeCar(pricesHeap, pricesTrie);
		mileageTrie.removeHeapIndex(VIN);
		carMileage.removeCar(milesHeap, mileageTrie);
		carGroups.removeCar(car);
		
		System.out.println("The car has been removed from the system");
	}
	
	public static void lowestPrice(){
		Car car = carPrices.getMinimumCar();
		if (car == null) {
			System.out.println("There are no cars in the system yet.");
		}else {
			System.out.println("Lowest priced car:");
			printCar(car);
		}
		
	}
	
	public static void lowestMileage(){
		Car car = carMileage.getMinimumCar();
		
		if (car == null) {
			System.out.println("There are no cars in the system yet.");
		}else {
			System.out.println("Lowest mileage car:");
			printCar(car);
		}
	}
	
	public static void lowPriceMM(){
		System.out.println("Enter the make of the car you want to find: ");
		String make = sc.nextLine();
		
		System.out.println("Enter the model of the car you want to find: ");
		String model = sc.nextLine();
		
		CarLinkedList list = carGroups.getList(make, model);
		
		if (list == null) {
			System.out.printf("The entered car is not in the system");
			return;
		}
		
		LinkedNode<Car> node = list.getListHead();
		BasicHeap tempHeap;
		tempHeap = new BasicHeap('p');
		
		if (node == null || !node.hasData()) {
			System.out.printf("The entered car is not in the system");
			return;
		}
		
		while (node.hasData()) {
			tempHeap.addCar(node.getData());
			node = node.getNextNode();
		}
			
		System.out.println("The lowest price " + make + " " + model + " is:");
		printCar(tempHeap.getMinimumCar());
	}
	
	public static void lowMileMM(){
		System.out.println("Enter the make of the car you want to find: ");
		String make = sc.nextLine();
		
		System.out.println("Enter the model of the car you want to find: ");
		String model = sc.nextLine();
		
		CarLinkedList list = carGroups.getList(make, model);
		
		if (list == null) {
			System.out.printf("The entered car is not in the system");
			return;
		}
		
		LinkedNode<Car> node = list.getListHead();
		BasicHeap tempHeap;
		tempHeap = new BasicHeap('m');
		
		if (node == null || !node.hasData()) {
			System.out.printf("The entered car is not in the system");
			return;
		}
		
		while (node.hasData()) {
			tempHeap.addCar(node.getData());
			node = node.getNextNode();
		}
			
		System.out.println("The lowest price " + make + " " + model + " is:");
		printCar(tempHeap.getMinimumCar());		
	}
	
	private static void printCar(Car car) {
		System.out.println("");
		System.out.println("VIN: " + car.getVIN());
		System.out.println("Make: " + car.getMake());
		System.out.println("Model: " + car.getModel());
		System.out.println("Price: " + car.getPrice());
		System.out.println("Mileage: " + car.getMileage());
		System.out.println("Color: " + car.getColor());
	}
	
}