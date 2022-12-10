package telran.cipher;

public class BaseCipher {
	private static final char CHAR_MAX = 126;
	private static final char CHAR_MIN = 33;
	public String key;
	public int[] keyHelper;
	private int alphabetLength;
	
	public BaseCipher(int length) {
		if (length < 2) {
			length = 2;
		} else if(length > 94) {
			length = 94;
		}
		
		this.key = generateKey(length);
		this.keyHelper = generateHepler();
		this.alphabetLength = length;
	}

	private String generateKey(int length) {
		boolean[] usedSymbols = new boolean[CHAR_MAX - CHAR_MIN + 1];
		String key = "";
		for (int i = 0; i < length; i++) {
			char tempSymbol = 0;
			while (tempSymbol == 0) {
				tempSymbol = generateRandomSymbol(CHAR_MIN, CHAR_MAX);
				if (usedSymbols[tempSymbol - CHAR_MIN] == false) {
					usedSymbols[tempSymbol - CHAR_MIN] = true;
					key += tempSymbol;
				} else {
					tempSymbol = 0;
				}
			}
		}
		return key;
	}
	
	private int[] generateHepler() {
		int length = CHAR_MAX - CHAR_MIN + 1;
		int[] helper = new int[length];
		for (int i = 0; i < alphabetLength; i++) {
			helper[key.charAt(i) - CHAR_MIN] = i;
		}
		return helper;
	}
	
	private char generateRandomSymbol(char min, char max) {
		return (char)(min + Math.random() * (max - min + 1));
	}
	
	public String cipher(int number) {
		return number > 0 ? getCipheredNumber(number) : String.valueOf(key.charAt(0));
	}
	
	private String getCipheredNumber(int number) {
		int digitsQuantity = digitsQuantity(number);
		char[] resArr = new char[digitsQuantity];
		int index = resArr.length - 1;
		do {
			char symbol = key.charAt(number % alphabetLength);
			number /= alphabetLength;
			resArr[index] = symbol;
			index--;
		} while (number > 0);
		return resArr.toString();
	}
	
	private int digitsQuantity(int number) {
		
	    return (int) Math.floor(Math.log(number) / Math.log(alphabetLength)) + 1;
	}
	
	public int decipher(String cipher) {
		int number;
		if (cipherIsValid(cipher)) {
			int cipherLength = cipher.length();
			number = getValue(cipher.charAt(0), cipherLength - 1);
			for (int i = 1; i < cipher.length(); i++) {
				number += getValue(cipher.charAt(i), cipherLength - 1 - i);
			} 
		} else {
			number = -1;
		}
		return number;
	}
	/**
	 * 
	 * @param cipher
	 * @return true if cipher length non zero 
	 * 		   and each element exists in alphabet
	 */
	private boolean cipherIsValid(String cipher) {
		boolean res = true;
		for (int i = 0; i < cipher.length(); i++) {
			if (getNumberFromKey(cipher.charAt(i)) == -1) {
				res = false;
			}
		}
		return cipher.length() > 0 && res;
	}
	
	private int getNumberFromKey(char symbol) {
		return key.charAt(keyHelper[symbol - CHAR_MIN]);
	}
	/**
	 * 
	 * @param symbol
	 * @param position displays quantity of digits on the right side of symbol
	 * @return
	 */
	private int getValue(char symbol, int position) {
		return getNumberFromKey(symbol) * (int) Math.pow(alphabetLength, position);
	}
}