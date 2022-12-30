package telran.util;

import java.util.function.Predicate;

public interface Collection<T> extends Iterable <T>{
	boolean add (T element);
	boolean remove (T pattern);
	boolean removeIf (Predicate<T> predicate);
	boolean isEmpty ();
	int size ();
	boolean contains (T pattern);
	/****************************/
	/**
	 * @param arr
	 * @return array containing elements of a Collection
	 * if arr refers to memory that enough for all elements of 
	 * Collection then new array isn't created.
	 * Otherwise there will be created new array.
	 * If arr refers to memory that greater than required
	 * to store all elements of Collection, the rest of arr will
	 * be filled by null's.
	 */
	T[] toArray(T[] arr);
}
