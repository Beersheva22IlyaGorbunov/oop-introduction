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
		Node<T> current;
		Node<T> prev;
		boolean flNext = false;
		
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
			prev = current;
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
			flNext = true;
			return res;
		}
		
		@Override
		public void remove() {
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
		Node<T> current = getNode(newNode.obj);
		if (current.obj != newNode.obj) {
			boolean isRight = comp.compare(newNode.obj, current.obj) > 0 ? true : false;
			if (isRight) {
				addRightElement(newNode, current);
			} else {
				addLeftElement(newNode, current);
			}
			isAdded = true;
		}
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
		return false;
	}
	
	@Override
	public boolean contains(T element) {
		return getNode(element).obj == element;
	}

	private Node<T> getNode(T element) {
		Node<T> current = null;
		Node<T> next = null;
		if (root != null) {
			current = root;
			int compRes = comp.compare(element, current.obj);
			next = compRes > 0 ? current.right : current.left;
			while (next != null && !current.obj.equals(element)) {
				current = next;
				compRes = comp.compare(element, current.obj);
				if (compRes != 0) {
					next = compRes > 0 ? current.right : current.left;
				}
			};
		}
		return current;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

}
