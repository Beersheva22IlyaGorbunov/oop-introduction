package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	
	private class ArrayListIterator implements Iterator<T> {
		int currentIndex = 0;
		boolean flNext = false;
		
		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return array[currentIndex++];
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(--currentIndex);
			flNext = false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public boolean add(T element) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	
	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		int tIndex = 0;
		for (int index = 0; index < oldSize; index++) {
			if (predicate.test(array[index])) {
				size--;
			} else {
				array[tIndex++] = array[index];
			}
		}
		Arrays.fill(array, size, oldSize, null);	
		return oldSize > size;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		T temp = array[index];
		size--;
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return temp;
	}

	@Override
	public int indexOf(T pattern) {
		int i = 0;
		while (i < array.length && !isEqual(array[i], pattern)) {
			i++;
		}
		return i < array.length ? i : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int i = array.length - 1;
		while (i > -1 && !isEqual(array[i], pattern)) {
			i--;
		}
		return i > -1 ? i : -1;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return array[index];
	}

	@Override
	public void set(int index, T elem) {
		checkIndex(index, false);
		array[index] = elem;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

}
