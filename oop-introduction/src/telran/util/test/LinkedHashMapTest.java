package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.LinkedHashMap;
import telran.util.TreeMap;

public class LinkedHashMapTest extends MapTest  {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		map = new LinkedHashMap<>();
		super.setUp();
	}
}
