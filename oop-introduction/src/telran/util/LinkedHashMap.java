package telran.util;

public class LinkedHashMap<K, V> extends AbstractMap<K, V> {
	public LinkedHashMap() {
		set = new LinkedHashSet<>();
	}
}
