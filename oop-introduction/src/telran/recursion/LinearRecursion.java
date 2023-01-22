package telran.recursion;


public class LinearRecursion {
	public static long factorial(int num) {
		long res = 0;
		if (num < 0) {
			res = factorial(-num);
		} else if (num < 2) {
			res = 1;
		} else {
			res = num * factorial(num - 1);
		}
		return res;
	}
	
	public static int power(int a, int b) {
		int res = 1;
		if (b < 0) {
			throw new IllegalArgumentException();
		}
		if (b > 1) {
			res = multiply(power(a, b - 1), a);
		} else if (b == 1) {
			res = a;
		}
		return res;
	}
	
	public static int multiply(int a, int b) {
		int res = 0;
		if (b < 0) {
			res = -multiply(a, -b);
		} else if (b > 0) {
			res = a + multiply(a, b - 1);
		}
		return res;
	}
	
	public static long sum(int[] ar) {
		return sum(0, ar);
	}

	private static long sum(int firstIndex, int[] ar) {
		long res = 0;
		if (firstIndex != ar.length) {
			res = ar[firstIndex] + sum(firstIndex + 1, ar);
		}
		return res;
	}
	
	public static long square(int x) {
		long res = 0;
		if (x > 1) {
			res = x + (x - 1) + square(x - 1);
		} else if (x == 1) {
			res = 1;
		}
		return res;
	}
	
	public static void reverse(int[] arr) {
		reverse(0, arr);
	}
	
	public static void reverse(int index, int[] arr) {
		if (index < arr.length / 2) {
			swap(index, arr);
			reverse(index + 1, arr);
		}
	}
	
	private static void swap(int index, int[] arr) {
		int temp = arr[index];
		arr[index] = arr[arr.length - index - 1];
		arr[arr.length - index - 1] = temp;
	}
	
	public static boolean isSubstring(String string, String substring) {
		return checkSubstring(string, substring, 0, 0);
	}
	
	public static boolean checkSubstring(String string, String substring, int stringPos, int substrPos) {
		boolean isStarted = false;
		boolean isFound = false;
		if (!isFound && substrPos < substring.length() && stringPos < string.length()) {
			if (string.charAt(stringPos) == substring.charAt(substrPos)) {
				isStarted = true;
			} else if (string.charAt(stringPos) == substring.charAt(0)) {
				isStarted = true;
				substrPos = 0;
			} 
			isFound = checkSubstring(string, substring, stringPos + 1, isStarted ? substrPos + 1 : 0);
		}
		return isFound || substrPos == substring.length();
	}

}
