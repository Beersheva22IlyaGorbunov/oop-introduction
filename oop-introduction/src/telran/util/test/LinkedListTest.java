package telran.util.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class LinkedListTest extends ListTest {
	LinkedList <Integer> list;
//	{10, 100, -5, 134, 280, 120, 15}
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
		list = (LinkedList<Integer>) collection;
	} 
	@Test
	void hasLoopMiddleTest() {
		assertFalse(list.hasLoop());
		list.setNext(5, 3);
		assertTrue(list.hasLoop());
	}
	@Test
	void hasLoopHeadTest() {
		assertFalse(list.hasLoop());
		list.setNext(5, 0);
		assertTrue(list.hasLoop());
	}
	@Test
	void hasLoopTailTest() {
		assertFalse(list.hasLoop());
		list.setNext(6, 0);
		assertTrue(list.hasLoop());
	}
	@Test
	void hasLoopSelfloopTest() {
		assertFalse(list.hasLoop());
		list.setNext(3, 3);
		assertTrue(list.hasLoop());
	}
	@Test
	void hasLoopOneElemTest() {
		LinkedList <Integer> list = new LinkedList<>();
		list.add(3);
		assertFalse(list.hasLoop());
		list.setNext(0, 0);
		assertTrue(list.hasLoop());
	}
}