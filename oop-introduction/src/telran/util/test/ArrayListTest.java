package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

public class ArrayListTest {
	ArrayList<Integer> intList = new ArrayList<>();
	
	@Test
	void arrayListTests() {
		ArrayList<Object> newList = new ArrayList<>();
		newList.add("123");
		newList.add("123");
		Object[] strArr1 = newList.toArray(new String[] {"1", "2"});
		intList.add(3);
		intList.add(0, 5);
		assertEquals(2, intList.size());
		assertEquals(0, intList.indexOf(5));
		assertEquals((Integer) 3, intList.get(1));
		errorsTest();
		assertFalse(intList.removeIf(element -> element % 2 == 0));
//		intList.remove(2);
		assertArrayEquals(new Integer[]{5, 3, null, null}, intList.toArray(new Integer[]{3, 5, 8, 10}));
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
	}
}
