package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.*;

import telran.util.Sorted;
import telran.util.TreeSet;

class TreeSetTest extends SortedTest {
	TreeSet<Integer> tree;
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
		tree = (TreeSet<Integer>) collection;
	}
	
	@Test
	void displayTreeRotatedTest() {
		tree.displayTreeRotated();
	}
	
	@Test
	void heightTreeTest() {
		assertEquals(4, tree.height());
	}
	
	@Test
	void widthTreeTest() {
		assertEquals(4, tree.width());
	}
	
	@Test
	void inversionTest() {
		tree.inversion();
		Integer[] expected = {280, 134, 120, 100, 15, 10, -5};
		Integer[] actual = tree.toArray(empty);
		assertArrayEquals(actual, expected);
		assertTrue(tree.contains(280));
	}

	@Override
	protected Sorted<Integer> getSortedCollection() {
		return new TreeSet<Integer>();
	}
	
	@Test
	void balanceTest() {
		tree.balance();
		assertEquals(3, tree.height());
		assertEquals(4, tree.width());
		System.out.println("************** Balanced tree **************");
		tree.displayTreeRotated();
	}
//	
//	@Test
//	void performanceTestSortedAddingWithBalance() {
//		TreeSet<Integer> sorted = new TreeSet<>();
//		IntStream.range(0, N_ELEMENTS).forEach(i -> sorted.add(i));
//		sorted.balance();
//		runPerformanceTest(sorted);
//	}
}
