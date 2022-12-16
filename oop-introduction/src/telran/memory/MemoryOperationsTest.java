package telran.memory;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class MemoryOperationsTest {
	byte[] ar;
	
	@Test
	void maxMemoryTestTest() {
		int maxMemory = MemoryOperations.getMaxAvailableMemory();
		ar = new byte[maxMemory];
		ar = null;
		boolean flagException = false;
		try {
			ar = new byte[maxMemory + 1];
		} catch(Throwable e) {
			flagException = true;
		}
		assertTrue(flagException);
	}
}
