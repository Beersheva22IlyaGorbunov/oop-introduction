package telran.util;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;
	@Override
	public V put(K key, V value) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value));
		}
		return res;
	}

	@Override
	public V get(K key) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
		}
		return res;
	}

	@Override
	public boolean containsKey(K key) {
		return set.contains(new Entry<>(key, null));
	}

	@Override
	public boolean containsValue(V value) {
		return set.stream().anyMatch(elem -> isEqual(value, elem.getValue()));
	}

	@Override
	public Collection<V> values() {
		Collection<V> res = new ArrayList<>();
		set.forEach(elem -> res.add(elem.getValue()));
		return res;
	}

	@Override
	public Set<K> keySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<K> res = set.getClass().getConstructor().newInstance();
			set.forEach(elem -> res.add(elem.getKey()));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<Entry<K, V>> res = set.getClass().getConstructor().newInstance();
			set.forEach(res::add);
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public V remove(K key) {
		V res = get(key);
		if (res != null) {
			set.remove(new Entry<>(key, null));
		}
		return res;
	}

	protected boolean isEqual(V elem, V pattern) {
		return elem == null ? pattern == elem : elem.equals(pattern);
	}
}
