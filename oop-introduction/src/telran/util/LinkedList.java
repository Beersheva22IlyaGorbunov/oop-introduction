package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;
		Node(T obj) {
			this.obj = obj;
		}
	}
	private Node<T> head;
	private Node<T> tail;
	private int size;
	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T obj = current.obj;
			current = current.next;
			return obj;
		}
	}
	
	@Override
	public boolean add(T element) {
		Node<T> newNode = new Node<T>(element);
		if (head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		int index = indexOf(pattern);
		if (index > -1) remove(index);
		return index > -1;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		if (size > 0) {
			Node<T> current = tail;
			for (int index = oldSize - 1; index > -1; index--) {
				if (predicate.test(current.obj)) {
					remove(index);
				}
				current = current.prev;
			}
		}
		return oldSize > size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T[] toArray(T[] arr) {
		if (size > arr.length) {
			arr = Arrays.copyOf(arr, size);
		}
		fillArray(arr);
		Arrays.fill(arr, size, arr.length, null);
		return arr;
	}

	private void fillArray(T[] arr) {
		int index = 0;
		Iterator<T> iter = new LinkedListIterator();
		while (iter.hasNext()) {
			arr[index++] = iter.next();
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (index == size) {
			add(element);
		} else if(index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}
	}

	private void addMiddle(int index, T element) {
		Node<T> newNode = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		newNode.prev = nodeIndex.prev;
		newNode.next = nodeIndex;
		nodePrev.next = newNode;
		nodeIndex.prev = newNode;
		size++;
	}

	private Node<T> getNode(int index) {	
		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> elem = tail;
		for (int i = size - 1; i > index; i--) {
			elem = elem.prev;
		}
		return elem;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> elem = head;
		for (int i = 0; i < index; i++) {
			elem = elem.next;
		}
		return elem;
	}

	private void addHead(T element) {
		Node<T> node = new Node<>(element);
		head.prev = node;
		node.next = head;
		head = node;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		if (head == tail) {
			removeLastElem();
		} else if (index == size - 1) {
			removeTail();
		} else if(index == 0) {
			removeHead();
		} else {
			removeMiddleElement(node);
		}
		size--;
		return node.obj;
	}

	private void removeLastElem() {
		head = tail = null;
	}

	private void removeHead() {
		head = head.next;
		head.prev = null;
	}

	private void removeTail() {
		tail = tail.prev;
		tail.next = null;
	}

	private void removeMiddleElement(Node<T> node) {
		Node<T> prevNode = node.prev;
		Node<T> nextNode = node.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head;
		int i = 0;
		while (i < size && !isEqual(current.obj, pattern)) {
			current = current.next;
			i++;
		}
		return i < size ? i : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail;
		int i = size - 1;
		while (i > -1 && !isEqual(current.obj, pattern)) {
			current = current.prev;
			i--;
		}
		return i > -1 ? i : -1;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		node.obj = element;
	}

}
