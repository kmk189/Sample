public class LinkedList<T> {
	
	private LinkedNode<T> listHead;
	
	public LinkedList() {
		
		setListHead(new LinkedNode<T>());
	}
	
	public LinkedList(T obj) {
		
		setListHead(new LinkedNode<T>(obj));
	}
	
	public LinkedNode<T> getListHead() {
		
		return listHead;
	}
	
	protected void setListHead(LinkedNode<T> node) {
		
		listHead = node;
	}
	
	public void add(T obj) {
		
		LinkedNode<T> oldHead = getListHead();
		LinkedNode<T> newHead = new LinkedNode<T>(obj);
		setListHead(newHead);
		newHead.setNextNode(oldHead);
		oldHead.setLastNode(newHead);
	}
	
	public String toString() {
		
		LinkedNode<T> node = getListHead();
		String str = "";
		
		while (node.hasData()) {
			str += (node.getData().toString() + "\n");
			node = node.getNextNode();
		}
		
		return str;
	}
}