package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.hamcrest.core.IsEqual;

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
	
	public static <T> int binarySearch(T[] array, T element, Comparator<T> comp) {
		int left = 0;
		int right = array.length - 1;
		int middle = right / 2;
		while (left <= right && !array[middle].equals(element)) {
			if (comp.compare(element, array[middle]) < 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}
		return left > right ? - left - 1 : middle ;
	}

	public static <T> T[] filter(T[] array, Predicate<T> predicate) {
		int countPredicate = getCountPredicate(array, predicate);
		T[] res = Arrays.copyOf(array, countPredicate);
		int index = 0;
		for (T element : array) {
			if (predicate.test(element)) {
				res[index++] = element;
			}
		}
 		return res;
	}

	private static <T> int getCountPredicate(T[] array, Predicate<T> predicate) {
		int count = 0;
		for (T element : array) {
			if (predicate.test(element)) {
				count++;
			}
		}
		return count;
	}
	
	public static <T> T[] removeIf (T[] array, Predicate<T> predicate) {
		return filter(array, predicate.negate());
	}
	
	public static <T> T[] removeRepeated (T[] array) {
		T[] res = array.clone();
		int index = 0;
		while (array.length > 0) {
			res[index++] = array[0];
			array = removeIf(array, Predicate.isEqual(array[0]));
		}
		return Arrays.copyOf(res, index);
	}

	public static <T> boolean contains (T[] array, T pattern) {
		boolean isFound = false;
		int i = 0;
		while (i < array.length && !isFound) {
			isFound = checkElement(array[i], pattern);
			i++;
		}
		return isFound;
	}

	private static <T> boolean checkElement(T elem, T pattern) {
		boolean isEqual = false;
		if (elem != null && elem.equals(pattern)) {
			isEqual = true;
		} else {
			if (pattern == null) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}
