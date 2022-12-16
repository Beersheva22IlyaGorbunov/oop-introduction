package telran.memory;

public class MemoryOperations {
	public static int getMaxAvailableMemory() {
		boolean isWorked = false;
		byte[] ar = null;
		int left = 0;
		int right = Integer.MAX_VALUE;
		int middle = right / 2;
		while (left <= right) {
			isWorked = true;
			ar = null;
			try {
				ar = new byte[middle];
			} catch(Throwable e) {
				isWorked = false;
			}
			if (isWorked) {
				left = middle + 1;
			}
			else {
				right = middle - 1;
			}
			middle = (left + right) / 2;
		}
		return middle;
	}
}
