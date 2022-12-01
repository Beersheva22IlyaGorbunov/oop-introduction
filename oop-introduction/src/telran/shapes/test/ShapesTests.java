package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.shapes.Rectangle;
import telran.shapes.Square;

class ShapesTests {

	@Test
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(5));
	}
	
	@Test
	void squareTest() {
		Square square = new Square(10);
		assertEquals(10, square.getWidth());
		assertEquals(10, square.getHeight());
		displayStrings(square.presentation(5));
	}
	
	private void displayStrings(String strings[]) {
		for (String str: strings) {
			System.out.println(str);
		}
	}
}
