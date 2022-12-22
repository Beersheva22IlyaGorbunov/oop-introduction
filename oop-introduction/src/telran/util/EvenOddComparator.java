package telran.util;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int res;
		if (o1 % 2 == 0) {
			res = o2 % 2 == 0 ? o1 - o2 : -1;
		} else {
			res = o2 % 2 == 0 ? 1 : o2 - o1;
		}
		return res;
	}

}
