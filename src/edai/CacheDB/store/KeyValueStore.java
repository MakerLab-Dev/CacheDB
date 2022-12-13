package edai.CacheDB.store;

import edai.CacheDB.exceptions.*;
import edai.CacheDB.utils.*;

// This class is used to store key-value pairs in a tree map and persist them in a
// file. The key gets hashed and the value is stored in the node with the
// same hash.
public class KeyValueStore implements IStore {
    private final TreeMap map = new TreeMap();

    /**
     * Get all keys stored in cache.
     * @return array of stored keys
     */
    public String[] getAllKeys() {
        return map.keys();
    }

    /**
     * Get all values stored in cache.
     * @return array of stored values
     */
    public String[] getAllValues() {
        return map.values();
    }

    /**
     * Get the value associated with the key passed as argument.
     * @param key Key to look for
     * @return The value associated with the key
     * @throws KeyNotFoundException if key does not exist.
     */
    public String get(String key) throws KeyNotFoundException {
        return map.get(getHash(key));
    }

    /**
     * Check is a key exists in cache.
     * @param key Key to look for
     * @return True if key exists.
     */
    public boolean exists(String key) {
        return map.exists(getHash(key));
    }

    /**
     * Add or update the value associated to a key.
     * @param key Key to be stored.
     * @param value Value to be stored.
     * @throws IllegalArgumentException if key or value are null.
     */
    public void put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null");
        }
        map.put(getHash(key), value);
    }

    /**
     * Remove a key from the store.
     * @param key Key to be removed.
     */
    public boolean remove(String key) {
        return map.remove(getHash(key));
    }

    /**
     * Count the number of keys stored in the store.
     * @return Number of keys stored. (and therefore the number of values stored)
     */
    public int size() {
        return map.size();
    }

    /**
     * Get the hash of a key.
     * @param key Key to be hashed.
     * @return Hash of the key.
     */
    private String getHash(String key) {
        return String.format("%6x", key.hashCode());
    }

}
