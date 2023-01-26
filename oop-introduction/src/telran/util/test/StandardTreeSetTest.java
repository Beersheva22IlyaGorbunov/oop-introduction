package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.Sorted;
import telran.util.StandardTreeSet;

public class StandardTreeSetTest extends SortedTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new StandardTreeSet<>();
		super.setUp();
	}

	@Override
	protected Sorted<Integer> getSortedCollection() {
		return new StandardTreeSet<Integer>();
	}
}
