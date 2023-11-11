import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AStarTest {

    @Test
    public void testAStar() {
        Graph<String> graph = new Graph<>();

        // Create a graph with nodes and edges
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 3);
        graph.addEdge("C", "E", 2);
        graph.addEdge("D", "E", 1);

        AStar<String> astar = new AStar<>(graph) {
            @Override
            protected double heuristic(station node, station goal) {
                return 0;
            }
        };

        // Define your start and goal nodes
        String startNode = "A";
        String goalNode = "E";

        // Find the shortest path
        List<String> path = astar.findShortestPath(startNode, goalNode);

        // Assert the path or make assertions based on your problem
        System.out.println(path);
        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals("A", path.get(0));
        assertEquals("E", path.get(path.size() - 1));
    }

    @Test
    void testNoPath() {
        Graph<String> graph = new Graph<>();

        // Create a graph with nodes and edges
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 3);
        graph.addEdge("C", "E", 2);
        graph.addEdge("D", "E", 1);

        AStar<String> astar = new AStar<>(graph) {
            @Override
            protected double heuristic(station node, station goal) {
                return 0;
            }
        };

        // Define your start and goal nodes
        String startNode = "A";
        String goalNode = "F";

        // Find the shortest path
        List<String> path = astar.findShortestPath(startNode, goalNode);

        // Assert the path or make assertions based on your problem
        System.out.println(path);
        assertNotNull(path);
        assertTrue(path.isEmpty());
    }
}
