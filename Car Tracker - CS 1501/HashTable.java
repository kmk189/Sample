public class HashTable {
	
	private CarLinkedList[] carsArray;	
	
	public HashTable() {
		
		carsArray = (CarLinkedList[])new CarLinkedList[269];
	}
	
	public void insertCar(Car car) {
		
		int hashIndex = generateHashIndex(car);
		placeInArray(car, hashIndex);
	}
	
	public void removeCar(Car car) {
		
		int hashIndex = generateHashIndex(car);
		removeFromArray(car, hashIndex);
	}
	
	public Car updatePrice(Car car, int price) {
		
		int hashIndex = generateHashIndex(car);
		String vin = car.getVIN();
		CarLinkedList list = carsArray[hashIndex];
		return list.updatePrice(vin, price);
	}
	
	public Car updateMileage(Car car, int mileage) {
		
		int hashIndex = generateHashIndex(car);
		String vin = car.getVIN();
		CarLinkedList list = carsArray[hashIndex];
		return list.updateMileage(vin, mileage);
	}
	
	public Car updateColor(Car car, String color) {
		
		int hashIndex = generateHashIndex(car);
		String vin = car.getVIN();
		CarLinkedList list = carsArray[hashIndex];
		return list.updateColor(vin, color);
	}
	
	public CarLinkedList getList(String make, String model) {
		
		int hashIndex = generateHashIndex(make, model);
		return carsArray[hashIndex];
	}
	
	private int generateHashIndex(Car car) {
		
		String make = car.getMake();
		String model = car.getModel();
		
		make = make.substring(0, 1);
		
		if (model.length() < 5) {
			while (model.length() < 5) {
				model = model.concat(" ");
			}
		}
		else {
			model = model.substring(0, 5);
		}
		
		String carName = make.concat(model);
		
		long hashSum = 0;
		
		for (int i = 0; i < carName.length(); i++) {
			int ascii = (int)carName.charAt(i);
			hashSum *= 256;	
			hashSum += ascii;
		}
		return (int)(hashSum % 269);
	}
	
	private int generateHashIndex(String make, String model) {
		
		make = make.substring(0, 1);
		
		if (model.length() < 5) {
			while (model.length() < 5) {
				model = model.concat(" ");
			}
		}
		else {
			model = model.substring(0, 5);
		}
		
		String carName = make.concat(model);
		
		long hashSum = 0;
		
		for (int i = 0; i < carName.length(); i++) {
			int ascii = (int)carName.charAt(i);
			hashSum *= 256;
			hashSum += ascii;
		}
		return (int)(hashSum % 269);
	}
	
	private void placeInArray(Car car, int index) {
		
		if (carsArray[index] == null) {	
			carsArray[index] = new CarLinkedList();
		}
		
		CarLinkedList list = carsArray[index];
		list.add(car);
		carsArray[index] = list;
	}
	
	private void removeFromArray(Car car, int index) {
		
		String vin = car.getVIN();
		CarLinkedList list = carsArray[index];
		list.removeByVIN(vin);
		carsArray[index] = list;
	}
	
	public String dumpLinkedList(Car car) {
		
		int hashIndex = generateHashIndex(car);
		CarLinkedList list = carsArray[hashIndex];

		return list.toString();
	}
}