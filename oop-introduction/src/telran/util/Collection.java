package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable <T>{
	boolean add (T element);
	boolean remove (T pattern);
	boolean isEmpty ();
	int size ();
	boolean contains (T pattern);
	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while (it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
			}
		}
		return oldSize > size();
	}
	default T[] toArray(T[] arr) {
		if (size() > arr.length) {
			arr = Arrays.copyOf(arr, size());
		}
		fillArray(arr);
		Arrays.fill(arr, size(), arr.length, null);
		return arr;
	}

	private void fillArray(T[] arr) {
		int index = 0;
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			arr[index++] = iter.next();
		}
	}
}
