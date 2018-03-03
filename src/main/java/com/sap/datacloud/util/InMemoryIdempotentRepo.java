package com.sap.datacloud.util;

import java.util.HashMap;
import java.util.Map;

public class InMemoryIdempotentRepo {

	private final Map<String, String> cache = new HashMap<>();

	private InMemoryIdempotentRepo() {

	}

	private static class LazyHolder {

		private static final InMemoryIdempotentRepo INSTANCE = new InMemoryIdempotentRepo();

		private LazyHolder() {

		}

	}

	public static InMemoryIdempotentRepo getContext() {
		return LazyHolder.INSTANCE;
	}

	public String getAttribute(final String key) {
		return cache.get(key);
	}

	public void putAttribute(final String key, final String value) {

		cache.put(key, value);
	}

	public Map<String, String> getCache() {
		return cache;
	}

}
