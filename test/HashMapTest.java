import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test
    void putAndGet() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);

        assertEquals(1, hashMap.get("key1"));
        assertEquals(2, hashMap.get("key2"));
    }

    @Test
    void putAndGetWithCollision() {
        HashMap<String, Integer> hashMap = new HashMap<>(5);
        // These keys will hash to the same index
        hashMap.put("key", 1);
        hashMap.put("yek", 2);

        assertEquals(1, hashMap.get("key"));
        assertEquals(2, hashMap.get("yek"));
    }

    @Test
    void putAndGetAfterResize() {
        HashMap<String, Integer> hashMap = new HashMap<>(2);
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);
        hashMap.put("key3", 3);

        assertEquals(1, hashMap.get("key1"));
        assertEquals(2, hashMap.get("key2"));
        assertEquals(3, hashMap.get("key3"));
    }

    @Test
    void remove() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);

        assertDoesNotThrow(() -> hashMap.remove("key1"));
        assertNull(hashMap.get("key1"));
        assertEquals(2, hashMap.get("key2"));
    }

    @Test
    void removeNonExistentKey() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        hashMap.put("key1", 1);

        assertThrows(IllegalArgumentException.class, () -> hashMap.remove("key2"));
    }

    @Test
    void removeWithCollision() {
        HashMap<String, Integer> hashMap = new HashMap<>(5);
        hashMap.put("key", 1);
        hashMap.put("yek", 2);

        assertDoesNotThrow(() -> hashMap.remove("key"));
        assertNull(hashMap.get("key"));
        assertEquals(2, hashMap.get("yek"));
    }

    @Test
    void putNullKey() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);

        assertThrows(IllegalArgumentException.class, () -> hashMap.put(null, 1));
    }

    @Test
    void putNullValueThrows() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> hashMap.put("key", null));
    }

    @Test
    void mapCapacity() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        assertEquals(10, hashMap.HashMap.length);

        hashMap.put("key1", 1);
        hashMap.put("key2", 2);

        // After putting two elements, the map should not change its capacity
        assertEquals(10, hashMap.HashMap.length);
    }

    @Test
    void fillAndWrapAround() {
        int initialCapacity = 10;
        HashMap<String, Integer> hashMap = new HashMap<>(initialCapacity);

        // Fill the hash map
        for (int i = 0; i < initialCapacity; i++) {
            hashMap.put("key" + i, i);
        }

        // Verify the elements are in place
        for (int i = 0; i < initialCapacity; i++) {
            assertEquals(i, hashMap.get("key" + i));
        }

        // Fill the last index
        hashMap.put("lastKey", 100);

        // Verify the last element is in place
        assertEquals(100, hashMap.get("lastKey"));

        // Insert another element with the same hash code to trigger wrapping around
        hashMap.put("key" + initialCapacity, 200);

        // Verify the new element is in place
        assertEquals(200, hashMap.get("key" + initialCapacity));
    }

    @Test
    void fillAndWrapAroundAndRemove() {
        int initialCapacity = 10;
        HashMap<String, Integer> hashMap = new HashMap<>(initialCapacity);

        // Fill the hash map
        for (int i = 0; i < initialCapacity; i++) {
            hashMap.put("key" + i, i);
        }

        // Verify the elements are in place
        for (int i = 0; i < initialCapacity; i++) {
            assertEquals(i, hashMap.get("key" + i));
        }

        // Fill the last index
        hashMap.put("lastKey", 100);

        // Verify the last element is in place
        assertEquals(100, hashMap.get("lastKey"));

        // Remove an element
        hashMap.remove("key3");

        // Verify the removal
        assertNull(hashMap.get("key3"));

        // Insert another element with the same hash code to trigger wrapping around
        hashMap.put("key" + initialCapacity, 200);

        // Verify the new element is in place
        assertEquals(200, hashMap.get("key" + initialCapacity));
    }

    @Test
    public void testRemoveNullThrows() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> hashMap.remove(null));
    }

    @Test
    public void isEmpty() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);
        assertFalse(hashMap.isEmpty());
        hashMap.remove("key1");
        hashMap.remove("key2");
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testToString() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);
        System.out.println(hashMap.toString());
    }

}
