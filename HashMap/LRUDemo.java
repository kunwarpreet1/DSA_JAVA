public class LRUDemo{
	public static void main(String args[]){
		LRUCache lru = new LRUCache(3);
		lru.put(1,2);
		lru.put(2,3);
		lru.put(3,5);


		int value = lru.get(3);
		System.out.println(value + lru.size());
	}
}