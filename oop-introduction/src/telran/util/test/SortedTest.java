package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.*;

import telran.util.Sorted;

public abstract class SortedTest extends SetTest {
	Sorted<Integer> sorted;
	@BeforeEach
	@Override
	void setUp() throws Exception {
		super.setUp();
		sorted = (Sorted<Integer>) collection;
	}
	
	@Override
	@Test
	void testIterator() {
		Integer[] actual = new Integer[numbers.length];
		int currentIndex = 0;
		for (Integer num: sorted) {
			actual[currentIndex++] = num;
		}
		
		Integer[] expected = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void floorTest() {
		assertEquals((Integer)100, sorted.floor(100));
		assertNull(sorted.floor(-10));
		assertEquals((Integer)15, sorted.floor(20));
		assertEquals((Integer)280, sorted.floor(300));
		assertEquals((Integer)120, sorted.floor(133));
	}
	
	@Test
	void ceilingTest() {
		assertEquals((Integer)100, sorted.ceiling(100));
		assertNull(sorted.ceiling(281));
		assertEquals((Integer)100, sorted.ceiling(20));
		assertEquals((Integer)(-5), sorted.ceiling(-100));
		assertEquals((Integer)(134), sorted.ceiling(133));
	}
	
	@Test
	void firstTest() {
		assertEquals((Integer)(-5), sorted.first());
	}
	
	@Test
	void lastTest() {
		assertEquals((Integer)(280), sorted.last());
	}
}
