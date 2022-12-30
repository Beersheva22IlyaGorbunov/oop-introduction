package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
	int min;
	int max;
	
	private class RangeIterator implements Iterator<Integer> {
		int current = min;
		@Override
		public boolean hasNext() {
			return current < max;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return current++;
		}		
	}
	
	public Range(int min, int max) {
		checkValues(min, max);
		this.min = min;
		this.max = max;
	}

	
	public boolean checkNumber(int number) {
		return number >= min && number < max;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		checkValues(min, max);
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		checkValues(min, max);
		this.max = max;
	}
	
	private void checkValues(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max value must be greater than min value");
		}
	}
}
