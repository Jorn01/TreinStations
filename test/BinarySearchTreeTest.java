import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    public static void writeWebGraphvizToFile(String webGraphvizRepresentation, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(webGraphvizRepresentation);
            System.out.println("WebGraphviz representation written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);


        assertTrue(bst.contains(30));
        assertTrue(bst.contains(70));
        assertTrue(bst.contains(20));
        assertTrue(bst.contains(40));
        assertFalse(bst.contains(10));
    }

    @Test
    public void testContains() {
        BinarySearchTree<String> bst = new BinarySearchTree<>("apple");
        bst.add("banana");
        bst.add("cherry");
        bst.add("date");


        assertTrue(bst.contains("banana"));
        assertTrue(bst.contains("cherry"));
        assertFalse(bst.contains("grape"));
    }

    @Test
    public void testRemove() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);

        assertTrue(bst.contains(30));
        assertTrue(bst.contains(40));


        System.out.println(bst.generateDot());

        bst.remove(30);
        bst.remove(20);
        bst.remove(80);
        bst.remove(50);

        System.out.println(bst.generateDot());
    }

    @Test
    public void testPrintPreOrder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        assertEquals("50 ", bst.printPreOrder());
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);

        String expectedOutput = "50 30 20 40 70 ";
        String actualOutput = bst.printPreOrder();

        assertEquals(expectedOutput, actualOutput);

        bst.remove(50);
        bst.remove(30);
        bst.remove(70);
        bst.remove(20);
        bst.remove(40);
        assertEquals("", bst.printPreOrder());
    }

    @Test
    public void testPrintInOrder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        assertEquals("50 ", bst.printInOrder());
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);

        String expectedOutput = "20 30 40 50 70 ";
        String actualOutput = bst.printInOrder();

        assertEquals(expectedOutput, actualOutput);

        bst.remove(50);
        bst.remove(30);
        bst.remove(70);
        bst.remove(20);
        bst.remove(40);
        assertEquals("", bst.printInOrder());
    }

    @Test
    public void testPrintPostOrder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);

        String expectedOutput = "20 40 30 70 50 ";
        String actualOutput = bst.printPostOrder();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintWebGraphWizFormat() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);

        String expectedOutput = "digraph BST {\n" +
                "  50 -> 30;\n" +
                "  30 -> 20;\n" +
                "  30 -> 40;\n" +
                "  50 -> 70;\n" +
                "  70 -> 60;\n" +
                "  70 -> 80;\n" +
                "}\n";
        String actualOutput = bst.generateDot();
        System.out.println(actualOutput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintWebGrapWizEmptyTreeExceptRoot() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);

        String expectedOutput = "digraph BST {\n" +
                "}\n";
        String actualOutput = bst.generateDot();
        System.out.println(actualOutput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEmptyTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(10);

        assertFalse(bst.contains(50));
        assertFalse(bst.contains(30));
        assertFalse(bst.contains(70));
        assertFalse(bst.contains(20));
        assertFalse(bst.contains(40));
        assertFalse(bst.contains(60));
        assertFalse(bst.contains(80));
    }

    @Test
    public void testSize() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);

        assertEquals(7, bst.size());
    }

    @Test
    public void testHeight() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        System.out.println(bst.generateDot());
        assertEquals(2, bst.getHeight(80));
    }

    @Test
    public void getDepth() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        System.out.println(bst.generateDot());
        assertEquals(2, bst.getDepth(80));
    }

    @Test
    public void testGetFucked() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(10);
        bst.add(10);
        bst.add(10);
        bst.add(10);
        bst.add(10);
        bst.add(10);
        bst.add(10);
        bst.add(10);
        bst.add(10);
        bst.add(10);
        bst.add(10);
        System.out.println(bst.generateDot());
        assertEquals("digraph BST {\n" +
                "  10 -> 10;\n" +
                "  10 -> 10;\n" +
                "  10 -> 10;\n" +
                "  10 -> 10;\n" +
                "  10 -> 10;\n" +
                "  10 -> 10;\n" +
                "  10 -> 10;\n" +
                "  10 -> 10;\n" +
                "  10 -> 10;\n" +
                "  10 -> 10;\n" +
                "}\n", bst.generateDot());
    }

    @Test
    public void testStresTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(5000);
        for (int i = 0; i < 2_500; i++) {
            int randomNumber = (int) Math.floor(Math.random() * 2_500);
            bst.add(randomNumber);
            writeWebGraphvizToFile(bst.generateDot(), "bst.dot");
        }

        System.out.println(bst.generateDot());
    }

    @Test
    public void testIsEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        assertFalse(bst.isEmpty());
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        assertFalse(bst.isEmpty());
    }

    @Test
    public void testContainsEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        assertTrue(bst.contains(50));
        bst.remove(50);
        assertFalse(bst.contains(50));
    }

    @Test
    public void testGetters() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);

        System.out.println(bst.generateDot());
        assertEquals(70, bst.getRight().getRoot());
        assertEquals(30, bst.getLeft().getRoot());
    }

    @Test
    public void testRootNull() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(null);
        assertNull(bst.getRoot());
        assertEquals(0, bst.getSize());
        assertEquals("", bst.printInOrder());
        assertEquals("", bst.printPostOrder());
        assertEquals("", bst.printPreOrder());
        assertEquals(0, bst.getHeight(null));
        assertEquals(0, bst.getDepth(null));
    }

    @Test
    void getDepthForLeftBranch() {
        // Create a binary search tree
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);

        // Check the depth for a value on the left branch
        assertEquals(2, tree.getDepth(3));
    }

    @Test
    void getHeightForLeftBranch() {
        // Create a binary search tree
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);

        // Check the height for a value on the left branch
        assertEquals(2, tree.getHeight(3));
    }

    @Test
    void testIteratorNext() {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();

        // Iterate and retrieve elements
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());

        // Ensure hasNext() returns false after all elements are retrieved
        assertFalse(iterator.hasNext());

        // Ensure calling next() after all elements have been retrieved returns null
        assertNull(iterator.next());
        assertNull(iterator.next());
    }
}
