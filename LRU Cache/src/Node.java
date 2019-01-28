
/**
 * Models the nodes of the doubly linked list.
 * T is the data type of the key
 * U is the data type of the data
 */
public class Node<T, U> {
	//all fields here are package protected
	final U _data;
	final T _key;
	Node<T, U> _next;
	Node<T, U> _previous;
	
	public Node (U data, T key, Node<T, U> next, Node<T, U> previous) {
		_data = data;
		_key = key;
		_next = next;
		_previous = previous;
	}
}
