package telran.shapes;

import java.util.Arrays;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;
	protected SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}
	
	public String[] presentation(int offset) {
		String[] res = new String[width];
		addTopCorner(res, offset);
		res[res.length - 1] = getLine(offset);
		return res;
	}

	private void addTopCorner(String[] res, int offset) {
		int rightBorder = res.length - 1;
		for (int i = 0; i < rightBorder; i++) {
			char[] line = new char[width + offset];
			Arrays.fill(line, ' ');
			int diagPos = isLeftDiagonal ? i : rightBorder - i;
			int sidePos = isLeftDiagonal ? 0 : rightBorder;
			line[diagPos + offset] = symbol.charAt(0);
			line[sidePos + offset] = symbol.charAt(0);
			res[i] = String.valueOf(line);
		}
	}
	
	
}
