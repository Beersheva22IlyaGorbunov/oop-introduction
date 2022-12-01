package telran.shapes;

public class Rectangle {
	public static final String SYMBOL = "*";
	private int width;
	private int height;
	private static String symbol = SYMBOL;

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String symbol) {
		Rectangle.symbol = symbol;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String[] presentation(int offset) {
		String[] res = new String[height];
		String line = getLine(offset);
		res[0] = line;
		int lastLine = height - 1;
		res[lastLine] = line;
		for (int i = 1; i < lastLine; i++) {
			res[i] = getMiddleLine(offset);
		}
		return res;
	}

	private String getMiddleLine(int offset) {
		return getOffset(offset) + symbol + getOffset(width - 2) + symbol;
	}

	private String getLine(int offset) {
		return getOffset(offset) + symbol.repeat(width);
	}

	private String getOffset(int offset) {
		return " ".repeat(offset);
	}
}
