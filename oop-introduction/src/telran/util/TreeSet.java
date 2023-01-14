package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Set<T> {
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
		
		TreeSetIterator() {
			current = getLeast(root);
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
			Node<T> parent = current.parent;
			if (current.right == null) {
				while (parent != null && current == parent.right) {
					current = current.parent;
					parent = current.parent;
				}
				current = parent;
			} else {
				current = getLeast(current.right);
			}
			return res;
		}
		
		public Node<T> getLeast(Node<T> node) {
			while (node.left != null) {
				node = node.left;
			}
			return node;
		}
	}
	private Node<T> root;
	private Comparator<T> comp;
	
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}
	@Override
	public boolean add(T element) {
		boolean isAdded = false;
		Node<T> newNode = new Node<T>(element);
		if (root == null) {
			isAdded = addRoot(newNode);
		} else {
			isAdded = addNonRootElement(newNode);
		}
		if (isAdded) {
			size++;
		}
		return isAdded;
	}
	private boolean addNonRootElement(Node<T> newNode) {
		boolean isAdded = false;
		Node<T> current = root;
		int compRes = 0;
		do {
			compRes = comp.compare(newNode.obj, current.obj);
			if (compRes > 0) {
				if (current.right != null) {
					current = current.right;
				} else {
					isAdded = addRightElement(newNode, current);
				}
			} else if (compRes < 0) {
				if (current.left != null) {
					current = current.left;
				} else {
					isAdded = addLeftElement(newNode, current);
				}
			}
		} while (!isAdded && compRes != 0);
		return isAdded;
	}
	private boolean addLeftElement(Node<T> newNode, Node<T> current) {
		newNode.parent = current;
		current.left = newNode;
		return true;
	}
	private boolean addRightElement(Node<T> newNode, Node<T> current) {
		newNode.parent = current;
		current.right = newNode;
		return true;
	}
	private boolean addRoot(Node<T> newNode) {
		root = newNode;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		// Not implemented yet
		return false;
	}

	@Override
	public boolean contains(T element) {
		boolean elemExists = false;
		if (root != null) {
			Node<T> current = root;
			do {
				int compRes = comp.compare(element, current.obj);
				if (compRes == 0) {
					elemExists = true;
				} else {
					current = compRes > 0 ? current.right : current.left;
				}
			} while (current != null && !elemExists);
		}
		return elemExists;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

}
