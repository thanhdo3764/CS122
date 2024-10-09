
/**
 * A Binary Search Tree is a Binary Tree with the following
 * properties:
 * 
 * For any node x: (1) the left child of x compares "less than" x; 
 * and (2) the right node of x compares "greater than" x
 *
 *
 * @param <T> the type of data object stored by the BST's Nodes
 */
public class BST<T extends Comparable<T>> {

	/**
	 * The root Node is a reference to the 
	 * Node at the 'top' of a QuestionTree
	 */
	private Node<T> root;
	
	
	/**
	 * Construct a BST
	 */
	public BST() {
		root = null;
	}
	
	/**
	 * @return the root of the BST
	 */
	public Node<T> getRoot() { 
		return root;
	}
	
	/**
	 * _Part 1: Implement this method._
	 *
	 * Add a new piece of data into the tree. To do this, you must:
	 * 
	 * 1) If the tree has no root, create a root node 
	 *    with the supplied data
	 * 2) Otherwise, walk the tree from the root to the bottom
	 *    (i.e., a leaf) as though searching for the supplied data.
	 * Then:
	 * 3) Add a new Node to the leaf where the search ended.
	 *
	 * @param data - the data to be added to the tree
	 */
	public void add(T data) {
		// For the first node added
		if (root == null) {
			root = new Node<T>(data);
		} else {
			Node<T> cursor = root;
			while(true) {
				// If data is less than cursor.data
				if (data.compareTo(cursor.data) < 0) {
					// Add to left if there's no left
					if (cursor.left == null) {
						cursor.left = new Node<>(data);
						cursor.left.parent = cursor;
						break;
					} else {
						cursor = cursor.left;
					}
				// If data is greater than cursor.data
				} else if (data.compareTo(cursor.data) > 0) {
					// Add to right if there's no right
					if (cursor.right == null) {
						cursor.right = new Node<>(data);
						cursor.right.parent = cursor;
						break;
					} else {
						cursor = cursor.right;
					}
				}
			}
		}
	}
	

	/**
	 * _Part 2: Implement this method._
	 * 
	 * Find a Node containing the specified key and
	 * return it.  If such a Node cannot be found,
	 * return null.  This method works by walking
	 * through the tree starting at the root and
	 * comparing each Node's data to the specified 
	 * key and then branching appropriately.
	 * 
	 * @param key - the data to find in the tree
	 * @return a Node containing the key data, or null.
	 */
	public Node<T> find(T key) {
		Node<T> cursor = root;
		while(true) {
			// If key is less than cursor.data, continue left
			if (key.compareTo(cursor.data) < 0) {
				if (cursor.left != null) cursor = cursor.left;
				else break;
			// If key is greater than cursor.data, continue right
			} else if (key.compareTo(cursor.data) > 0) {
				if (cursor.right != null) cursor = cursor.right;
				else break;
			} else {
				return cursor;
			}
		}
		return null;
	}

	/**
	 * _Part 3: Implement this method._
	 *  
	 * @return the Node with the largest value in the BST
	 */
	public Node<T> maximum() {
		// Finds the right-most leaf
		Node<T> cursor = root;
		while(cursor.right != null) {
			cursor = cursor.right;
		}
		return cursor;
	}
	
	/**
	 * _Part 4: Implement this method._
	 *  
	 * @return the Node with the smallest value in the BST
	 */
	public Node<T> minimum() {
		// Finds the left-most leaf
		Node<T> cursor = root;
		while(cursor.left != null) {
			cursor = cursor.left;
		}
		return cursor;
	}

	/**
	 * _Part 5: Implement this method._
	 *
	 *  Assuming no two nodes contain equal data in the tree,
	 *  this method takes a node n with data d, and should
	 *  return the node that stores the value which appears
	 *  immediately after d in the sorted order, or null
	 *  if no such node exists.
	 *
	 *  Thus, for a tree containing data: 1, 3, 5, 10 (regardless
	 *  of the order they were added) the successor of the node
	 *  containing 1 should be the node containing 3.
	 *
	 * @param n a node in the BST
	 * @return the Node in the BST whose value is next in the sorted ordering
	 *   of all keys, or null if no such node exists
	 */
	public Node<T> successor(Node<T> n) {
		T temp = n.data;
		// Case when n has a right child
		if (n.right != null) {
			Node<T> cursor = n.right;
			while (cursor.left != null) {
				cursor = cursor.left;
			}
			return cursor;
		}
		// Case when n doesn't have a right child but has a parent
		if (n.parent != null) {
			// Case when n is the left child
			if (n == n.parent.left) {
				return n.parent;
			}
			// Case when n is the right child
			if (n == n.parent.right) {
				Node<T> cursor = n.parent;
				while (temp.compareTo(cursor.data) > 0) {
					if (cursor.parent != null) cursor = cursor.parent;
					else return null;
				}
				return cursor;
			}
		}
		return null;
	}
	/**
	 * _Part 6: Extra Credit! Implement this method._
	 *  
	 * Searches for a Node with the specified key.
	 * If found, this method removes that node
	 * while maintaining the properties of the BST.
	 * 
	 * Note that the CLRS TreeDelete pseudo code
	 * nearly meets the requirements of this method, 
	 * however, it does not always return the correct
	 * value; your implementation should return a
	 * a reference to the removed Node.
	 *
	 * @return the Node that was removed or null.
	 */
	public Node<T> remove(T data) {
		Node<T> z = find(data);
		if (z==null) return null;
		Node<T> y;
		Node<T> x;
		if (z.left == null || z.right == null) {
			y = z;
		} else {
			y = successor(z);
		}
		if (y.left != null) {
			x = y.left;
		} else {
			x = y.right;
		}
		if (x != null) {
			x.parent = y.parent;
		}
		if (y.parent == null) {
			root = x;
		} else if (y == y.parent.left){
			y.parent.left = x;
		} else {
			y.parent.right = x;
		}
		if (y != z) {
			y.parent = z.parent;
			y.left = z.left;
			y.right = z.right;
			if (z.parent != null) {
				if (z == z.parent.left) z.parent.left = y;
				else z.parent.right = y;
			} else {
				root = y;
			}
			if (z.left != null) z.left.parent = y;
			if (z.right != null) z.right.parent = y;
		}
		z.left = null;
		z.right = null;
		z.parent = null;
		return z;
	}
	
	/**
	 * 
	 * The Node class for our BST.  The BST
	 * as defined above is constructed from zero or more
	 * Node objects. Each object has between 0 and 2 children
	 * along with a data member that must implement the
	 * Comparable interface.
	 * 
	 * @param <T>
	 */
	public static class Node<T extends Comparable<T>> {
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;
		private T data;
		
		private Node(T d) {
			data = d;
			parent = null;
			left = null;
			right = null;
		}
		public Node<T> getParent() { return parent; }
		public Node<T> getLeft() { return left; }
		public Node<T> getRight() { return right; }
		public T getData() { return data; }
	}
}
