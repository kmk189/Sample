public class BasicHeap {
	
	private Car[] heap;
	private char mode;
	private int nextLeaf;
	
	public BasicHeap(char md) {
		heap = new Car[32];
		mode = md;
	}
	
	public Car getMinimumCar() {
		return heap[0];
	}
	
	public void addCar(Car car) {
		if (nextLeaf == heap.length) {
			heap = resizeHeap(heap);
		}
		
		heap[nextLeaf] = car;
		floatCar();
		nextLeaf++;
	}
	
	private void floatCar() {
		int parentIndex = (int)Math.floor(nextLeaf / 2);
		
		if (getMode() == 'p') {
			floatByPrice(parentIndex, nextLeaf);
		}else {
			floatByMileage(parentIndex, nextLeaf);
		}
	}
	
	private void floatByPrice(int parentIndex, int insertedIndex) {
		if (parentIndex < 0) {
			return;
		}
		
		int parentPrice = heap[parentIndex].getPrice();
		int insertedPrice = heap[insertedIndex].getPrice();
		
		if (parentPrice > insertedPrice) {
			Car tempCar = heap[parentIndex];
			heap[parentIndex] = heap[insertedIndex];
			heap[insertedIndex] = tempCar;
			
			insertedIndex = parentIndex;
			parentIndex = (int)Math.floor((parentIndex - 1) / 2);
			
			floatByPrice(parentIndex, insertedIndex);
		}
	}
	
	private void floatByMileage(int parentIndex, int insertedIndex) {
		if (parentIndex < 0) {
			return;
		}
		
		int parentMileage = heap[parentIndex].getMileage();
		int insertedMileage = heap[insertedIndex].getMileage();
		
		if (parentMileage > insertedMileage) {
			Car tempCar = heap[parentIndex];
			heap[parentIndex] = heap[insertedIndex];
			heap[insertedIndex] = tempCar;
			
			insertedIndex = parentIndex;
			parentIndex = (int)Math.floor((parentIndex - 1) / 2);
			
			floatByMileage(parentIndex, insertedIndex);
		}
	}
	
	public char getMode() {
		return mode;
	}
	
	private Car[] resizeHeap(Car[] oldHeap) {
		int doubledSize = oldHeap.length * 2;
		Car[] newHeap = new Car[doubledSize];
		
		for (int i = 0; i < oldHeap.length; i++) {
			newHeap[i] = oldHeap[i];
		}
		
		return newHeap;
	}
	
	public String toString() {
		String heapDump = "";
		
		if (nextLeaf == 0) {
			heapDump = "Nothing in the heap";
		}else if (getMode() == 'p') {
			for (int i = 0; i < nextLeaf; i++) {
				heapDump += ("$" + heap[i].getPrice() + " - " + heap[i].getMake() + " " + heap[i].getModel() + "\n");
			}
		}else {
			for (int i = 0; i < nextLeaf; i++) {
				heapDump += (heap[i].getMileage() + " mi. - " + heap[i].getMake() + " " + heap[i].getModel() + "\n");
			}
		}
		
		return heapDump;
	}
}