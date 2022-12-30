package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;
	
	private class ArrayListIterator implements Iterator<T> {
		int currentIndex = 0;
		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[currentIndex++];
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
	public boolean remove(T pattern) {
		int index = indexOf(pattern);
		if (index > -1) remove(index);
		return index > -1;
	}
	
	public boolean removeIfByRemove(Predicate<T> predicate) {
		int oldSize = size;
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test(array[i])) {
				remove(i);
			}
		}
		return oldSize > size;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int delta = 0;
		for (int index = 0; index < size; index++) {
			if (predicate.test(array[index])) {
				delta++;	
			} else {
				if (delta > 0 && index < size) {
					shiftElement(index, delta);
				}
			}
		}
		size -= delta;
		Arrays.fill(array, size, size + delta, null);
		return delta > 0;
	}
	
	public boolean removeIfTroughtIterator(Predicate<T> predicate) {
		int currentIndex = 0;
		Iterator<T> iter = this.iterator();
		while (iter.hasNext()) {
			T currentElem = iter.next();
			if (!predicate.test(currentElem)){
				array[currentIndex++] = currentElem;
			}
		}
		int delta = size - currentIndex;
		size = size - delta;
		Arrays.fill(array, size, size + delta, null);
		return delta > 0;
	}

	private void shiftElement(int index, int delta) {
		array[index - delta] = array[index];
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
	public boolean contains(T pattern) {
		int index = 0;
		while (index < size && !isEqual(array[index], pattern)) {
			index++;
		}
		return index < size;
	}
	
	private static <T> boolean isEqual(T elem, T pattern) {
		return elem == null ? pattern == elem : elem.equals(pattern);
	}

	@Override
	public T[] toArray(T[] arr) {
		if (size > arr.length) {
			arr = Arrays.copyOf(arr, size);
		}
		System.arraycopy(array, 0, arr, 0, size);
		Arrays.fill(arr, size, arr.length, null);
		return arr;
	}

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(String.format("Element can't be added in position %s", index));
		}
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index);
		T temp = array[index];
		size--;
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return temp;
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(String.format("Element with index %s doesn't exist", index));
		}
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
		checkIndex(index);
		return array[index];
	}

	@Override
	public void set(int index, T elem) {
		checkIndex(index);
		array[index] = elem;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

}
