package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;

import telran.util.ArrayList;

public class StreamTest {
	ArrayList<Integer> list;
	Integer[] numbers = {10, 20, 3, 8, 100, 200, -10, -5};
	@BeforeEach
	void setUp() {
		list = new ArrayList<>();
		Arrays.stream(numbers).forEach(a -> list.add(a));
	}
	
	@Test
	void sortTest() {
		Integer[] expected = { -10, -5, 3, 8, 10, 20, 100, 200};
		assertArrayEquals(expected, list.stream().sorted().toArray(Integer[]::new));
	}
	
	@Test
	void sumNegative() {
		assertEquals(-15, list.stream().filter(a -> a < 0).mapToInt(n -> n).sum());
	}
	
	@Test
	void stringArrayTest() {
		String[] expected = {"10", "20", "3", "8", "100", "200", "-10", "-5"};
		assertArrayEquals(expected, list.stream()
				.map(n -> n.toString()).toArray(String[]::new));
	}
	
	@Test
	void stringTest() {
		String expected = "10, 20, 3, 8, 100, 200, -10, -5";
		assertEquals(expected, list.stream()
				.map(n -> n.toString()).collect(Collectors.joining(", ")));
	}
	
	@Test
	void sportLotoTest() {
		new Random().ints(1, 50).distinct().limit(7).forEach(num -> System.out.print(num + "; "));
	}
	
	@Test
	void toArrayShufflingTest() {
		Integer[] sorted = list.stream().sorted().toArray(Integer[]::new);
		Integer[] shuffled = list.toArrayShuffling(numbers);
		System.out.println(Arrays.toString(sorted));
		System.out.println(Arrays.toString(shuffled));
		assertEquals(sorted.length, shuffled.length);
	}
}
