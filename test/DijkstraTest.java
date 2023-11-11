import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DijkstraTest {
    @Test
    public void testShortestPath() {
        Graph<String> graph = new Graph<>();

        // Create a graph with nodes and edges
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 2);
        graph.addEdge("D", "E", 4);

        Dijkstra<String> dijkstra = new Dijkstra<>(graph);

        System.out.println(graph.toWebGraphViz());
        System.out.println(dijkstra.getShortestPath("A", "E"));
        System.out.println(dijkstra.getShortestPath("A", "C"));
    }

    @Test
    void testShortestPathDijkstra() {
        Graph<String> graph = new Graph<>();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 2);
        graph.addEdge("D", "E", 4);
        graph.addEdge("E", "F", 1);
        graph.addEdge("F", "G", 2);
        graph.addEdge("G", "H", 2);
        graph.addEdge("H", "E", 2);

        Dijkstra<String> dijkstra = new Dijkstra<>(graph);

        System.out.println(graph.toWebGraphViz());

        Assertions.assertEquals(dijkstra.getShortestPath("A", "E"), Arrays.asList("A", "C", "D", "E"));
        Assertions.assertEquals(dijkstra.getShortestPath("A", "C"), Arrays.asList("A", "C"));
        Assertions.assertEquals(dijkstra.getShortestPath("A", "H"), Arrays.asList("A", "C", "D", "E", "F", "G", "H"));
    }

    @Test
    void testNonExistingDestenation() {
        Graph<String> graph = new Graph<>();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 2);
        graph.addEdge("D", "E", 4);
        graph.addEdge("E", "F", 1);
        graph.addEdge("F", "G", 2);
        graph.addEdge("G", "H", 2);
        graph.addEdge("H", "E", 2);

        Dijkstra<String> dijkstra = new Dijkstra<>(graph);

        System.out.println(graph.toWebGraphViz());

        Assertions.assertEquals(dijkstra.getShortestPath("A", "Z"), Arrays.asList());
    }
}
