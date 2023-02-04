package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	private static final int DEFAULT_TABLE_SIZE = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	List<T>[] hashTable;
	private float factor;
	@SuppressWarnings("unchecked")
	public HashSet(int tableSize, float factor) {
		if (tableSize < 1) {
			throw new IllegalArgumentException("Table size less than 1 element");
		}
		if (factor > 1 || factor < 0.3) {
			throw new IllegalArgumentException("Wrong factor value");
		}
		hashTable = new List[tableSize];
		this.factor = factor;
	}
	public HashSet() {
		this(DEFAULT_TABLE_SIZE, DEFAULT_FACTOR);
	}
	private class HashSetIterator implements Iterator<T> {
		int currentIndex = 0;
		Iterator<T> listIterator;
		int currentElement = 0;
		boolean flNext = false;
		
		HashSetIterator() {
			listIterator = getInitialIterator();
		}
		
		@Override
		public boolean hasNext() {
			return currentElement < size;
		}

		private Iterator<T> getInitialIterator() {
			currentIndex = getNextIndex(true);
			return currentIndex > -1 ? hashTable[currentIndex].iterator() : null;
		}

		private int getNextIndex(boolean isInitial) {
			int nextIndex = isInitial ? 0 : currentIndex + 1;
			while (nextIndex < hashTable.length && hashTable[nextIndex] == null) {
				nextIndex++;
			}
			return nextIndex < hashTable.length ? nextIndex : -1;
		}
		
		@Override
		public T next() {
			T res;
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (listIterator.hasNext()) {
				res = listIterator.next();
			} else {
				currentIndex = getNextIndex(false);
				listIterator = hashTable[currentIndex].iterator();
				res = listIterator.next();
			}
			currentElement++;
			flNext = true;
			return res;
		}
		

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			listIterator.remove();
			if (hashTable[currentIndex].isEmpty()) {
				hashTable[currentIndex] = null;
			}
			currentElement--;
			size--;
			flNext = false;
		}
	}
	@Override
	public boolean add(T element) {
		if (size >= hashTable.length * factor) {
			tableRebuild();
		}
		int index = getHashIndex(element);
		boolean res = false;
		List<T> list = hashTable[index];
		if (list == null) {
			list = new LinkedList<>();
			hashTable[index] = list;
		}
		if (!list.contains(element)) {
			res = true;
			list.add(element);
			size++;
		}
		return res;
	}

	private void tableRebuild() {
		HashSet<T> hashSet = new HashSet<>(hashTable.length * 2, factor);
		for (List<T> list: hashTable) {
			if (list != null) {
				for (T obj: list) {
					hashSet.add(obj);
				}
			}
		}
		hashTable = hashSet.hashTable;
	}
	private int getHashIndex(T element) {
		return Math.abs(element.hashCode() % hashTable.length);
	}
	@Override
	public boolean remove(T pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
				if (hashTable[index].isEmpty()) {
					hashTable[index] = null;
				}
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int index = getHashIndex(pattern);
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}
	
	public T get(T pattern) {
		T res = null;
		List<T> list = hashTable[getHashIndex(pattern)];
		if (list != null) {
			T obj = null;
			Iterator<T> it = list.iterator();
			while(it.hasNext() && !isEqual((obj = it.next()),pattern)) {}
			if (isEqual(obj, pattern)) {
				res = obj;
			}
		}
		return res;
	}
}
