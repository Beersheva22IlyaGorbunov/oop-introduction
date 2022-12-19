package telran.shapes;

public class Canvas extends Shape {
	private Shape[] shapes;
	private String direction = "row";
	private int margin = 5;
	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	@Override
	public int getHeight() {
		return direction == "row" ? super.getHeight() : sumHeights() + margin * (shapes.length - 1);
	}
	
	@Override
	public String[] presentation(int offset) {
		String[] res;
		checkDirection();
		int[][] sizesTemp = getShapesSizes();
		if (direction == "column") {
			res = verticalPresentation(offset);
		} else {
			res = horizontalPresentation(offset);
		}
		restoreSizes(sizesTemp);
		return res;
	}
	
	private void checkDirection() {
		for (int i = 0; i < shapes.length; i++) {
			if (shapes[i] instanceof Canvas) {
				((Canvas)shapes[i]).setDirection(direction);
			}
		}
	}

	private int[][] getShapesSizes() {
		int[][] sizes = new int[shapes.length][2];
		for (int i = 0; i < shapes.length; i++) {
			sizes[i][0] = shapes[i].getWidth();
			sizes[i][1] = shapes[i].getHeight();
		}
		return sizes;
	}

	private void restoreSizes(int[][] initialSizes) {
		for (int i = 0; i < initialSizes.length; i++) {
			shapes[i].setWidth(initialSizes[i][0]);
			shapes[i].setHeight(initialSizes[i][1]);
		}
	}

	private void normalizeHeight() {
		int presentationHeight = getHeight();
		for (int i = 0; i < shapes.length; i++) {
			shapes[i].setHeight(presentationHeight);
		}
	}

	private void normalizeWidth() {
		int presentationWidth = getWidth();
		for (int i = 0; i < shapes.length; i++) {
			shapes[i].setWidth(presentationWidth);
		}
	}

	private String[] horizontalPresentation(int offset) {
		normalizeHeight();
		String[] result = shapes[0].presentation(offset);
		for (int i = 1; i < shapes.length; i++) {
			result = joinHorizontalArrays(result, shapes[i].presentation(margin));
		}
		return result;
	}
	
	private String[] joinHorizontalArrays(String[] array1, String[] array2) {
		String[] result = new String[array1.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = array1[i] + array2[i];
		}
		return result;
	}

	private String[] verticalPresentation(int offset) {
		normalizeWidth();
		int presentationHeight = getHeight();
		String[] result = new String[presentationHeight];
		int index = 0;
		for (int i = 0; i < shapes.length - 1; i++) {
			String[] shape = shapes[i].presentation(offset);
			insertVerticalArrays(index, result, shape);
			insertVerticalMargin(index + shape.length, offset, result);
			index += shape.length + margin;
		}
		insertVerticalArrays(index, result, shapes[shapes.length - 1].presentation(offset));
		return result;
	}
	
	private void insertVerticalMargin(int index, int offset, String[] result) {
		int width = getWidth() + offset;
		for (int i = index; i < index + margin; i++) {
			result[i] = " ".repeat(width);
		}
	}

	private int sumHeights() {
		int sum = 0;
		for (int i = 0; i < shapes.length; i++) {
			sum += shapes[i].getHeight();
		}
		return sum;
	}

	private void insertVerticalArrays(int index, String[] result, String[] addedArr) {
		System.arraycopy(addedArr, 0, result, index, addedArr.length);
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setMargin(int margin) {
		this.margin = margin;
	}
}