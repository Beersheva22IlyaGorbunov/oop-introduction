package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.*;

public abstract class CollectionTest {
	protected Integer [] numbers = {10, 100, -5, 134, 280, 120, 15};
	protected Integer ar[] = new Integer[numbers.length + 100];
	protected Collection<Integer> collection;
	protected Integer [] empty = {};
	@BeforeEach
	void setUp() throws Exception {
		for(Integer num: numbers) {
			collection.add(num);
		}
	}

	@Test
	abstract void testAdd();
	
	abstract void testIterator();

	@Test
	void testRemove() {
		Integer [] expected = {10, 100, -5,  280, 120, 15};
		assertTrue(collection.remove((Integer)134));
		assertArrayEquals(expected, collection.toArray(empty));
		assertFalse(collection.remove((Integer)134));
	}

	@Test
	void testRemoveIf() {
		Integer []expected = {-5, 15};
		assertTrue(collection.removeIf(n -> n % 2 == 0));
		assertArrayEquals(expected, collection.toArray(empty));
		assertFalse(collection.removeIf(n -> n % 2 == 0));
		assertTrue(collection.removeIf(n -> n % 2 != 0));
		assertTrue(collection.isEmpty());
	}
	
	@Test
//	@Disabled
	void testRemoveIfperformance() {
		for (int i = 0; i < 10000; i++) {
			setUpForPerformance();
			collection.removeIf(n -> true);
		}
		for (int i = 0; i < 10000; i++) {
			setUpForPerformance();
			collection.removeIf(n -> n % 2 == 0);
		}
	}
	
	void setUpForPerformance() {
		collection.removeIf(n -> true);
		for (int i = 0; i < 100; i++) {
			int randomSymbol = generateRandomSymbol();
			collection.add(randomSymbol);
		}
	}
	
	private Integer generateRandomSymbol() {
		return getRandomNumber(0, 10);
	}
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}

	@Test
	void testIsEmpty() {
		assertFalse(collection.isEmpty());
		collection.removeIf(n -> true);
		assertTrue(collection.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(numbers.length, collection.size());
	}

	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertFalse(collection.contains(Integer.MIN_VALUE));
	}

	@Test
	void testToArray() {
		
		Arrays.fill(ar, 10);
		assertTrue(ar == collection.toArray(ar));
		for(int i = 0; i < numbers.length; i++) {
			assertEquals(ar[i], numbers[i]);
		}
		for(int i = numbers.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
		
	}
	
	@Test
	void removeIteratorTest() {
		Iterator<Integer> iter  = collection.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> iter.remove());
		Integer num = iter.next();
		assertTrue(collection.contains(num));
		iter.remove();
		assertFalse(collection.contains(num));
		assertEquals(numbers.length - 1, collection.size());
		assertThrowsExactly(IllegalStateException.class, () -> iter.remove());
		
		Iterator<Integer> iter1  = collection.iterator();
		while (iter1.hasNext()) {
			num = iter1.next();
		}
		assertTrue(collection.contains(num));
		iter1.remove();
		assertFalse(collection.contains(num));
	}
}
