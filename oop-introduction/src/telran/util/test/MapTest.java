package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Map;
import telran.util.Map.Entry;

abstract class MapTest {
	protected Map<Integer, String> map;
	
	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
	}
	
	@Test
	void putTest() {
		assertEquals("One", map.put(1, "Один"));
		assertEquals("Один", map.get(1));
		assertNull(map.put(4, "Four"));
		assertEquals("Four", map.get(4));
	}
	
	@Test
	void putIfAbsentTest() {
		assertEquals("One", map.putIfAbsent(1, "Один"));
		assertEquals("One", map.get(1));
		assertNull(map.putIfAbsent(4, "Four"));
		assertEquals("Four", map.get(4));
	}
	
	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertNull(map.get(1000));
	}
	
	@Test
	void getOrDefaultTest() {
		assertEquals("One", map.getOrDefault(1, "Five"));
		assertEquals("Five", map.getOrDefault(1000, "Five"));
	}
	
	@Test
	void containsKeyTest() {
		assertTrue(map.containsKey(1));
		assertFalse(map.containsKey(4));
		map.put(4, "Four");
		assertTrue(map.containsKey(4));
	}
	
	@Test
	void containsValueTest() {
		assertTrue(map.containsValue("One"));
		assertFalse(map.containsValue("Four"));
		map.put(4, "Four");
		assertTrue(map.containsValue("Four"));
	}
	
	@Test
	void removeTest() {
		assertTrue(map.containsKey(1));
		assertTrue(map.containsValue("One"));
		assertTrue(map.containsKey(2));
		assertTrue(map.containsKey(3));
		
		assertEquals("One", map.remove(1));
		
		assertFalse(map.containsKey(1));
		assertFalse(map.containsValue("One"));
		assertTrue(map.containsKey(2));
		assertTrue(map.containsKey(3));
	}
	
	@Test
	void valuesTest() {
		String[] expected = {"One", "Two", "Three"};
		String[] actual = map.values().toArray(new String[] {});
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void keySetTest() {
		Integer[] expected = {1, 2, 3};
		Integer[] actual = map.keySet().toArray(new Integer[] {});
		assertArrayEquals(expected, actual);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void entrySetTest() {
		Map.Entry<Integer, String>[] expected = (Entry<Integer, String>[])new Entry[]{new Entry<Integer, String>(1, "One"),
																					  new Entry<Integer, String>(2, "Two"),
																					  new Entry<Integer, String>(3, "Three")};
		Map.Entry<Integer, String>[] example = (Entry<Integer, String>[])new Entry[]{};
		Entry<Integer, String>[] actual = map.entrySet().toArray(example);
		assertArrayEquals(expected, actual);
	}
}
