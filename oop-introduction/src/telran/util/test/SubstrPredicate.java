package telran.util.test;

import java.util.function.Predicate;

public class SubstrPredicate implements Predicate<String> {
	String subStr;
	
	public SubstrPredicate(String subStr) {
		this.subStr = subStr;
	}
	
	@Override
	public boolean test(String t) {
		return t.contains(subStr);
	}
}
