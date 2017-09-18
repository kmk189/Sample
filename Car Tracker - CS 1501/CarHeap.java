public class CarHeap {
	
	private Car[] heap;
	private char mode;
	private int nextLeaf;
	
	public CarHeap(char md) {
		heap = new Car[32];
		mode = md;
		nextLeaf = 0;
	}
	
	public Car getMinimumCar() {
		return heap[0];
	}
	
	public void addCar(Car car, CarTrie trie) {
		if (nextLeaf == heap.length) {
			heap = resizeHeap(heap);
		}
		
		heap[nextLeaf] = car;
		floatCar(trie);
		nextLeaf++;
	}
	
	public void removeCar(int index, CarTrie trie) {
		nextLeaf--;
		heap[index] = heap[nextLeaf];
		
		if (index == nextLeaf) {
			trie.removeHeapIndex(heap[index].getVIN());
			heap[index] = null;
			return;
		}else {
			heap[nextLeaf] = null;
			
			if (getMode() == 'p') {
				sinkByPrice(index, trie);
			}
			else {
				sinkByMileage(index, trie);
			}
		}
	}
	
	public Car getCar(int index) {
		return heap[index];
	}
	
	public void setCar(Car car, int index) {
		heap[index] = car;
	}
	
	public void checkCarUpdates(int index, CarTrie trie) {
		int parentIndex = (int)Math.floor((index - 1) / 2.0);
		
		if (getMode() == 'p') {
			floatByPrice(parentIndex, index, trie);
			sinkByPrice(index, trie);
		}else {
			floatByMileage(parentIndex, index, trie);
			sinkByMileage(index, trie);
		}
	}
	
	private void floatCar(CarTrie trie) {
		int parentIndex = (int)Math.floor((nextLeaf - 1) / 2.0);
		
		if (getMode() == 'p') {
			floatByPrice(parentIndex, nextLeaf, trie);
		}else {
			floatByMileage(parentIndex, nextLeaf, trie);
		}
	}
	
	private void floatByPrice(int parentIndex, int insertedIndex, CarTrie trie) {
		if (parentIndex < 0) {
			trie.storeHeapIndex(heap[0].getVIN(), 0);
			return;
		}
		
		double parentPrice = heap[parentIndex].getPrice();
		double insertedPrice = heap[insertedIndex].getPrice();
		
		if (parentPrice > insertedPrice) {
			Car tempCar = heap[parentIndex];
			heap[parentIndex] = heap[insertedIndex];			
			heap[insertedIndex] = tempCar;
			
			trie.storeHeapIndex(heap[insertedIndex].getVIN(), insertedIndex);
			trie.storeHeapIndex(heap[parentIndex].getVIN(), parentIndex);
			
			insertedIndex = parentIndex;
			parentIndex = (int)Math.floor((parentIndex - 1) / 2.0);
			
			floatByPrice(parentIndex, insertedIndex, trie);
		}else {
			trie.storeHeapIndex(heap[insertedIndex].getVIN(), insertedIndex);
		}
	}
	
	private void floatByMileage(int parentIndex, int insertedIndex, CarTrie trie) {
		if (parentIndex < 0) {
			trie.storeHeapIndex(heap[0].getVIN(), 0);
			return;
		}
		
		int parentMileage = heap[parentIndex].getMileage();
		int insertedMileage = heap[insertedIndex].getMileage();
		
		if (parentMileage > insertedMileage) {
			Car tempCar = heap[parentIndex];
			heap[parentIndex] = heap[insertedIndex];
			heap[insertedIndex] = tempCar;
			
			trie.storeHeapIndex(heap[insertedIndex].getVIN(), insertedIndex);
			trie.storeHeapIndex(heap[parentIndex].getVIN(), parentIndex);
			
			insertedIndex = parentIndex;
			parentIndex = (int)Math.floor((parentIndex - 1) / 2.0);
			
			floatByMileage(parentIndex, insertedIndex, trie);
		}else {
			trie.storeHeapIndex(heap[insertedIndex].getVIN(), insertedIndex);
		}
	}
	
	private void sinkByPrice(int parentIndex, CarTrie trie) {
		int leftIndex = 2 * parentIndex + 1;
		int rightIndex = 2 * parentIndex + 2;
		int comparedIndex;
		Car leftCar, rightCar;
		
		if (leftIndex > heap.length - 1) {
			leftCar = null;
		}else {
			leftCar = heap[leftIndex];
		}
		
		if (rightIndex > heap.length - 1) {
			rightCar = null;
		}else {
			rightCar = heap[rightIndex];
		}
		
		if (leftCar == null && rightCar == null) {
			trie.storeHeapIndex(heap[parentIndex].getVIN(), parentIndex);
			return;
		}else if (leftCar != null && rightCar == null) {
			comparedIndex = leftIndex;
		}else if (leftCar == null && rightCar != null) {
			comparedIndex = rightIndex;
		}else {
			double leftPrice = leftCar.getPrice();
			double rightPrice = rightCar.getPrice();
			
			if (leftPrice <= rightPrice) {
				comparedIndex = leftIndex;
			}else {
				comparedIndex = rightIndex;
			}
		}
		
		double parentPrice = heap[parentIndex].getPrice();
		double childPrice = heap[comparedIndex].getPrice();
		
		if (parentPrice > childPrice) {
			Car tempCar = heap[comparedIndex];
			heap[comparedIndex] = heap[parentIndex];
			heap[parentIndex] = tempCar;
			
			trie.storeHeapIndex(heap[comparedIndex].getVIN(), comparedIndex);
			trie.storeHeapIndex(heap[parentIndex].getVIN(), parentIndex);
			
			sinkByPrice(comparedIndex, trie);
		}else {
			trie.storeHeapIndex(heap[parentIndex].getVIN(), parentIndex);
		}
	}
	
	private void sinkByMileage(int parentIndex, CarTrie trie) {
		int leftIndex = 2 * parentIndex + 1;
		int rightIndex = 2 * parentIndex + 2;
		int comparedIndex;
		Car leftCar, rightCar;
		
		if (leftIndex > heap.length - 1) {
			leftCar = null;
		}else {
			leftCar = heap[leftIndex];
		}
		
		if (rightIndex > heap.length - 1) {
			rightCar = null;
		}else {
			rightCar = heap[rightIndex];
		}
		
		if (leftCar == null && rightCar == null) {
			trie.storeHeapIndex(heap[parentIndex].getVIN(), parentIndex);
			return;
		}else if (leftCar != null && rightCar == null) {
			comparedIndex = leftIndex;
		}else if (leftCar == null && rightCar != null) {
			comparedIndex = rightIndex;
		}else {
			int leftMileage = leftCar.getMileage();
			int rightMileage = rightCar.getMileage();
			
			if (leftMileage <= rightMileage) {
				comparedIndex = leftIndex;
			}else {
				comparedIndex = rightIndex;
			}
		}
		
		int parentMileage = heap[parentIndex].getMileage();
		int childMileage = heap[comparedIndex].getMileage();
		
		if (parentMileage > childMileage) {
			Car tempCar = heap[comparedIndex];
			heap[comparedIndex] = heap[parentIndex];
			heap[parentIndex] = tempCar;
			
			trie.storeHeapIndex(heap[comparedIndex].getVIN(), comparedIndex);
			trie.storeHeapIndex(heap[parentIndex].getVIN(), parentIndex);
			
			sinkByMileage(comparedIndex, trie);
		}else {
			trie.storeHeapIndex(heap[parentIndex].getVIN(), parentIndex);
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