package telran.util;

import java.util.Comparator;

public class MyArrays {
	static public <T> void sort (T[] objects, Comparator <T> comparator) {
		int length = objects.length;
		do {
			length--;
		} while (moveMaxAtEnd(objects, length, comparator));
	}

	private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
		boolean flag = false;
		for (int i = 0; i < length; i++) {
			if (comp.compare(objects[i], objects[i + 1]) > 0) {
				swap(objects, i, i + 1);
				flag = true;
			}
		}
		return flag;
	}

	private static <T> void swap(T[] objects, int i, int j) {
		T temp = objects[i];
		objects[i] = objects[j];
		objects[j] = temp;
	}
	
	public static <T> int binarySearch(T[] array, T searchedNumber, Comparator<T> comp) {
		int left = 0;
		int right = array.length - 1;
		int middle = right / 2;
		boolean elementFounded = comp.compare(array[middle], searchedNumber) == 0;
		while (left <= right && !elementFounded) {
			if (comp.compare(array[middle], searchedNumber) > 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
			elementFounded = comp.compare(array[middle], searchedNumber) == 0;
		}
		return elementFounded ? middle : - middle - 1;
	}
}
