import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeSetTest {

    public static void writeWebGraphvizToFile(String webGraphvizRepresentation, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(webGraphvizRepresentation);
            System.out.println("WebGraphviz representation written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void add() {
        BinarySearchTreeSet<Integer> bst = new BinarySearchTreeSet<>(500);
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);

        assertTrue(bst.contains(30));

        assertDoesNotThrow(() -> bst.add(60));

        System.out.println(bst.generateDot());
    }

    @Test
    public void testStresTree() {
        BinarySearchTreeSet<Integer> bst = new BinarySearchTreeSet<>(5000);
        for (int i = 0; i < 2_500; i++) {
            int randomNumber = (int) Math.floor(Math.random() * 2_500);
            bst.add(randomNumber);
            writeWebGraphvizToFile(bst.generateDot(), "bstSet.dot");
        }

        System.out.println(bst.generateDot());
    }
}