import java.util.HashMap;

public class IntProvider<T, U> implements DataProvider<T, U> {
	HashMap<Integer,Integer> hashMap;
	
	IntProvider(){	//package private
		hashMap = new HashMap<Integer, Integer>();
		hashMap.put(1, 111);
		hashMap.put(2, 222);
		hashMap.put(3, 333);
		hashMap.put(4, 444);
		hashMap.put(5, 555);
		hashMap.put(6, 666);
		hashMap.put(7, 777);
		hashMap.put(8, 888);
		hashMap.put(9, 999);

	}
	/**returns the data associated with a key
	 * @return the data associated with a key 
	 * @param key
	 */
	final public U get(T key) {
		return (U) hashMap.get(key);
	}
	
	
}
