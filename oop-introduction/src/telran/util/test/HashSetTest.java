package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.*;

public class HashSetTest extends SetTest {
	HashSet <Integer> hashSet;
//	{10, 100, -5, 134, 280, 120, 15}
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new HashSet<>(4, 0.75f);
		super.setUp();
		hashSet = (HashSet<Integer>) collection;
	} 
}
