package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import static telran.recursion.LinearRecursion.*;

public class LinearRecursionTest {
	@Test
	void factorialTest() {
		assertEquals(120 * 6, factorial(6));
		assertEquals(120, factorial(5));
		assertEquals(24, factorial(-4));
	}
	
	@Test
	void powerTest() {
		assertEquals(81, power(9, 2));
		assertEquals(1, power(10, 0));
		assertEquals(1000, power(10, 3));
		assertEquals(-1000, power(-10, 3));
		assertThrowsExactly(IllegalArgumentException.class, () -> power(1000, -1));
	}
	
	@Test
	void sumTest() {
		assertEquals(81, sum(new int[] {60, 20, 1}));
		assertEquals(0, sum(new int[] {}));
		assertEquals(23, sum(new int[] {23}));
	}
	
	@Test
	void reverseTest() {
		int[] initial = {1, 2, 3, 4, 5};
		int[] expected = {5, 4, 3, 2, 1};
		reverse(initial);
		assertArrayEquals(expected, initial);
		
		int[] initial2 = {1, 2, 3, 4, 5, 6};
		int[] expected2 = {6, 5, 4, 3, 2, 1};
		reverse(initial2);
		assertArrayEquals(expected2, initial2);
		
		int[] empty = {};
		reverse(empty);
	}
	
	@Test
	void multiplyTest() {
		assertEquals(6, multiply(3, 2));
		assertEquals(6, multiply(2, 3));
		assertEquals(0, multiply(2, 0));
		assertEquals(-6, multiply(-3, 2));
	}
	
	@Test
	void squareTest() {
		assertEquals(16, square(4));
		assertEquals(81, square(9));
		assertEquals(0, square(0));
		assertEquals(1, square(1));
	}
	
	@Test
	void isSubstringTest() {
		assertTrue(isSubstring("abcdefg", "abc"));
		assertTrue(isSubstring("defgabc", "abc"));
		assertTrue(isSubstring("deababcabffg", "abc"));
		assertTrue(isSubstring("defgabcasdasd", "abc"));
		assertFalse(isSubstring("abcdefg", "abd"));
	}
}
