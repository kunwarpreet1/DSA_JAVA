import java.util.HashMap;
//Implement LRU Cache.
public class LRUCache{
	//using doubly Linked List for all operations to be in:
	//put - O(1)
	//Get - O(1)
	//Update - O(1)
	//Size - O(1)
	//  head        Tail 
	// [-1,-1] -> [-1,-1]

	//making all variables to public is a good practise.
	class DLLNode{
		public int key;
		public int value;
		public DLLNode next;
		public DLLNode prev;

		//constructor of doubly LL (DLLNode)
		public DLLNode(int key,int value){
			this.key = key;
			this.value = value;
			this.prev = null;
			this.next = null;
		}

 	}

 	HashMap<Integer,DLLNode> storageMap = new HashMap<>();
 	int currentSize;
 	int maxCapacity;
 	private DLLNode head;
	private DLLNode tail;


 	//Constructor of LRU Cache main Class.
	public LRUCache(int capacity){
		head = new DLLNode(-1,-1);
		tail = new DLLNode(-1,-1);
		head.next = tail;
		tail.prev = head;
		maxCapacity = capacity;
		currentSize = 0;
	}

	//Helper 
	private boolean isCacheFull(){
		return this.maxCapacity == this.currentSize;
	}

	//Helper 
	private void remove(DLLNode node){
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.prev = null;
		node.next = null;
		this.currentSize--;
	}

	//Helper 
	private void addLast(DLLNode node){
		node.next = tail;
    	node.prev = tail.prev;
    	tail.prev.next = node;
    	tail.prev = node;
    	this.currentSize++;
	}

	//getting the value from cache.
	public int get(int key) {
		if(storageMap.containsKey(key)){
			DLLNode temp = storageMap.get(key);
       		remove(temp);
       		addLast(temp);
       		return temp.value;
		}
      return -1;
    }

    // putting this value in cache.
    public void put(int key, int value) {

    	if(storageMap.containsKey(key)){
    		DLLNode temp = storageMap.get(key);
    		temp.value = value;
    		remove(temp);
    		addLast(temp);
    	}else{
    		DLLNode newNode = new DLLNode(key,value);
    		if(isCacheFull()){
    			storageMap.remove(head.next.key);// removing from map first if call remove first then head.next will be changed by then.
    			remove(head.next);
    		}
    		storageMap.put(key,newNode);
    		addLast(newNode);
    	}
        
    }

    public int size(){
    	return this.currentSize;
    }

}