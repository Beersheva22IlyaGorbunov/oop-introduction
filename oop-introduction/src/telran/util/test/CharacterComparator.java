package telran.util.test;

import java.util.Comparator;

public class CharacterComparator implements Comparator<Character> {
	
	@Override
	public int compare(Character o1, Character o2) {
		return o1 - o2;
	}

}
