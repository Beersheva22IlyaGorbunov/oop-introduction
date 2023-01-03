package telran.util;

import java.util.Arrays;
import java.util.Iterator;
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

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}
		//TODO
	}
	
	@Override
	public boolean add(T element) {
		Node<T> newNode = new Node(element);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
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
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			arr[i] = current.obj;
			current = current.next;
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
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
