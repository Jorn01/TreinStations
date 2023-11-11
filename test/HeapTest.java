import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeapTest {

    @Test
    public void testPushAndPop() {
        // Create a min heap of integers
        Heap<Integer> heap = new Heap<Integer>(10, Comparator.naturalOrder());

        // Push elements into the min heap
        heap.push(5);
        heap.push(10);
        heap.push(3);
        heap.push(8);

        // Check the top element (should be the minimum)
        assertEquals(Integer.valueOf(3), heap.peek());

        System.out.println(heap.graphViz());

        heap.pop();
        System.out.println(heap);

        heap.pop();
        System.out.println(heap);

        heap.pop();
        System.out.println(heap);

        heap.pop();
        System.out.println(heap);


        // Check if the min heap is empty
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testDataNullThrow() {
        Heap<Integer> heap = new Heap<Integer>(10, Comparator.naturalOrder());
        try {
            heap.push(null);
        } catch (IllegalArgumentException e) {
            assertEquals("data cannot be null", e.getMessage());
        }
    }

    @Test
    public void testHeapFullThrow() {
        Heap<Integer> heap = new Heap<Integer>(2, Comparator.naturalOrder());
        heap.push(1);
        heap.push(2);
        try {
            heap.push(3);
        } catch (IllegalStateException e) {
            assertEquals("Heap is full", e.getMessage());
        }
    }

    @Test
    public void testPeekIsEmptyThrow() {
        Heap<Integer> heap = new Heap<Integer>(10, Comparator.naturalOrder());
        try {
            heap.peek();
        } catch (IllegalStateException e) {
            assertEquals("Heap is empty", e.getMessage());
        }
    }

    @Test
    public void testPopIsEmptyThrow() {
        Heap<Integer> heap = new Heap<Integer>(10, Comparator.naturalOrder());
        try {
            heap.pop();
        } catch (IllegalStateException e) {
            assertEquals("Heap is empty", e.getMessage());
        }
    }

    @Test
    public void testBuildHeapMethod() {
        Heap<Integer> heap = new Heap<Integer>(10, Comparator.naturalOrder());

        Integer[] data = {5, 10, 3, 8};
        heap.buildHeap(data);

        assertEquals(Integer.valueOf(3), heap.peek());

    }
}
