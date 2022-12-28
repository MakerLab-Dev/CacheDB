package edai.CacheDB;

import edai.CacheDB.exceptions.*;
import edai.CacheDB.store.*;

public class Cache implements ICache {
    private final KeyValueStore store;

    public Cache() {
        store = new KeyValueStore();
    }

    /* Specify the path where the cache will be stored. */
    public Cache(String path) {
        store = new KeyValueStore(path);
    }

    /**
     * Get all keys stored in cache.
     * @return array of stored keys
     */
    public String[] getAll() {
        return store.getAllKeys();
    }

    /**
     * Get the value associated with the key passed as argument.
     * @param key Key to look for
     * @return The value associated with the key
     * @throws KeyNotFoundException if key does not exist.
     */
    public String get(String key) throws KeyNotFoundException {
        return store.get(key);
    }

    /**
     * Return the value of key passed as argument. Otherwise, return the
     * default value passed as second argument.
     * @param key Key to look for
     * @param defaultValue Value returned when key does not exist.
     * @return The value associated with the key or the defaultValue if key was not
    found.
     */
    public String getOrDefault(String key, String defaultValue) {
        try {
            return store.get(key);
        } catch (KeyNotFoundException e) {
            return defaultValue;
        }
    }

    /**
     * Check is a key exists in cache.
     * @param key Key to look for
     * @return True if key exists.
     */
    public boolean exists(String key) {
        return store.exists(key);
    }

    /**
     * Add or update the value associated to a key.
     * @param key Key to be stored.
     * @param value Value to be stored.
     * @throws IllegalArgumentException if key or value are null.
     */
    public void put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        store.put(key, value);
    }

    /**
     * Add a value to a new key. If key already exists, it throws an exception.
     * @param key Key to be stored.
     * @param value Value to be stored.
     * @throws DuplicatedKeyException the key already exists.
     */
    public void addNew(String key, String value) throws DuplicatedKeyException {
        if (store.exists(key)) {
            throw new DuplicatedKeyException();
        }
        store.put(key, value);
    }

    /**
     * Remove a key and its value.
     * @param key Key to be stored.
     * @throws KeyNotFoundException if key does not exist.
     */
    public void remove(String key) throws KeyNotFoundException {
        if (!store.exists(key)) {
            throw new KeyNotFoundException();
        }
        store.remove(key);
    }

    /**
     * Count the keys (and values) stored in cache.
     * @return Count of keys.
     */
    public int size() {
        return store.size();
    }

    /**
     * Clear the cache.
     */
    public void clear() {
        store.clear();
    }
}
