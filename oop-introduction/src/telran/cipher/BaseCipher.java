package telran.cipher;

public class BaseCipher {
	private static final char CHAR_MAX = 126;
	private static final char CHAR_MIN = 33;
	public String key;
	private int alphabetLength;
	
	public BaseCipher(int length) {
		if (length < 2) {
			length = 2;
		} else if(length > 94) {
			length = 94;
		}
		
		this.key = generateKey(length);
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
	
	private char generateRandomSymbol(char min, char max) {
		return (char)(min + Math.random() * (max - min + 1));
	}
	
	public String cipher(int number) {
		int alphabetLength = key.length();
		String reversedNumber = "";
		do {
			char symbol = key.charAt(number % alphabetLength);
			number /= alphabetLength;
			reversedNumber += symbol;
		} while (number > 0);

		return reverseString(reversedNumber);
	}
	
	private String reverseString(String string) {
		String res = "";
		for (int i = string.length() - 1; i > -1 ; i--) {
			res += string.charAt(i);
		}
		return res;
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
	
	private boolean cipherIsValid(String cipher) {
		boolean res = true;
		for (int i = 0; i < cipher.length(); i++) {
			if (getNumberFromKey(cipher.charAt(i)) == -1) {
				res = false;
			}
		}
		return res;
	}
	
	private int getNumberFromKey(char symbol) {
		return key.indexOf(symbol);
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
