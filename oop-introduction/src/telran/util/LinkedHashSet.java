package telran.util;

import java.util.Iterator;
import telran.util.LinkedList.Node;
import telran.util.Map.Entry;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	HashMap<T, Node<T>> map = new HashMap<>();
	LinkedList<T> orderList = new LinkedList<>();
	
	private class LinkedHashSetIterator implements Iterator<T> {
		Iterator<T> linkedIterator = orderList.iterator();
		T currentElem;
		
		@Override
		public boolean hasNext() {
			return linkedIterator.hasNext();
		}

		@Override
		public T next() {
			return currentElem = linkedIterator.next();
		}
		
		@Override
		public void remove() {
			map.remove(currentElem);
			linkedIterator.remove();
			size--;
		}
	}
	
	@Override
	public boolean add(T element) {
		boolean res = false;
		Node<T> newNode = new Node<T>(element);
		if (map.putIfAbsent(element, newNode) == null) {
			orderList.addNode(newNode);
			res = true;
			size++;
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> nodeToDelete = map.remove(pattern);
		if (nodeToDelete != null) {
			orderList.removeNode(nodeToDelete);
			res = true;
			size--;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		return map.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T pattern) {
		Entry<T, Node<T>> resEntry = null;
		Entry<T, Node<T>> entry = new Entry<>(pattern, null);
		resEntry = map.set.get(entry);
		return resEntry != null ? resEntry.getKey() : null;
	}

}
