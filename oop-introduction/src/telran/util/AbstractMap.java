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
	public V putIfAbsent(K key, V value) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
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
	public V getOrDefault(K key, V value) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
		} else {
			res = value;
		}
		return res;
	}

	@Override
	public boolean containsKey(K key) {
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		return entry != null;
	}

	@Override
	public boolean containsValue(V value) {
		boolean[] res = {false};
		set.forEach(elem -> { if (isEqual(value, elem.getValue())) {
			res[0] = true;
		}});
		return res[0];
	}

	@Override
	public Collection<V> values() {
		try {
			@SuppressWarnings("unchecked")
			Collection<V> res = set.getClass().getConstructor().newInstance();
			set.forEach(elem -> res.add(elem.getValue()));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
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
			set.forEach(elem -> res.add(elem));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public V remove(K key) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
			set.remove(entry);
		}
		return res;
	}

	protected boolean isEqual(V elem, V pattern) {
		return elem == null ? pattern == elem : elem.equals(pattern);
	}
}
