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
//	@Disabled
	void testAdd() {
		assertTrue(set.add(20));
		assertFalse(set.add(20));
	}

	@Override
	@Test
//	@Disabled
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
		
		assertArrayEquals(numbersCopy, actual);
		assertThrowsExactly(NoSuchElementException.class, () -> iter.next());
	}
	
	@Override
	@Test
//	@Disabled
	void testToArray() {
		
		Arrays.fill(ar, 10);
		Integer[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
		assertTrue(ar == collection.toArray(ar));
		Arrays.sort(ar, 0, collection.size());
		Arrays.sort(numbersCopy);
		for(int i = 0; i < numbersCopy.length; i++) {
			assertEquals(ar[i], numbersCopy[i]);
		}
		for(int i = numbersCopy.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
	}
	
	@Override
	@Test
//	@Disabled
	void testRemove() {
		Integer [] expected = {10, 100, -5,  280, 120, 15};
		assertTrue(collection.remove((Integer)134));
		Arrays.sort(expected);
		Integer [] actual = collection.toArray(empty);
		Arrays.sort(actual);

		assertArrayEquals(expected, actual);
		assertFalse(collection.remove((Integer)134));
		
		Integer[] numbersInSet = new Integer[1000];
		fillSetAndArr(numbersInSet);
		for (Integer num: numbersInSet) {
			collection.remove(num);
		}
		assertTrue(collection.isEmpty());
	}
	
	void fillSetAndArr(Integer[] arr) {
		collection.removeIf(n -> true);
		int index = 0;
		while (index < arr.length) {
			int randomSymbol = getRandomNumber(0, 100000);
			if (collection.add(randomSymbol)) {
				arr[index++] = randomSymbol;
			}
		}
	}
}
