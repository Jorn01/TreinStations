import org.junit.jupiter.api.Test;

import java.util.List;

class KriskalAlgorithmTest {
    @Test
    void run() {
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

        KruskalMST<String> k = new KruskalMST<>(graph);
        List<Edge<String>> result = k.run();
        System.out.println(result);
    }

}