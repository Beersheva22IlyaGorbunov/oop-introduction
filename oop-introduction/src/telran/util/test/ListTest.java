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
		try {
			list.add(1000, 1000);
			fail("should be exception");
		} catch(IndexOutOfBoundsException e) {}
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
		try {
			list.remove(1000);
			fail("should be exception");
		} catch(IndexOutOfBoundsException e) {}
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
	}

	@Test
	void testLastIndexOf() {
		list.add(3, 134);
		assertEquals(3, list.indexOf(134));
		assertEquals(4, list.lastIndexOf(134));
		
	}

	@Test
	void testGet() {
		try {
			list.get(1000);
			fail("should be exception");
		} catch(IndexOutOfBoundsException e) {}
		assertEquals(10, list.get(0));
	}

	@Test
	void testSet() {
		list.set(0, 1000);
		assertEquals(1000, list.get(0));
	}
	
	@Test
	void testIterator() {
		int currentIndex = 0;
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			assertEquals(list.get(currentIndex++), iter.next());
		}
		try {
			iter.next();
			fail("should be exception");
		} catch (NoSuchElementException e) {
		}
		assertFalse(iter.hasNext());
	}
}
