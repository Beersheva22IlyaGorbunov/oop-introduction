package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.shapes.Canvas;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
import telran.shapes.Square;
import telran.shapes.SquareLeftTriangle;
import telran.shapes.SquareRightTriangle;

class CanvasTests {

	@Test
	void canvasTest() {
		String row = "row";
		String column = "column";
		Rectangle rectangle = new Rectangle(10, 20);
		Square square = new Square(15);
		SquareRightTriangle rightTriangle = new SquareRightTriangle(10);
		SquareLeftTriangle leftTriangle = new SquareLeftTriangle(10);
		Shape[] shapes1 = {rectangle, square, rightTriangle, leftTriangle};
		Shape[] shapes2 = {leftTriangle, rectangle, square};
		Canvas canvas1 = new Canvas(10, 10, shapes1);
		Canvas canvas2 = new Canvas(10, 10, shapes2);
		Canvas canvas5 = new Canvas(10, 10, new Shape[] {canvas1, leftTriangle});
		canvas1.setMargin(10);
		canvas1.setDirection(column);
		canvas2.setMargin(10);
		canvas2.setDirection(column);
		canvas5.setDirection(column);
		displayStrings(canvas5.presentation(5));
		displayStrings(square.presentation(5));
	}
	
	private void displayStrings(String strings[]) {
		for (String str: strings) {
			System.out.println(str);
		}
	}

}
