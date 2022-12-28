package edai.CacheDB;

import static org.junit.jupiter.api.Assertions.*;

import edai.CacheDB.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheTest {
    private Cache cache;

    @BeforeEach
    void setUp() {
        cache = new Cache();
    }

    void fillCache() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
    }

    @Test
    void testInsertGet() {
        fillCache();

        try {
            assertEquals("value1", cache.get("key1"));
            assertEquals("value2", cache.get("key2"));
            assertEquals("value3", cache.get("key3"));
        } catch (KeyNotFoundException e) {
            fail("KeyNotFoundException thrown");
        }
        assertEquals("default", cache.getOrDefault("key4", "default"));
    }

    @Test
    void testExists() {
        fillCache();

        assertTrue(cache.exists("key1"));
        assertFalse(cache.exists("key4"));
    }

    @Test
    void testUpdate() {
        fillCache();

        try {
            assertEquals("value2", cache.get("key2"));
            cache.put("key2", "value2 updated");
            assertEquals("value2 updated", cache.get("key2"));
        } catch (KeyNotFoundException e) {
            fail("KeyNotFoundException thrown");
        }
    }

    @Test
    void testGetAll() {
        fillCache();

        String[] keys = cache.getAll();
        assertEquals(3, keys.length);
        assertEquals("key1", keys[0]);
        assertEquals("key2", keys[1]);
        assertEquals("key3", keys[2]);
    }

    @Test
    void testRemove() {
        fillCache();

        try {
            assertTrue(cache.exists("key1"));
            cache.remove("key1");
            assertFalse(cache.exists("key1"));
        } catch (KeyNotFoundException e) {
            fail("KeyNotFoundException thrown");
        }
    }

    @Test
    void testSize() {
        fillCache();

        assertEquals(3, cache.size());
    }

    @Test
    void testClear() {
        fillCache();

        cache.clear();
        assertEquals(0, cache.size());
    }

    @Test
    void testAddNew() {
        fillCache();

        try {
            cache.addNew("key1", "value1");
            fail("KeyAlreadyExistsException not thrown");
        } catch (DuplicatedKeyException e) {
            // Expected
        }
    }

    @Test
    void testPersistence() {
        fillCache();
        assertEquals(3, cache.size());
        cache = new Cache();
        assertEquals(3, cache.size());
    }
}
