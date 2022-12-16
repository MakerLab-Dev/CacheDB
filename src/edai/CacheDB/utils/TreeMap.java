package edai.CacheDB.utils;

import edai.CacheDB.exceptions.*;

public class TreeMap {
        private final BinaryTree<MapEntry> tree = new BinaryTree<>();

    public void put(String key, String value) {
        MapEntry entry = new MapEntry(key, value);
        tree.insert(entry);
    }

    public String get(String key) throws KeyNotFoundException {
        TreeNode<MapEntry> entry = tree.search(new MapEntry(key, null));
        if (entry == null) {
            throw new KeyNotFoundException();
        }
        return entry.getValue().getValue();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public boolean exists(String key) {
        return tree.search(new MapEntry(key, null)) != null;
    }

    public boolean remove(String key) {
        if (!exists(key)) { return false; }

        tree.remove(new MapEntry(key, null));
        return true;
    }

    public String[] keys() {
        Object[] data = tree.toArray();
        String[] keys = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            keys[i] = ((MapEntry) data[i]).getKey();
        }
        return keys;
    }

    public String[] values() {
        Object[] data = tree.toArray();
        String[] values = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            values[i] = ((MapEntry) data[i]).getValue();
        }
        return values;
    }
}
