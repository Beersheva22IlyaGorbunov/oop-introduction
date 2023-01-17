package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public abstract class ListTest extends CollectionTest {
	List<Integer> list;
	
	@BeforeEach
	@Override
	void setUp() throws Exception {
		super.setUp();
		list = (List<Integer>) collection;
	}
	
	@Test
	@Override
	void testAdd() {
		assertTrue(list.add(numbers[0]));
		assertEquals(list.size(), numbers.length + 1);
	}
	
	@Test
	void testAddInt() {
		Integer [] expected1 = {10, 100, -5, 100, 134, 280, 120, 15};
		Integer [] expected2 = {8, 10, 100, -5, 100, 134, 280, 120, 15};
		Integer [] expected3 = {8, 10, 100, -5, 100, 134, 280, 120, 15, 200};
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(1000, 1000));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(-1, 1000));
		list.add(3, 100);
		assertArrayEquals(expected1, list.toArray(empty));
		list.add(0, 8);
		assertArrayEquals(expected2, list.toArray(empty));
		list.add(list.size(), 200);
		assertArrayEquals(expected3, list.toArray(empty));
	}

	@Test
	void testRemoveInt() {
		Integer [] expected1 = {10, 100, -5, 280, 120, 15};
		Integer [] expected2 = { 100, -5,  280, 120, 15};
		Integer [] expected3 = { 100, -5,  280, 120};
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(1000));
		assertEquals(134,list.remove(3));
		assertArrayEquals(expected1, list.toArray(empty));
		assertEquals(10, list.remove(0));
		assertArrayEquals(expected2, list.toArray(empty));
		assertEquals(15,list.remove(list.size() - 1));
		assertArrayEquals(expected3, list.toArray(empty));
	}

	@Test
	void testIndexOf() {
		for(int i = 0; i < numbers.length; i++) {
			assertEquals(i, list.indexOf(numbers[i]));
		}
		assertEquals(-1, list.indexOf(30));
	}

	@Test
	void testLastIndexOf() {
		list.add(3, 134);
		assertEquals(3, list.indexOf(134));
		assertEquals(4, list.lastIndexOf(134));
		
	}

	@Test
	void testGet() {
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(1000));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertEquals(10, list.get(0));
	}

	@Test
	void testSet() {
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.set(1000, 1000));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.set(-1, 1000));
		list.set(0, 1000);
		assertEquals(1000, list.get(0));
	}
	
	@Test
	void testIterator() {
		Integer[] actual = new Integer[numbers.length];
		int currentIndex = 0;
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			actual[currentIndex++] = iter.next();
		}
		assertArrayEquals(actual, numbers);
	}
}
