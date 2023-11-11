import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private Graph<Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
    }

    @Test
    void addNode() {
        graph.addNode(1);
        assertTrue(graph.getNodes().contains(1));
    }

    @Test
    void containsKey() {
        graph.addNode(1);
        assertTrue(graph.containsKey(1));
        assertFalse(graph.containsKey(2));
    }

    @Test
    void addEdge() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, 1);
        assertTrue(graph.getNeighbors(1).contains(2));
    }

    @Test
    void getNeighbors() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);

        assertTrue(graph.getNeighbors(1).contains(2));
        assertTrue(graph.getNeighbors(1).contains(3));
    }

    @Test
    void getNodes() {
        graph.addNode(1);
        graph.addNode(2);
        assertTrue(graph.getNodes().contains(1));
        assertTrue(graph.getNodes().contains(2));
        assertFalse(graph.getNodes().contains(3));
    }

    @Test
    void size() {
        graph.addNode(1);
        graph.addNode(2);
        assertEquals(2, graph.size());
    }

    @Test
    void toWebGraphViz() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);
        graph.addNode(7);
        graph.addNode(8);


        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 5, 4);
        graph.addEdge(5, 1, 5);
        graph.addEdge(1, 3, 6);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 5, 8);


        String webGraphViz = graph.toWebGraphViz();

        // Print the WebGraphviz representation to the console
        System.out.println("WebGraphviz representation:\n" + webGraphViz);

        // You can use the generated webGraphViz string for visualization
        // through online WebGraphviz tools or Graphviz locally.
    }


    @Test
    void testWebGrapVizFromPP() {
        Graph<String> graph = new Graph<>();
        graph.addNode("V0");
        graph.addNode("V1");
        graph.addNode("V2");
        graph.addNode("V3");
        graph.addNode("V4");
        graph.addNode("V5");
        graph.addNode("V6");

        graph.addEdge("V0", "V1", 2);
        graph.addEdge("V0", "V3", 1);
        graph.addEdge("V1", "V3", 3);
        graph.addEdge("V1", "V4", 10);
        graph.addEdge("V2", "V0", 4);
        graph.addEdge("V2", "V5", 5);
        graph.addEdge("V3", "V2", 2);
        graph.addEdge("V3", "V5", 8);
        graph.addEdge("V3", "V4", 2);
        graph.addEdge("V4", "V6", 6);
        graph.addEdge("V6", "V1", 1);

        String webGraphViz = graph.toWebGraphViz();

        // Print the WebGraphviz representation to the console
        System.out.println("WebGraphviz representation:\n" + webGraphViz);

    }

    @Test
    void testAddNullNode() {
        assertThrows(IllegalArgumentException.class, () -> graph.addNode(null));
    }

    @Test
    void testAddNullEdge() {
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(null, null, null));
    }

    @Test
    void testAddEdgeToNonExistentNode() {
        graph.addNode(1);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 2, 1));
    }

    @Test
    void testGetNeighborsOfNonExistentNode() {
        assertThrows(IllegalArgumentException.class, () -> graph.getNeighbors(1));
    }

    @Test
    void testGetWeightOfNonExistentNode() {
        assertThrows(IllegalArgumentException.class, () -> graph.getWeight(1, 2));
    }

    @Test
    void testGetWeightOfNullNode() {
        assertThrows(IllegalArgumentException.class, () -> graph.getWeight(null, null));
    }

    @Test
    void testGetNeighborsOfNullNode() {
        assertThrows(IllegalArgumentException.class, () -> graph.getNeighbors(null));
    }

    @Test
    void testContainsEdgeOfNullNode() {
        assertThrows(IllegalArgumentException.class, () -> graph.containsEdge(null, null));
    }

    @Test
    void testContainsEdgeOfNonExistentNode() {
        assertThrows(IllegalArgumentException.class, () -> graph.containsEdge(1, 2));
    }

    @Test
    void testContainsEdge() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, 1);
        assertTrue(graph.containsEdge(1, 2));
        assertFalse(graph.containsEdge(2, 1));
    }


}
