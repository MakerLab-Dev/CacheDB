package edai.CacheDB.store;

import edai.CacheDB.exceptions.*;

public interface IStore {
    /**
     * Get all keys stored in cache.
     * @return array of stored keys
     */
    String[] getAllKeys();

    /**
     * Get all values stored in cache.
     * @return array of stored values
     */
    String[] getAllValues();

    /**
     * Get the value associated with the key passed as argument.
     * @param key Key to look for
     * @return The value associated with the key
     * @throws KeyNotFoundException if key does not exist.
     */
    String get(String key) throws KeyNotFoundException;

    /**
     * Check is a key exists in cache.
     * @param key Key to look for
     * @return True if key exists.
     */
    boolean exists(String key);

    /**
     * Add or update the value associated to a key.
     * @param key Key to be stored.
     * @param value Value to be stored.
     * @throws IllegalArgumentException if key or value are null.
     */
    void put(String key, String value);

    /**
     * Remove a key from the store.
     * @param key Key to be removed.
     */
    boolean remove(String key);

    /**
     * Count the number of keys stored in cache.
     * @return Count of keys. (and therefore the number of values stored)
     */
    int size();

    /**
     * Clear the cache.
     */
    void clear();
}
