package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.StandardTreeSet;

public class StandardTreeSetTest extends SortedTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new StandardTreeSet<>();
		super.setUp();
	}
}
