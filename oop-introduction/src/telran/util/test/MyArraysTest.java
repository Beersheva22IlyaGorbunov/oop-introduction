package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {
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
//	@Disabled
	void arrayIntBinarySearchTest() {
		Integer[] sorted = {-8, -5, -3, 0, 10, 100, 700};
		Integer number = 100;
		int expected = 5;
		int result = MyArrays.binarySearch(sorted, number, new IntegerComparator());
		assertEquals(expected, result);
	}
	
	@Test
//	@Disabled
	void arrayCharBinarySearchTest() {
		Character[] chars = {'A', 'B', 'C', 'C', 'D', 'N', 'Y'};
		Character character = 'N';
		int expected = 5;
		int result = MyArrays.binarySearch(chars, character, new CharacterComparator());
		assertEquals(expected, result);
	}
	
	@Test
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
	void containsTest() {
		Integer containNumber = 2;
		String containStr = "g";
		assertTrue( MyArrays.contains(integers, containNumber));
		assertFalse( MyArrays.contains(strings, containStr));
	}
	
	@Test
	void removeRepeatedTest() {
		Integer[] arr = {1, 1, 2, 2, 3, 3, 4, 3, 5};
		System.out.println(Arrays.toString(MyArrays.removeRepeated(arr)));
	}
}
