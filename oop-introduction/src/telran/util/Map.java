package telran.util;

import java.util.Objects;

public interface Map<K, V> {
	class Entry<K, V> implements Comparable<Entry<K, V>> {
		private K key;
		private V value;
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Entry<K, V> o) {
			return ((Comparable<K>) key).compareTo(o.key);
		}
		@Override
		public int hashCode() {
			return Objects.hash(key);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("rawtypes")
			Entry  other = (Entry) obj;
			return Objects.equals(key, other.key);
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		public Entry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	/**
	 * 
	 * @param key
	 * @param value
	 * Either adds new entry or updates value in the existing one
	 * @return null, if new entry is added
	 * @return ref to old value, if it was replaced
	 */
	V put(K key, V value);
	
	/**
	 * 
	 * @param key
	 * @param value
	 * Adds new entry, if entry with such a name have not existed yet
	 * @return null if new entry is added
	 * @return reference to value if entry exists
	 */
	
	default public V putIfAbsent(K key, V value) {
		V res = get(key);
		if (res == null) {
			put(key,value);
			res = null;
		}
		return res;
	}
	
	/**
	 * 
	 * @param key
	 * @return value matching to key or null, if key is not existed
	 */
	V get(K key);
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return value with provided key if key exists in 
	 */

	default public V getOrDefault(K key, V defaultValue) {
		V res = get(key);
		return res != null ? res : defaultValue;
	}
	
	/**
	 * 
	 * @param key
	 * @return true, if there is an entry with a given key, otherwise false
	 */ 
	boolean containsKey(K key);
	
	/**
	 * 
	 * @param key
	 * @return true, if there is an entry with a given value, otherwise false
	 */ 
	boolean containsValue(V value);
	
	Collection<V> values();
	Set<K> keySet();
	Set<Entry<K, V>> entrySet();
	
	/**
	 * 
	 * @param key
	 * Remove entry with a given key
	 * @return value of deleted Entry or null, if it was not removed
	 */
	V remove(K key);
}
