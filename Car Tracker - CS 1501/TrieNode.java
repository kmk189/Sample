public class TrieNode {
	
	int indexOfVIN;
	TrieNode[] trieNode;
	
	public TrieNode() {
		trieNode = new TrieNode[33];
		setHeapIndex(-1);
	}
	
	public void setHeapIndex(int index) {
		indexOfVIN = index;
	}
	
	public int getHeapIndex() {
		return indexOfVIN;
	}
	
	public TrieNode visitTrieIndex(int index) {
		return trieNode[index];
	}
	
	public void setTrieNode(int index) {
		trieNode[index] = new TrieNode();
	}
}