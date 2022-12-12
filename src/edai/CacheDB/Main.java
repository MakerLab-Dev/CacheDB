package edai.CacheDB;

public class Main {
    public static void main(String[] args) {
        // Quick test
        try {
            Cache cache = new Cache();

            cache.put("key1", "value1");
            cache.put("key2", "value2");
            cache.put("key3", "value3");

            System.out.println("key1: " + cache.get("key1"));
            System.out.println("key2: " + cache.get("key2"));
            System.out.println("key3: " + cache.get("key3"));

            System.out.println("key1: " + cache.getOrDefault("key1", "default"));
            System.out.println("key2: " + cache.getOrDefault("key2", "default"));

            System.out.println("key1 exists: " + cache.exists("key1"));
            cache.remove("key1");
            System.out.println("key1 exists: " + cache.exists("key1"));
            System.out.println("key1: " + cache.getOrDefault("key1", "default"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}