import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoubleLinkedListTest {
    /*

    @Test
    public void testInsertionAtFront() {
        // Test insertion at the front of the list
        // Initialize a double linked list and insert nodes at the front
        // Use assertions to check the correctness of the list

        doubleLinkedList<Integer> list = new doubleLinkedList<Integer>();
        list.insertAtFront(10);
        list.insertAtFront(20);

        assertEquals(20, list.getHead().getData());
        assertEquals(10, list.getHead().getNext().getData());
        assertNull(list.getHead().getPrevious());
    }

    @Test
    public void testInsertionAtEnd() {
        // Test insertion at the end of the list
        // Initialize a double linked list and insert nodes at the end
        // Use assertions to check the correctness of the list

        doubleLinkedList<Integer> list = new doubleLinkedList<Integer>();
        list.insertAtEnd(10);
        list.insertAtEnd(20);

        assertEquals(10, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertNull(list.getHead().getPrevious());
    }

    @Test
    public void testDeletion() {
        // Test deletion from the list
        // Initialize a double linked list, insert nodes, remove some nodes, and check the correctness of the list

        doubleLinkedList<Integer> list = new doubleLinkedList<Integer>();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.insertAtEnd(40);

        System.out.println(list.toString());

        list.remove(2);

        assertEquals(10, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertEquals(40, list.getHead().getNext().getNext().getData());
        assertNull(list.getHead().getNext().getNext().getNext());

        list.remove(0);

        System.out.println(list.toString());

        assertEquals(20, list.getHead().getData());
        assertEquals(40, list.getHead().getNext().getData());
        assertNull(list.getHead().getNext().getNext());

        list.remove(1);

        System.out.println(list.toString());


        assertEquals(20, list.getHead().getData());

        list.remove(0);

        System.out.println(list.toString());


        assertNull(list.getHead());


    }
*/

    @Test
    public void testDoubleLinkedListAlgemeen() {
        doubleLinkedList<Integer> list = new doubleLinkedList<Integer>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        Assertions.assertEquals("doubleLinkedList{10 -> 20 -> 30 -> 40}", list.toString());

        list.insertBefore(new Node<>(5), list.getHead());

        Assertions.assertEquals("doubleLinkedList{5 -> 10 -> 20 -> 30 -> 40}", list.toString());


        list.insertAfter(new Node<>(15), list.getHead().getNext());

        Assertions.assertEquals("doubleLinkedList{5 -> 10 -> 15 -> 20 -> 30 -> 40}", list.toString());


        list.remove(2);

        Assertions.assertEquals("doubleLinkedList{5 -> 10 -> 20 -> 30 -> 40}", list.toString());

        list.remove(0);

        Assertions.assertEquals("doubleLinkedList{10 -> 20 -> 30 -> 40}", list.toString());

        list.removeData(30);

        Assertions.assertEquals("doubleLinkedList{10 -> 20 -> 40}", list.toString());

        list.removeData(10);

        Assertions.assertEquals("doubleLinkedList{20 -> 40}", list.toString());

        list.removeData(40);

        Assertions.assertEquals("doubleLinkedList{20}", list.toString());

        System.out.println(list.toString());

        list.removeData(20);

        Assertions.assertEquals("doubleLinkedList{}", list.toString());
        Assertions.assertNull(list.getHead());
        Assertions.assertEquals(0, list.size());
        Assertions.assertTrue(list.isEmpty());

        list.add(10);
        list.add(20);
        list.insertAfter(new Node<>(15), list.getHead());
        list.insertBefore(new Node<>(5), list.getHead());

        Assertions.assertEquals("doubleLinkedList{5 -> 10 -> 15 -> 20}", list.toString());
        Assertions.assertEquals(4, list.size());
        Assertions.assertFalse(list.isEmpty());

        list.removeData(15);

        Assertions.assertEquals("doubleLinkedList{5 -> 10 -> 20}", list.toString());
        Assertions.assertEquals(3, list.size());
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void edgeCases() {
        doubleLinkedList<Integer> list = new doubleLinkedList<Integer>();

        Assertions.assertTrue(list.isEmpty());
        Assertions.assertEquals(0, list.size());
        Assertions.assertNull(list.getHead());
        Assertions.assertNull(list.getTail());

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        Assertions.assertThrows(IllegalArgumentException.class, () -> list.remove(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.removeData(90));

        list.insertBefore(new Node<>(5), list.getHead().getNext());

        Assertions.assertEquals("doubleLinkedList{10 -> 5 -> 20 -> 30 -> 40}", list.toString());

        list.insertAfter(new Node<>(15), list.getTail());

        Assertions.assertEquals("doubleLinkedList{10 -> 5 -> 20 -> 30 -> 40 -> 15}", list.toString());

        Assertions.assertTrue(list.contains(10));
        Assertions.assertFalse(list.contains(9999));

        list.removeData(10);
        list.removeData(20);
        list.removeData(30);
        list.removeData(40);
        list.removeData(15);
        list.removeData(5);

        Assertions.assertTrue(list.isEmpty());
        Assertions.assertEquals(0, list.size());
        Assertions.assertNull(list.getHead());
        Assertions.assertNull(list.getTail());

        list.add(10);
        list.remove(0);
        Assertions.assertEquals("doubleLinkedList{}", list.toString());


        list.add(10);
        System.out.println(list.getHead().toString());
        Assertions.assertEquals("Node{data=10null}", list.getHead().toString());
    }
}
