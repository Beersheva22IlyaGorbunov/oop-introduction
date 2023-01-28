package telran.recursion;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MdArray<T> {
	private MdArray<T>[] array;
	private T value;
	
	public MdArray(int[] dimensions, T value) {
		this(dimensions, 0, value);
	}

	@SuppressWarnings("unchecked")
	private MdArray(int[] dimensions, int firstDim, T value) {
		if (firstDim < dimensions.length) {
			this.value = null;
			array = new MdArray[dimensions[firstDim]];
			for (int i = 0; i < array.length; i++) {
				array[i] = new MdArray<>(dimensions, firstDim + 1, value);
			}
		} else {
			this.value = value;
			array = null;
		}
	}
	
	public void forEach(Consumer<T> cons) {
		forEach(cons, this);
	}
	
	private void forEach(Consumer<T> cons, MdArray<T> MdArray) {
		if (MdArray.array != null) {
			for (int i = 0; i < MdArray.array.length; i++) {
				forEach(cons, MdArray.array[i]);
			}
		} else {
			cons.accept(MdArray.value);
		}
	}
	
	public T[] toArray(T[] example) {
		ArrayList<T> list = new ArrayList<>();
		this.forEach(elem -> list.add(elem));
		return list.toArray(example);
	}
	
	public T getValue(int[] indexes) {
		return getElement(indexes).value;
	}

	private MdArray<T> getElement(int[] indexes) {
		MdArray<T> temp = this;
		for (int i = 0; i < indexes.length; i++) {
			if (temp.array == null ) {
				throw new NoSuchElementException();
			}
			if (i == indexes.length - 1 && indexes[i] > temp.array.length) {
				throw new ArrayIndexOutOfBoundsException();
			}
			temp = temp.array[indexes[i]];
		}
		if (temp.array != null) {
			throw new IllegalStateException();
		}
		return temp;
	}
	
	public void setValue(int[] indexes, T value) {
		MdArray<T> element = getElement(indexes);
		element.value = value;
	}
} 
