package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {
	static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;
		Node(T obj) {
			this.obj = obj;
		}
	}
	private Node<T> head;
	private Node<T> tail;

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flNext = false;

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
			flNext = true;
			return obj;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			removeNode(removedNode);
			flNext = false;
		}
	}
	
	@Override
	public boolean add(T element) {
		Node<T> newNode = new Node<T>(element);
		addNode(newNode);
		return true;
	}
	
	void addNode(Node<T> newTail) {
		if (head == null) {
			head = tail = newTail;
		} else {
			tail.next = newTail;
			newTail.prev = tail;
			tail = newTail;
		}
		size++;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		Node<T> current = head;
		while (current != null) {
			if (predicate.test(current.obj)) {
				removeNode(current);
			}
			current = current.next;
		}
		return oldSize > size;
	}

	void removeNode(Node<T> current) {
		if (current == head) {
			removeHead();
		} else if (current == tail) {
			removeTail();
		} else {
			removeMiddleElement(current);
		}
		size--;
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
		Iterator<T> iter = iterator();
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

	public Node<T> getNode(int index) {	
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
		removeNode(node);
		return node.obj;
	}


	private void removeHead() {
		if (size == 1) {
			head = tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
	}

	private void removeTail() {
		tail = tail.prev;
		tail.next = null;
	}

	private void removeMiddleElement(Node<T> node) {
		Node<T> prevNode = node.prev;
		Node<T> nextNode = node.next;
		if (nextNode == null) {
			System.out.println("Pause");
		}
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
	
	public void setNext(int index1, int index2) {
		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
		Node<T> node = getNode(index1);
		Node<T> nextNode = getNode(index2);
		node.next = nextNode;
	}
	
	public boolean hasLoop() {
		Node<T> singleStep = head;
		Node<T> doubleStep = head;
		boolean isLoop = false;
		while (!isLoop && doubleStep != null && doubleStep.next != null) {
			singleStep = singleStep.next;
			doubleStep = doubleStep.next.next;
			if (singleStep == doubleStep) {
				isLoop = true;
			}
		};
		return isLoop;
	}
}
