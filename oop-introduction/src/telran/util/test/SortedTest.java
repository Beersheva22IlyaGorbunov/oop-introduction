package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.*;

import telran.util.Sorted;

public abstract class SortedTest extends SetTest {
	protected static final int N_ELEMENTS = 100000;
	protected static final int N_RUNS = 10000;
	private Random gen = new Random();
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
	
	@Test
	@Disabled
	void performanceTestSortedAdding() {
		Sorted<Integer> sorted = getSortedCollection();
		IntStream.range(0, N_ELEMENTS).forEach(i -> sorted.add(i));
		runPerformanceTest(sorted);
	}
	
	protected void runPerformanceTest(Sorted<Integer> sorted2) {
		for (int i = 0; i < N_RUNS; i++) {
			sorted.floor(Integer.MAX_VALUE);
		}
	}

	protected abstract Sorted<Integer> getSortedCollection();

	@Test
	void performanceTestRandomAdding() {
		Sorted<Integer> sorted = getSortedCollection();
		IntStream.range(0, N_ELEMENTS).forEach(i -> sorted.add(gen.nextInt()));
		runPerformanceTest(sorted);
	}
}
