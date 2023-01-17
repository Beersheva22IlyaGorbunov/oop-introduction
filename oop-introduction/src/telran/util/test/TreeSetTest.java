package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

import telran.util.TreeSet;

class TreeSetTest extends SetTest {
	TreeSet <Integer> treeSet;
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
		treeSet = (TreeSet<Integer>) collection;
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
		Arrays.sort(numbersCopy);
		
		assertArrayEquals(numbersCopy, actual);
		assertThrowsExactly(NoSuchElementException.class, () -> iter.next());
	}
}
