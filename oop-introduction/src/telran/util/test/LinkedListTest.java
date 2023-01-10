package telran.util.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class LinkedListTest extends ListTest {
	LinkedList <Integer> linkedList;
//	{10, 100, -5, 134, 280, 120, 15}
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
		linkedList = (LinkedList<Integer>) collection;
	} 
	@Test
	void hasLoopMiddleTest() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(5, 3);
		assertTrue(linkedList.hasLoop());
	}
	@Test
	void hasLoopHeadTest() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(5, 0);
		assertTrue(linkedList.hasLoop());
	}
	@Test
	void hasLoopTailTest() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(6, 0);
		assertTrue(linkedList.hasLoop());
	}
	@Test
	void hasLoopSelfloopTest() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(3, 3);
		assertTrue(linkedList.hasLoop());
	}
	@Test
	void hasLoopOneElemTest() {
		LinkedList <Integer> linkedList = new LinkedList<>();
		linkedList.add(3);
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(0, 0);
		assertTrue(linkedList.hasLoop());
	}
	@Test
	void isLoopTestEven() {
		linkedList.add(300);
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(linkedList.size() - 1, 0);
		assertTrue(linkedList.hasLoop());
		
	}
	@Test
	void isLoopTestOdd() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(linkedList.size() - 1, 0);
		assertTrue(linkedList.hasLoop());
		
	}
	@Test
	void isLoopNoOneNode() {
		LinkedList<Integer> linkedList = new LinkedList<>();
		assertFalse(linkedList.hasLoop());
		linkedList.add(10);
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(0, 0);
		assertTrue(linkedList.hasLoop());
	}
}