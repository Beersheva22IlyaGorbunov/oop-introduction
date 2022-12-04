package telran.shapes;

import java.util.Arrays;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;
	protected SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}
	
	public String[] presentation(int offset) {
		String[] res = new String[getWidth()];
		addTopCorner(res, offset);
		res[res.length - 1] = getLine(offset);
		return res;
	}

	private void addTopCorner(String[] res, int offset) {
		int rightBorder = res.length - 1;
		char symbol = getSymbol().charAt(0);
		int sidePos = isLeftDiagonal ? 0 : rightBorder;
		for (int i = 0; i < rightBorder; i++) {
			char[] line = new char[getWidth() + offset];
			Arrays.fill(line, ' ');
			int diagPos = isLeftDiagonal ? i : rightBorder - i;
			line[diagPos + offset] = symbol;
			line[sidePos + offset] = symbol;
			res[i] = String.valueOf(line);
		}
	}
	
	
}
