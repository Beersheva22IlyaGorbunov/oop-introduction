package telran.cipher.test;

import static org.junit.jupiter.api.Assertions.*;
import telran.cipher.BaseCipher;

import org.junit.jupiter.api.Test;

class BaseChiperTest {

	@Test
	void BaseCipherRandomTests() {
		System.out.println("\n*******   BaseCipherRandomTests   *******\n");
		for (int i = 0; i < 150000; i++) {
			int alphabetLength = (int) (Math.random() * (126 - 33 + 1));
			int randomInt = (int) (Math.random() * (100000000));
			BaseCipher newCipher = new BaseCipher(alphabetLength);
			System.out.println(String.format("Length of alphabet: %s; Random number: %s", alphabetLength, randomInt));
			System.out.println(String.format("Generated key: %s", newCipher.key));
			String ciphered = newCipher.cipher(randomInt);
			System.out.println(ciphered);
			int deciphered = newCipher.decipher(ciphered);
			System.out.println(deciphered);
			System.out.println();
			assertEquals(randomInt, deciphered);
		}
		for (int i = 0; i < 150000; i++) {
			int alphabetLength = (int) (Math.random() * (126 - 33 + 1));
			int randomInt = (int) (Math.random() * (100));
			BaseCipher newCipher = new BaseCipher(alphabetLength);
			System.out.println(String.format("Length of alphabet: %s; Random number: %s", alphabetLength, randomInt));
			System.out.println(String.format("Generated key: %s", newCipher.key));
			String ciphered = newCipher.cipher(randomInt);
			System.out.println(ciphered);
			int deciphered = newCipher.decipher(ciphered);
			System.out.println(deciphered);
			System.out.println();
			assertEquals(randomInt, deciphered);
		}
	}
	
	@Test
	void BaseCipherBorderTests() {
		System.out.println("\n*******   BaseCipherBorderTests   *******\n");
		
		int alphabetLength = 0;
		int randomInt = (int) (Math.random() * (100));
		BaseCipher newCipher = new BaseCipher(alphabetLength);
		
		System.out.println(String.format("Length of alphabet: %s; Random number: %s", alphabetLength, randomInt));
		System.out.println(String.format("Generated key: %s", newCipher.key));
		System.out.println(String.format("Key length: %s", newCipher.key.length()));
		String ciphered = newCipher.cipher(randomInt);
		System.out.println(ciphered);
		int deciphered = newCipher.decipher(ciphered);
		System.out.println(deciphered);
		System.out.println();
		
		int alphabetLength_1 = 150;
		BaseCipher newCipher_1 = new BaseCipher(alphabetLength_1);
		System.out.println(String.format("Length of alphabet: %s; Random number: %s", alphabetLength_1, randomInt));
		System.out.println(String.format("Generated key: %s", newCipher_1.key));
		System.out.println(String.format("Key length: %s", newCipher_1.key.length()));
		String ciphered_1 = newCipher_1.cipher(randomInt);
		System.out.println(ciphered_1);
		int deciphered_1 = newCipher_1.decipher(ciphered_1);
		System.out.println(deciphered_1);
		System.out.println();
		
		System.out.println("Invalid string sended to decipher");
		int deciphered_1_2 = newCipher.decipher("abc");
		System.out.println(deciphered_1_2);
		System.out.println();
	}
}
