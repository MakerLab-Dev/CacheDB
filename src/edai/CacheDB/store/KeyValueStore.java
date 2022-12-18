package edai.CacheDB.store;

import edai.CacheDB.io.*;
import edai.CacheDB.exceptions.*;
import edai.CacheDB.utils.*;

// This class is used to store key-value pairs in a tree map and persist them in a
// file. The key gets hashed and the value is stored in the node with the
// same hash.
// Nota: La estructura de datos que yo hubiera utilizado para implementar
// la cache hubiera sido un HashMap, ya que es la estructura mas adecuada
// por su eficiencia de O(1) de media que comparado con el TreeMap es O(log n).
public class KeyValueStore implements IStore {
    private final String path;
    private final TreeMap map = new TreeMap();

    // Create a new KeyValueStore with the default path.
    public KeyValueStore() {
        this.path = "./";
        loadDumpedCache();
    }

    // Create a new KeyValueStore with the path passed as argument.
    public KeyValueStore(String path) {
        this.path = path;
        loadDumpedCache();
    }

    // Dumps the entire tree map to the file system.
    public void dumpToFS() {
        // Here we save the entire tree map to a file for each key with its value.
        // We create a subdirectory with the starting letter of the key and save
        // the file there with the key value as hexadecimal.
        Object[] keys = map.keys();
        Object[] values = map.values();
        for (int i = 0; i < keys.length; i++) {
            String key = getHash((String) keys[i]);
            String value = (String) values[i];
            String dir = path + "/" + key.charAt(0);
            String file = dir + "/" + key;
            ReadWrite.writeToFile(file, new MapEntry(key, value));
        }
    }

    // Loads the entire tree map from the file system.
    private void loadDumpedCache() {
        for (int i = 0; i < 16; i++) {
            String dir = path + Integer.toHexString(i) + "/";
            String[] subFiles = ReadWrite.getAllFiles(dir);
            if (subFiles != null) {
                for (int j = 0; j < subFiles.length; j++) {
                    MapEntry entry = (MapEntry) ReadWrite.readFromFile(dir + subFiles[j]);
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    // Save the created/modified key-value pair to the file system instead of
    // the entire tree map.
    public void save(String key, String value) {
        ReadWrite.writeToFile(getFileFromKey(key), new MapEntry(key, value));
    }

    private String getFileFromKey(String key) {
        String hash = getHash(key);
        String dir = path + "/" + hash.charAt(0);
        String file = dir + "/" + hash;
        return file;
    }

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
        return map.get(key);
    }

    /**
     * Check is a key exists in cache.
     * @param key Key to look for
     * @return True if key exists.
     */
    public boolean exists(String key) {
        return map.exists(key);
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
        map.put(key, value);
        this.save(key, value);
    }

    /**
     * Remove a key from the store.
     * @param key Key to be removed.
     */
    public boolean remove(String key) {
        if (map.remove(key)) {
            ReadWrite.deleteFile(getFileFromKey(key));
            return true;
        }
        return false;
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
