package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

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
}
