package telran.util;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int res;
		if (o1 % 2 == 0) {
			if (o2 % 2 == 0) {
				res = o1 - o2;
			} else {
				res = -1;
			}
		} else {
			if (o2 % 2 == 0) {
				res = 1;
			} else {
				res = o2 - o1;
			}
		}
		return res;
	}

}
