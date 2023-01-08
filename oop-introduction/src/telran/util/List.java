package telran.util;

public interface List<T> extends Collection<T> {
	void add (int index, T element);
	T remove (int index);
	int indexOf (T pattern);
	int lastIndexOf (T pattern);
	T get (int index);
	void set (int index, T element);
	
	default void checkIndex(int index, boolean includedLast) {
		int lastIndex = includedLast ? size() : size() - 1;
		if (index < 0 || index > lastIndex) {
			throw new IndexOutOfBoundsException(String.format("Element with index %s doesn't exist", index));
		}
	}
	
	default boolean contains(T pattern) {
		return indexOf(pattern) > -1;
	}
	
	default boolean remove(T pattern) {
		int index = indexOf(pattern);
		if (index > -1) remove(index);
		return index > -1;
	}

}
