package telran.util;

public abstract class AbstractCollection<T> implements Collection<T> {
	protected int size;
	
	@Override
	public int size() {
		return size;
	}
	
	protected boolean isEqual(T elem, T pattern) {
		return elem == null ? pattern == elem : elem.equals(pattern);
	}
	

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	
}
