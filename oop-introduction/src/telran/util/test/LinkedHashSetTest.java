package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import telran.util.LinkedHashSet;

public class LinkedHashSetTest extends SetTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedHashSet<>();
		super.setUp();
	} 
	
	@Test
	void getTest() {
		assertEquals(10, ((LinkedHashSet<Integer>)collection).get(10));
		assertEquals(null, ((LinkedHashSet<Integer>)collection).get(11));
	}
}
