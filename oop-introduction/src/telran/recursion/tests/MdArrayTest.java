package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.recursion.MdArray;

public class MdArrayTest {
	@Test
	void forEachTest() {
		MdArray<Integer> integers = new MdArray<Integer>(new int[]{10, 5, 7}, 50); 
		Integer[] sum = {0}; 
		integers.forEach(num -> sum[0]+=num); 
		assertEquals(sum[0], 17500);
		
		MdArray<String> strings = new MdArray<>(new int[]{3, 15,7, 2, 10}, "hello"); 
		Integer[] length = {0};
		strings.forEach(str -> length[0] += str.length());
		assertEquals(length[0], 31500);
		
		strings.setValue(new int[]{2, 14, 6, 1, 9}, null);
		assertThrowsExactly(NullPointerException.class, () -> strings.forEach(str -> length[0] += str.length()));
	}
	
	@Test
	void toArrayTest() {
		MdArray<Integer> array = new MdArray<>(new int[]{10, 5, 7}, 50); 
		array.setValue(new int[] {5, 3, 2}, 500);
		Integer[] numbers = array.toArray(new Integer[0]);
		assertEquals(350, numbers.length);
		assertEquals(1, Arrays.stream(numbers).filter(a -> a != 50).toArray().length);
	}
	
	@Test
	void getValueTest() {
		MdArray<Integer> array = new MdArray<>(new int[]{10, 5, 7}, 50); 
		assertEquals(50, array.getValue(new int[] {3, 4, 6}));
		assertThrowsExactly(IllegalStateException.class, () -> array.getValue(new int[] {3, 4}));
		assertThrowsExactly(NoSuchElementException.class, () -> array.getValue(new int[] {3, 4, 6, 0}));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.getValue(new int[] {3, 4, 7}));
	}
	
	@Test
	void setValueTest() {
		MdArray<Integer> array = new MdArray<>(new int[]{10, 5, 7}, 50); 
		array.setValue(new int[] {3, 4, 6}, 100);
		assertEquals(100, array.getValue(new int[] {3, 4, 6}));
		assertThrowsExactly(IllegalStateException.class, () -> array.setValue(new int[] {3, 4}, 100));
		assertThrowsExactly(NoSuchElementException.class, () -> array.setValue(new int[] {3, 4, 6, 0}, 100));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.setValue(new int[] {3, 4, 7}, 100));
	}
}
