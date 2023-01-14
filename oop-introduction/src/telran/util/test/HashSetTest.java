package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Predicate;

import org.junit.jupiter.api.*;

import telran.util.*;

public class HashSetTest extends SetTest {
	Random random = new Random();
	private static final int N_RUNS = 10;
	private static final int N_NUMBERS = 100000;
	HashSet <Integer> hashSet;
//	{10, 100, -5, 134, 280, 120, 15}
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new HashSet<>(4, 0.75f);
		super.setUp();
		hashSet = (HashSet<Integer>) collection;
	}
	
	 @Test
	 void hashSetIteratorTest() {
		 Predicate<Integer> allPredicate = n -> true;
		 HashSet<Integer> set = new HashSet<>();
		 fillSet(set, new Integer[] {0, 16, 32, 48, 512, 128});
		 set.removeIf(allPredicate);
		 assertTrue(set.isEmpty());
		 for (int i = 0; i < N_RUNS; i++) {
			 Integer[] bigArray = getRandomArray();
			 fillSet(set, bigArray);
			 set.removeIf(allPredicate);
			 assertTrue(set.isEmpty());
		 }
	 }

	private Integer[] getRandomArray() {
		Integer[] result = new Integer[N_NUMBERS];
		for (int i = 0; i < N_NUMBERS; i++) {
			result[i] = random.nextInt();
		}
		return result;
	}

	private void fillSet(HashSet<Integer> set, Integer[] integers) {
		for (Integer num: numbers) {
			set.add(num);
		}
	}

}
