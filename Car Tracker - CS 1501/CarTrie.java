public class CarTrie {
	
	private TrieNode trieHead;
	
	public CarTrie() {
		trieHead = new TrieNode();
	}
	
	public void storeHeapIndex(String vin, int heapIndex) {
		TrieNode trieNode = trieHead;
		
		for (int i = 0; i < vin.length(); i++) {
			int trieIndex = translateCharToInt(vin.charAt(i));
			if (trieNode.visitTrieIndex(trieIndex) == null) {
				trieNode.setTrieNode(trieIndex);
			}
			trieNode = trieNode.visitTrieIndex(trieIndex);
		}
		trieNode.setHeapIndex(heapIndex);
	}
	
	public int getHeapIndex(String vin) {
		TrieNode trieNode = trieHead;
		
		for (int i = 0; i < vin.length(); i++) {
			int trieIndex = translateCharToInt(vin.charAt(i));
			if (trieNode.visitTrieIndex(trieIndex) == null) {
				return -1;
			}
			trieNode = trieNode.visitTrieIndex(trieIndex);
		}
		return trieNode.getHeapIndex();
	}
	
	public void removeHeapIndex(String vin) {
		TrieNode trieNode = trieHead;
		
		for (int i = 0; i < vin.length(); i++) {
			int trieIndex = translateCharToInt(vin.charAt(i));
			if (trieNode.visitTrieIndex(trieIndex) == null) {
				System.out.println("The entered VIN does not exist!");
				return;
			}
			trieNode = trieNode.visitTrieIndex(trieIndex);
		}
		trieNode.setHeapIndex(-1);
	}
	
	private int translateCharToInt(char ch) {
		switch (ch) {
			case '0':
				return 0;
			case '1':
				return 1;
			case '2':
				return 2;
			case '3':
				return 3;
			case '4':
				return 4;
			case '5':
				return 5;
			case '6':
				return 6;
			case '7':
				return 7;
			case '8':
				return 8;
			case '9':
				return 9;
			case 'A':
				return 10;
			case 'B':
				return 11;
			case 'C':
				return 12;
			case 'D':
				return 13;
			case 'E':
				return 14;
			case 'F':
				return 15;
			case 'G':
				return 16;
			case 'H':
				return 17;
			case 'J':
				return 18;
			case 'K':
				return 19;
			case 'L':
				return 20;
			case 'M':
				return 21;
			case 'N':
				return 22;
			case 'P':
				return 23;
			case 'R':
				return 24;
			case 'S':
				return 25;
			case 'T':
				return 26;
			case 'U':
				return 27;
			case 'V':
				return 28;
			case 'W':
				return 29;
			case 'X':
				return 30;
			case 'Y':
				return 31;
			case 'Z':
				return 32;
			default:
				return 0;
		}
	}
}