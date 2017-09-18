package cs445.a5;

class TernaryNode<T>{

	private T data;
	private TernaryNode<T> leftChild;
	private TernaryNode<T> middleChild;
	private TernaryNode<T> rightChild;
	
	public TernaryNode(){
		this(null);
	}
	
	public TernaryNode(T dataPortion){
		this(dataPortion, null, null, null);
	}
	
	public TernaryNode(T dataPortion, TernaryNode<T> newLeftChild, 
						TernaryNode<T> newMiddleChild, TernaryNode<T> newRightChild){
		data = dataPortion;
		leftChild = newLeftChild;
		middleChild = newMiddleChild;
		rightChild = newRightChild;
	}
	
	public T getData(){
		return data;
	}
	
	public void setData(T newData){
		data = newData;
	}
	
	public TernaryNode<T> getLeftChild(){
		return leftChild;
	}
	
	public void setLeftChild(TernaryNode<T> newLeftChild){
		leftChild = newLeftChild;
	}
	
	public boolean hasLeftChild(){
		return leftChild != null;
	}
	
	public TernaryNode<T> getMiddleChild(){
		return middleChild;
	}
	
	public void setMiddleChild(TernaryNode<T> newMiddleChild){
		middleChild = newMiddleChild;
	}
	
	public boolean hasMiddleChild(){
		return middleChild != null;
	}
	
	public TernaryNode<T> getRightChild(){
		return rightChild;
	}
	
	public void setRightChild(TernaryNode<T> newRightChild){
		rightChild = newRightChild;
	}
	
	public boolean hasRightChild(){
		return rightChild != null;
	}	
	
	public boolean isLeaf(){
		return(leftChild == null) && (middleChild == null) && (rightChild == null);
	}
	
	public int getNumberOfNodes(){
		int leftNumber = 0;
		int middleNumber = 0;
		int rightNumber = 0;
		
		if(leftChild != null){
			leftNumber = leftChild.getNumberOfNodes();
		}
		
		if(middleChild != null){
			middleNumber = middleChild.getNumberOfNodes();
		}
		
		if(rightChild != null){
			rightNumber = rightChild.getNumberOfNodes();
		}
		
		return 1 + leftNumber + middleNumber + rightNumber;
	}
	
	public int getHeight(){
		return getHeight(this);
	}
	
	public int getHeight(TernaryNode<T> node){
		int height = 0;
		
		if(node != null){
			int heightLeft = getHeight(node.getLeftChild());
			int heightMiddle = getHeight(node.getMiddleChild());
			int heightRight = getHeight(node.getRightChild());
			int heightA = Math.max(heightLeft, heightMiddle);
			int heightB = Math.max(heightA, heightRight);
		}
		
		return height;
	}
	
	public TernaryNode<T> copy(){
		TernaryNode<T> newRoot = new TernaryNode<>(data);
		
		if(leftChild != null){
			newRoot.setLeftChild(leftChild.copy());
		}
		
		if(middleChild != null){
			newRoot.setMiddleChild(middleChild.copy());
		}
		
		if(rightChild != null){
			newRoot.setRightChild(rightChild.copy());
		}
		
		return newRoot;
	}

}