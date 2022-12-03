package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.Rectangle;
import telran.shapes.Square;
import telran.shapes.SquareLeftTriangle;
import telran.shapes.SquareRightTriangle;
import telran.shapes.SquareTriangle;

class ShapesTests {

	@Test
//	@Disabled
	void rectangleTest() {
		System.out.println("RECTANGLE");
		Rectangle rectangle = new Rectangle(10, 8);
		Rectangle.setSymbol("#");
		assertEquals(10, rectangle.getWidth());
		assertEquals(8, rectangle.getHeight());
		displayStrings(rectangle.presentation(5));
	}
	
	@Test
//	@Disabled
	void squareTest() {
		System.out.println("SQUARE");
		Square square = new Square(10);
		Rectangle.setSymbol("%");
		assertEquals(10, square.getWidth());
		assertEquals(10, square.getHeight());
		square.setHeight(5);
		square.setWidth(15);
		assertEquals(15, square.getWidth());
		assertEquals(15, square.getHeight());
		displayStrings(square.presentation(8));
	}
	
	@Test
	void squareTriangle() {
		SquareRightTriangle rightTriangle = new SquareRightTriangle(5);
		SquareLeftTriangle leftTriangle = new SquareLeftTriangle(7);
		rightTriangle.setWidth(8);
		rightTriangle.setHeight(10);
		assertEquals(10, rightTriangle.getWidth());
		assertEquals(10, rightTriangle.getHeight());
		displayStrings(rightTriangle.presentation(2));
		displayStrings(leftTriangle.presentation(3));
	}
	
	private void displayStrings(String strings[]) {
		for (String str: strings) {
			System.out.println(str);
		}
	}
}
