package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {
	private static final int N_RUNS = 1000;
	private static final int N_NUMBERS = 10000;
	Integer[] integers = {13, 2, -8, 47, -11, 100, 10, 7};
	String[] strings = {"asd", "asdb", "asdbg", "asdbgt"};
	@Test
	@Disabled
	void sortTest() {
		String[] strings = {"Hello", "asb", "asfasfaf", "TelRan"};
		String[] expected = {"asb", "Hello", "TelRan", "asfasfaf"};
		MyArrays.sort(strings, new StringsLengthComparator());
		assertArrayEquals(expected, strings);
	}
	
	@Test
	@Disabled
	void evenOddSortTest() {
		Integer[] expected = {-8, 2, 10, 100, 47, 13, 7, -11};
		MyArrays.sort(integers, new EvenOddComparator());
		assertArrayEquals(expected, integers);
	}
	
	@Test
	@Disabled
	void arrayIntBinarySearchTest() {
		Integer[] sorted = {-8, -5, -3, 0, 10, 100, 700};
		Integer number = 100;
		int expected = 5;
		int result = MyArrays.binarySearch(sorted, number, new IntegerComparator());
		assertEquals(expected, result);
	}
	
	@Test
	@Disabled
	void arrayCharBinarySearchTest() {
		Character[] chars = {'A', 'B', 'C', 'C', 'D', 'N', 'Y'};
		Character character = 'N';
		int expected = 5;
		int result = MyArrays.binarySearch(chars, character, new CharacterComparator());
		assertEquals(expected, result);
	}
	
	@Test
	@Disabled
	void stringBinarySearchTest() {
		assertEquals(0, MyArrays.binarySearch(strings, "asd", new StringsComparator()));
		assertEquals(1, MyArrays.binarySearch(strings, "asdb", new StringsComparator()));
		assertEquals(2, MyArrays.binarySearch(strings, "asdbg", new StringsComparator()));
		assertEquals(3, MyArrays.binarySearch(strings, "asdbgt", new StringsComparator()));
		assertEquals(-1, MyArrays.binarySearch(strings, "asb", new StringsComparator()));
		assertEquals(-2, MyArrays.binarySearch(strings, "asda", new StringsComparator()));
		assertEquals(-3, MyArrays.binarySearch(strings, "asdba", new StringsComparator()));
		assertEquals(-4, MyArrays.binarySearch(strings, "asdbga", new StringsComparator()));
	}
	
	@Test
	@Disabled
	void filterTest() {
		int divider = 2;
		String subStr = "g";
		Predicate<Integer> predEven = new DividerPredicate(divider);
		Predicate<String> predSubstr = new SubstrPredicate(subStr);
		Integer[] expectedInt = {2, -8, 100, 10};
		String[] expectedStr = {"asdbg", "asdbgt"};
		assertArrayEquals(expectedInt, MyArrays.filter(integers, predEven));
		assertArrayEquals(expectedStr, MyArrays.filter(strings, predSubstr));
	}
	
	@Test
	@Disabled
	void removeIfTest() {
		int divider = 2;
		String subStr = "g";
		Predicate<Integer> predEven = new DividerPredicate(divider);
		Predicate<String> predSubstr = new SubstrPredicate(subStr);
		Integer[] expectedInt = {13, 47, -11, 7};
		String[] expectedStr = {"asd", "asdb"};
		assertArrayEquals(expectedInt, MyArrays.removeIf(integers, predEven));
		assertArrayEquals(expectedStr, MyArrays.removeIf(strings, predSubstr));
	}
	
	@Test
	@Disabled
	void containsTest() {
		Integer[] intWithNull = {1, 3, null, 5};
		assertTrue( MyArrays.contains(intWithNull, null));
		assertTrue( MyArrays.contains(integers, 2));
		assertFalse( MyArrays.contains(integers, 5));
		assertTrue( MyArrays.contains(strings, "asdb"));
		assertFalse( MyArrays.contains(strings, "g"));
	}
	
	@Test
//	@Disabled
	void removeRepeatedTest() {
		Integer[] arr = {1, 1, 2, 2, 3, 3, 4, 3, 5};
		Integer[] expectedInt = {1, 2, 3, 4, 5};
		Integer[] arrOnlyOne = {1, 1, 1, 1, 1, 1};
		Integer[] expectedOnlyOne = {1};
		assertArrayEquals(expectedInt , MyArrays.removeRepeated(arr));
		assertArrayEquals(expectedOnlyOne , MyArrays.removeRepeated(arrOnlyOne));
	}
	
	@Test
	@Disabled
	void joinFunctionalTest() {
		String expected = "13, 2, -8, 47, -11, 100, 10, 7";
		assertEquals(expected, MyArrays.join(integers, ", "));
	}
	
	@Test
	@Disabled
	void joinPerformanceTest() {
		Integer[] largeArray = getLargeNumbersArray();
		for (int i = 0; i < N_RUNS; i++) {
			MyArrays.join(largeArray, " asd as");
		}
	}

	private Integer[] getLargeNumbersArray() {
		Integer[] res = new Integer[N_NUMBERS];
		Arrays.fill(res, 1000);
		return res;
	}
}
