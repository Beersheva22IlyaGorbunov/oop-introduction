package telran.memory;

public class MemoryOperations {
	public static int getMaxAvailableMemory() {
		byte[] ar = null;
		int maxAvailable = 0;
		int left = 0;
		int right = Integer.MAX_VALUE;
		int middle = right / 2;
		while (left <= right) {
			ar = null;
			try {
				ar = new byte[middle];
				maxAvailable = middle;
				left = middle + 1;
			} catch(Throwable e) {
				right = middle - 1;
			}
			middle = left + (right - left) / 2;
		}
		return maxAvailable;
	}
	
	
}
