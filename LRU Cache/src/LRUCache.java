import java.util.HashMap;

/**
 * An implementation of <tt>Cache</tt> that uses a least-recently-used (LRU)
 * eviction policy.
 */
public class LRUCache<T, U> implements Cache<T, U> {
	
	//fields
	private int _numMisses;
	final private DataProvider<T, U> _provider;
	final private HashMap<T, Node<T, U>> hashMap = new HashMap<T, Node<T, U>>();
	
	//Doubly LinkedList
	final private int _capacity;
	private Node<T, U> _head, _tail;
	
	/**
	 * @param provider the data provider to consult for a cache miss
	 * @param capacity the exact number of (key,value) pairs to store in the cache
	 */
	public LRUCache (DataProvider<T, U> provider, int capacity) {
		_provider = provider;
		_capacity = capacity;
		_head = _tail = null;
		_numMisses = 0;
	}

	/**
	 * Returns the value associated with the specified key.
	 * @param key the key
	 * @return the value associated with the key
	 */
	final public U get (T key) {
		if(hashMap.containsKey(key)) {
			Node<T, U> node = hashMap.get(key);
			evictNode(node);		//must evict the node to prevent duplicates
			addAtHead(node);		//place node at front of LinkedList
			return node._data;	//LRU hash is unchanged
		}
		else {					//if not found in cache, must ask data provider
			_numMisses++;
			Node<T, U> node = new Node<T, U>(_provider.get(key),key, null, null);
			if (hashMap.size() < _capacity) {
				addAtHead(node);	
				hashMap.put(key, node);
			}
			else {
				hashMap.remove(_tail._key);
				evictNode(_tail);
				
				addAtHead(node);
				hashMap.put(key, node);
			}
			return node._data;	
		}
	}
	
	/**
	 * Removes a node from *any position of the linked list
	 * @param node
	 */
	final private void evictNode(Node<T, U> node) {
		if (node._previous == null) {
			_head = node._next;		//if node is at head, replace it with next node
		}
		else {
			(node._previous)._next = node._next; // fill gaps in Linked list where node was
		}
		if (node._next == null) {
			_tail = node._previous;	//if node is at tail, replace it with previous node
		}
		else {
			(node._next)._previous = node._previous; //again we fill gaps
		}	
	}
	
	/**
	 * Add a node to the front of the LinkedList
	 * @param node
	 */
	final private void addAtHead(Node<T, U> node) {
		node._next = _head;
		node._previous = null;
		
		if (_head != null) {
			_head._previous = node; //put trace of node in front of the head if it exists
		}
		_head = node;				//place node at head
		
		if (_tail == null) {			
			_tail = _head;			//this will take into effect if there is only one node in the LinkedList
		}
	}

	/**
	 * Returns the number of cache misses since the object's instantiation.
	 * @return the number of cache misses since the object's instantiation.
	 */
	final public int getNumMisses () {
		return _numMisses; //TODO -- implement!
	}
}


