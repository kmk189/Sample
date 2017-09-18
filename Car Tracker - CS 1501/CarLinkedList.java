public class CarLinkedList extends LinkedList<Car> {
	
	public CarLinkedList() {
		setListHead(new LinkedNode<Car>());
	}
	
	public CarLinkedList(Car car) {
		setListHead(new LinkedNode<Car>(car));
	}
	
	public Car removeByVIN(String vin) {
		LinkedNode<Car> previousNode = null;
		LinkedNode<Car> currentNode = getListHead();
		
		while (currentNode.hasData()) {
			Car listCar = currentNode.getData();
			String listVIN = listCar.getVIN();
			
			if (vin.compareTo(listVIN) == 0) {
				LinkedNode<Car> nextNode = currentNode.getNextNode();
				if (previousNode == null) {
					setListHead(nextNode);
				}
				else {
					previousNode.setNextNode(nextNode);
				}
				nextNode.setLastNode(previousNode);
				return listCar;
			}
			else {
				previousNode = currentNode;
				currentNode = currentNode.getNextNode();
			}
		}
		return null;
	}
	
	public Car updatePrice(String vin, int price) {
		LinkedNode<Car> currentNode = getListHead();
		
		while (currentNode.hasData()) {
			Car listCar = currentNode.getData();
			String listVIN = listCar.getVIN();
			
			if (vin.compareTo(listVIN) == 0) {
				listCar.setPrice(price);
				currentNode.setData(listCar);
				return listCar;
			}
			else {
				currentNode = currentNode.getNextNode();
			}
		}
		return null;
	}
	
	public Car updateMileage(String vin, int mileage) {
		LinkedNode<Car> currentNode = getListHead();
		
		while (currentNode.hasData()) {
			Car listCar = currentNode.getData();
			String listVIN = listCar.getVIN();
			
			if (vin.compareTo(listVIN) == 0) {
				listCar.setMileage(mileage);
				currentNode.setData(listCar);
				return listCar;
			}
			else {
				currentNode = currentNode.getNextNode();
			}
		}
		return null;
	}
	
	public Car updateColor(String vin, String color) {
		LinkedNode<Car> currentNode = getListHead();
		
		while (currentNode.hasData()) {
			Car listCar = currentNode.getData();
			String listVIN = listCar.getVIN();
			
			if (vin.compareTo(listVIN) == 0) {
				listCar.setColor(color);
				currentNode.setData(listCar);
				return listCar;
			}
			else {
				currentNode = currentNode.getNextNode();
			}
		}
		return null;
	}
}