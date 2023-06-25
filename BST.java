import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {
	/* 
	 * TODO: Add instance variables 
	 * You may add any instance variables you need, but 
	 * you may NOT use any class that implements java.util.SortedMap
	 * or any other implementation of a binary search tree
	 */
	
	Node<K, V> root;
	Node<K, V> leaf;
	private int size;
	
	public BST() {
		root = new Node<>();
		leaf = new Node<>();
		root.left = leaf;
		root.right = leaf;
		leaf.left = leaf;
		leaf.right = leaf;
		
	}

	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		Node<K, V> p = root, current = root.right;
		boolean dir = true;
		
		while (current != leaf) {
			p = current;
			if (key.compareTo(current.getKey()) > 0) {
				current = current.right;
				dir = true;
			}
			else if (key.compareTo(current.getKey()) < 0) {
				current = current.left;
				dir = false;
			}
			else {
				return false;		
			}
		}
		
		if (dir) {
			 p.right = new Node<>(key, value, leaf, leaf);
		}
		else {
			p.left = new Node<>(key, value, leaf, leaf);
		}
		
		size++;
		
		return true;
					
	}

	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		Node<K, V> curr = root.right;
		
		while (curr != leaf) {
			if (key.compareTo(curr.getKey()) < 0) {
				curr = curr.left;
			}
			else if (key.compareTo(curr.getKey()) > 0) {
				curr = curr.right;
			}
			else {
				curr.setValue(newValue);
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		Node<K, V> curr = root.right;
		Node<K, V> p = root;
		
		boolean dir = true;
		
		while (!key.equals(curr.getKey()) && curr != leaf ) {
			p = curr;
			
			if (key.compareTo(curr.getKey()) < 0) {
				curr = curr.left;
				dir = false;
			}
			
			else {
				curr = curr.right;
				dir = true;
			}
			
		}
		
		if (curr == leaf) {
			return false;
		}
		
		final Node<K, V> replace;
		
		if (curr.right == leaf) {
			replace = curr.left;
		}
		else if (curr.right.left == leaf) {
			replace = curr.right;
		}
		else {
			Node<K, V> temp = curr.right;
			
			while (temp.left.left != leaf) {
				temp = temp.left;
			}
			
			Node<K, V> success = temp.left;
			temp.left = success.right;
			success.left = curr.left;
			success.right = curr.right;
			replace = success;
		}
		
		if (dir == false) {
			p.left = replace;
		}
		else {
			p.right = replace;
		}
		
		size--;
		
		return true;
	}

	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		if (replace(key, value) == false) {
			put(key,value);
		}
	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		Node<K, V> curr = root.right;
		
		while (curr != leaf) {
			if (key.compareTo(curr.getKey()) < 0) {
				curr = curr.left;
			}
			
			else if (key.compareTo(curr.getKey()) > 0) {
				curr = curr.right;
			}
			
			else {
				return curr.getValue();
			}
		}
		
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		Node<K, V> curr = root.right;
		
		while (curr != leaf) {
			if (key.compareTo(curr.getKey()) < 0) {
				curr = curr.left;
			}
			
			else if (key.compareTo(curr.getKey()) > 0) {
				curr = curr.right;
			}
			else {
				return true;
			}
		}
		
		return false;
	}

	// Keys must be in ascending sorted order
	// You CANNOT use Collections.sort() or any other sorting implementations
	// You must do inorder traversal of the tree
	@Override
	public List<K> keys() {
		List<K> lst = new ArrayList<>();
		
		Node<K, V> current = root.right;
		Stack<Node<K, V>> stack = new Stack<>();
		
		while (true) {
			while (current != leaf) {
				stack.push(current);
				current = current.left;
			}
			
			if (stack.isEmpty()) {
				break;
			}
			else {
				current = stack.pop();
				lst.add(current.getKey());
				current = current.right;
			}
		}
		
		return lst;		
	}
	
	private static class Node<K extends Comparable<? super K>, V> 
								implements DefaultMap.Entry<K, V> {
		/* 
		 * TODO: Add instance variables
		 */
		Node<K, V> right;
		Node<K, V> left;
		K key;
		V val;
		
		Node() {
			key = null;
			val = null;
		}
		
		Node(K key, V val) {
			this.key = key;
			this.val = val;
		}
		
		Node(K key, V val, Node<K, V> left, Node<K, V> right) {
			this.key = key;
			this.val = val;
			this.left = left;
			this.right = right;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return val;
		}

		@Override
		public void setValue(V value) {
			// TODO Auto-generated method stub
			this.val = value;
		}
		
		
	}
	 
}