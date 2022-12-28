package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

public class ArrayListTest {
	ArrayList<Integer> intList = new ArrayList<>();
	
	@Test
	void arrayListTests() {
		intList.add(3);
		intList.add(0, 5);
		assertEquals(2, intList.size());
		assertEquals(0, intList.indexOf(5));
		assertEquals((Integer) 3, intList.get(1));
		
		errorsTest();
		
		intList.add(6);
		intList.add(8);
		intList.add(8);
		intList.add(10);
		intList.add(12);
		intList.add(15);
		
		indexOfTest();
		
		lastIndexOfTest();
		
		removeIfTest();
		//After removeIf rest only 5, 3, 15, null
		
		toArrayTest();
		
		getTest();
		
		sizeTest();
		
		containsTest();
		
		removeTest();
	}

	private void removeTest() {
		assertEquals((Integer) 5, intList.remove(0));
		intList.remove((Integer) 3);
		assertArrayEquals(new Integer[] {15}, intList.toArray(new Integer[] {}));
	}

	private void lastIndexOfTest() {
		assertEquals(4, intList.lastIndexOf(8));
	}

	private void indexOfTest() {
		assertEquals(1, intList.indexOf(3));
		assertEquals(3, intList.indexOf(8));
		assertEquals(7, intList.indexOf(15));
	}

	private void containsTest() {
		assertTrue(intList.contains(5));
		assertTrue(intList.contains(3));
		assertTrue(intList.contains(15));
		intList.add(null);
		assertTrue(intList.contains(null));
		intList.removeIf(element -> element == null);
		assertFalse(intList.contains(null));
		assertFalse(intList.contains(0));
	}

	private void sizeTest() {
		assertEquals(3, intList.size());
		intList.add(null);
		assertEquals(4, intList.size());
	}

	private void toArrayTest() {
		assertArrayEquals(new Integer[]{5, 3, 15, null}, intList.toArray(new Integer[]{3, 5, 8, 10}));
	}

	private void getTest() {
		assertEquals((Integer) 5, intList.get(0));
		assertEquals((Integer) 3, intList.get(1));
		assertEquals((Integer) 15, intList.get(2));
	}

	private void removeIfTest() {
		assertTrue(intList.removeIf(element -> element % 2 == 0));
	}

	private void errorsTest() {
		try {
			intList.remove(3);
		} catch (Throwable e) {
			System.out.println(e);
		}
		try {
			intList.add(4, 2);
		} catch (Throwable e) {
			System.out.println(e);
		}
		int curSize = intList.size();
		try {
			intList.add(null);
			assertTrue(intList.removeIf(element -> element % 2 == 0));
		} catch (Throwable e) {
			intList.remove(curSize);
			System.out.println(e);
		}
	}
}
