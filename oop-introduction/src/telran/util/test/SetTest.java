package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

import telran.util.*;

public abstract class SetTest extends CollectionTest {
	Set<Integer> set;
	
	@BeforeEach
	@Override
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}
	@Override
	@Test
	void testAdd() {
		assertTrue(set.add(20));
		assertFalse(set.add(20));
	}

	@Override
	@Test
	void testIterator() {
		Integer[] actual = new Integer[numbers.length];
		int currentIndex = 0;
		Iterator<Integer> iter = set.iterator();
		while (iter.hasNext()) {
			actual[currentIndex++] = iter.next();
		}
		
		Integer[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(actual);
		Arrays.sort(numbersCopy);
		
		assertArrayEquals(actual, numbersCopy);
		assertThrowsExactly(NoSuchElementException.class, () -> iter.next());
		
		Set<Integer> emptySet = new HashSet<>();
		Iterator<Integer> iter2 = emptySet.iterator();
		assertThrowsExactly(NoSuchElementException.class, () -> iter2.next());
	}

}
