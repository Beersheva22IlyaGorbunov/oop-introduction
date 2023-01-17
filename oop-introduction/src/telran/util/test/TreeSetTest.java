package telran.util.test;

import org.junit.jupiter.api.*;

import telran.util.TreeSet;

class TreeSetTest extends SortedTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
	}
}
