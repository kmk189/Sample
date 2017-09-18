public class LinkedNode<T> {
	
	private T data;
	private LinkedNode<T> nextNode;
	private LinkedNode<T> lastNode;
	
	public LinkedNode() {
		setData(null);
		setNextNode(null);
		setLastNode(null);
	}
	
	public LinkedNode(T obj) {
		setData(obj);
		setNextNode(null);
		setLastNode(null);
	}
	
	public void setData(T obj) {
		data = obj;
	}
	
	public T getData() {
		return data;
	}
	
	public boolean hasData() {
		return (getData() != null);
	}
	
	protected void setNextNode(LinkedNode<T> next) {
		nextNode = next;
	}
	
	protected void setLastNode(LinkedNode<T> last) {
		lastNode = last;
	}
	
	public boolean hasNextNode() {
		return (getNextNode() != null);
	}
	
	public LinkedNode<T> getNextNode() {	
		return nextNode;
	}
	
	public LinkedNode<T> getLastNode() {	
		return lastNode;
	}
}