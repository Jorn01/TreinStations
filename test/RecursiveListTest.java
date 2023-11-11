import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveListTest {

    @Test
    void add() {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);
        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
    }

    @Test
    void contains() {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertTrue(list.contains(4));
        assertFalse(list.contains(5));
    }

    @Test
    void size() {
        RecursiveList<Integer> list = new RecursiveList<>();
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.add(2);
        assertEquals(2, list.size());
        list.add(3);
        assertEquals(3, list.size());
        list.add(4);
        assertEquals(4, list.size());
    }

    @Test
    void getCount() {
        RecursiveList<Integer> list = new RecursiveList<>();
        assertEquals(0, list.getCount());
        list.add(1);
        assertEquals(1, list.getCount());
        list.add(2);
        assertEquals(2, list.getCount());
        list.add(3);
        assertEquals(3, list.getCount());
        list.add(4);
        assertEquals(4, list.getCount());
    }

    @Test
    void get() {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
    }

    @Test
    void isEmpty() {
        RecursiveList<Integer> list = new RecursiveList<>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
        list.add(2);
        assertFalse(list.isEmpty());
        list.add(3);
        assertFalse(list.isEmpty());
        list.add(4);
        assertFalse(list.isEmpty());
    }

    @Test
    void peekTail() {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(4, list.peekTail());
    }

    @Test
    void peekHead() {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);
        assertEquals(1, list.peekHead());
    }

    @Test
    void testGetOnEmptyList() {
        RecursiveList<Integer> list = new RecursiveList<>();
        assertNull(list.get(0));
    }

    @Test
    void testToStringEmptyList() {
        RecursiveList<Integer> list = new RecursiveList<>();
        assertEquals("", list.toString());
    }

    @Test
    void testIterator() {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        int i = 0;
        for (Integer data : list) {
            assertEquals(++i, data);
        }
    }

    @Test
    void testIteratorEmptyList() {
        RecursiveList<Integer> list = new RecursiveList<>();
        int i = 0;
        for (Integer data : list) {
            assertEquals(++i, data);
        }
    }

    @Test
    void testIteratorWhenNextNull() {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null);
        int i = 0;
        for (Integer data : list) {
            assertEquals(++i, data);
        }
    }

    @Test
    void testEmptyHeadGetHead() {
        RecursiveList<Integer> list = new RecursiveList<>();
        assertNull(list.peekHead());
    }

    @Test
    void testEmptyTailGetTail() {
        RecursiveList<Integer> list = new RecursiveList<>();
        assertNull(list.peekTail());
    }

    @Test
    void testIteratorWhenGivenNull() {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null);
        int i = 0;
        for (Integer data : list) {
            assertEquals(++i, data);
        }
    }


}