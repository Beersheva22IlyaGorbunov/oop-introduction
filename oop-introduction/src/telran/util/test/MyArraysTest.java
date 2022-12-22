package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.EvenOddComparator;
import telran.util.MyArrays;

class MyArraysTest {

	@Test
	@Disabled
	void sortTest() {
		String[] strings = {"Hello", "asb", "asfasfaf", "TelRan"};
		String[] expected = {"asb", "Hello", "TelRan", "asfasfaf"};
		MyArrays.sort(strings, new StringsLengthComparator());
		assertArrayEquals(expected, strings);
	}
	
	@Test
	void evenOddSortTest() {
		Integer[] integers = {13, 2, -8, 47, -11, 100, 10, 7};
		Integer[] expected = {-8, 2, 10, 100, 47, 13, 7, -11};
		MyArrays.sort(integers, new EvenOddComparator());
		assertArrayEquals(expected, integers);
	}
	
	@Test
	void arrayIntBinarySearchTest() {
		Integer[] integers = {-8, -5, -3, 0, 10, 100, 700};
		Integer number = 100;
		int expected = 5;
		int result = MyArrays.binarySearch(integers, number, new IntegerComparator());
		assertEquals(expected, result);
	}
	
	@Test
	void arrayCharBinarySearchTest() {
		Character[] chars = {'A', 'B', 'C', 'C', 'D', 'N', 'Y'};
		Character character = 'N';
		int expected = 5;
		int result = MyArrays.binarySearch(chars, character, new CharacterComparator());
		assertEquals(expected, result);
	}
}
