package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
	private static final String SYMBOL = " ";
	private static final int NUMBER_SYMBOLS_PER_LEVEL = 5;
	private Node<T> root;
	private Comparator<T> comp;
	
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	
	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}
	
	static private class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;
		Node(T obj) {
			this.obj = obj;
		}
	}
	
	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = root;
		Node<T> prev;
		boolean flNext = false;
		
		TreeSetIterator() {
			if (current != null) {
				current = getLeastNode(root);
			}
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			prev = current;
			current = getNextCurrent(current);
			flNext = true;
			return res;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			removeNode(prev);
			flNext = false;
		}
	}
	
	@Override
	public boolean add(T element) {
		boolean isAdded = false;
		Node<T> parent = getNode(element);
		int compRes = 0;
		if(parent == null || (compRes = comp.compare(element, parent.obj)) != 0) {
			isAdded  = true;
			size++;
			Node<T> node = new Node<>(element);
			node.parent = parent;
			if(parent == null) {
				root = node;
			} else {
				if (compRes < 0) {
					parent.left = node;
				} else {
					parent.right = node;
				}
			}
		}
		return isAdded;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> node = getNode(pattern);
		boolean isDeleted = false;
		if (node != null && comp.compare(node.obj, pattern) == 0) {
			removeNode(node);
			isDeleted = true;
		}
		return isDeleted;
	}
	
	private void removeNode(Node<T> removableNode) {
		if (removableNode.left != null && removableNode.right != null) {
			Node<T> replacingNode = getGreatestNode(removableNode.left);
			removableNode.obj = replacingNode.obj;
			removableNode = replacingNode;
		}
		Node<T> parent = removableNode.parent;
		Node<T> child = getChild(removableNode);
		boolean isRightChild = parent != null ? parent.right == removableNode : true;
		connectNodes(parent, child, isRightChild);
		removableNode = null;
		size--;
	}
	
	private void connectNodes(Node<T> parent, Node<T> child, boolean isRight) {
		if (parent != null) {
			if (isRight) {
				parent.right = child;
			} else {
				parent.left = child;
			} 
		} else {
			root = child;
		}
		if (child != null) {
			child.parent = parent;
		}
	}
	private Node<T> getChild(Node<T> removableNode) {
		return removableNode.left == null ? removableNode.right : removableNode.left;
	}
	@Override
	public boolean contains(T element) {
		Node<T> node = getNode(element);
		return node != null && comp.compare(node.obj, element) == 0;
	}

	private Node<T> getNode(T element) {
		Node<T> current = null;
		Node<T> next = root;
		int compRes;
		while (next != null && (compRes = comp.compare(element, next.obj)) != 0) {
			current = next;
			next = compRes > 0 ? current.right : current.left;
		};
		return next == null ? current : next;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}
	
	private Node<T> getLeastNode(Node<T> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	private Node<T> getGreatestNode(Node<T> node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	private Node<T> getNextCurrent(Node<T> current) {
		return current.right == null ? getGreaterParent(current) : getLeastNode(current.right);
	}
	private Node<T> getPrevCurrent(Node<T> current) {
		return current.left == null ? getLessParent(current) : getGreatestNode(current.right);
	}
	private Node<T> getLessParent(Node<T> current) {
		while (current.parent != null && current == current.parent.left) {
			current = current.parent;
		}
		return current.parent;
	}
	private Node<T> getGreaterParent(Node<T> current) {
		while (current.parent != null && current == current.parent.right) {
			current = current.parent;
		}
		return current.parent;
	}
	@Override
	public T floor(T element) {
		
		return floorCeiling(element, true);
	}
	@Override
	public T ceiling(T element) {
		
		return floorCeiling(element, false);
	}
	private T floorCeiling(T pattern, boolean isFloor) {
		T res = null;
		int compRes = 0;
		Node<T> current = root;
		while (current != null && (compRes = comp.compare(pattern, current.obj)) != 0) {
			if ((compRes < 0 && !isFloor) || (compRes > 0 && isFloor) ) {
				res = current.obj;
			} 
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? res : current.obj;
		
	}
	@Override
	public T first() {
		return root == null ? null : getLeastNode(root).obj;
	}
	@Override
	public T last() {
		return root == null ? null : getGreatestNode(root).obj;
	}
	
	public void displayTreeRotated() {
		displayTreeRotated(root, 0);
	}

	private void displayTreeRotated(Node<T> root, int level) {
		if (root != null) {
			displayTreeRotated(root.right, level + 1);
			displayRoot(root, level);
			displayTreeRotated(root.left, level + 1);
		}
	}

	private void displayRoot(Node<T> root, int level) {
		System.out.printf("%s%s\n", SYMBOL.repeat(NUMBER_SYMBOLS_PER_LEVEL * level), root.obj);
	}

	public int height() {
		return height(root);
	}

	private int height(Node<T> root) {
		int res = 0;
		if (root != null) {
			int heightLeft = height(root.left);
			int heightRight = height(root.right);
			res = Math.max(heightLeft, heightRight) + 1;
		}
		return res;
	}

	public int width() {
		return width(root);
	}

	private int width(Node<T> root) {
		int res = 0;
		if (root != null) {
			if (root.left == null && root.right == null) {
				res = 1;
			} else {
				res = width(root.right) + width(root.left);
			}
		}
		return res;
	}

	public void inversion() {
		comp = comp.reversed();
		inversion(root);
	}
	
	public void inversion(Node<T> root) {
		if (root != null) {
			swapBranches(root);
			inversion(root.right);
			inversion(root.left);
		}
	}

	private void swapBranches(Node<T> root) {
		Node<T> temp = root.right;
		root.right = root.left;
		root.left = temp;
	}

	public void balance() {
		if (root != null) {
			Node<T>[] array = getNodesArray();
			root = balance(array, 0, array.length - 1, null);
		}
	}

	private Node<T> balance(Node<T>[] array, int left, int right, Node<T> parent) {
		Node<T> root = null;
		if (left <= right) {
			final int rootIndex = (right + left) / 2;
			root = array[rootIndex];
			root.parent = parent;
			root.left = balance(array, left, rootIndex - 1, root);
			root.right = balance(array, rootIndex + 1, right, root);
		}
		return root;
	}

	@SuppressWarnings("unchecked")
	private Node<T>[] getNodesArray() {
		Node<T>[] res = new Node[size()];
		int index = 0;
		Node<T> current = getLeastNode(root);
		while (current != null) {
			res[index++] = current;
			current = getNextCurrent(current);
		}
		return res;
	}
}
