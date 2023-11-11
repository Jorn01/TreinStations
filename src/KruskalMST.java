import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalMST<T> {
    private Graph<T> graph;
    private HashMap<T, T> unionFind;

    public KruskalMST(Graph<T> graph) {
        this.graph = graph;
        this.unionFind = new HashMap<>(10);
    }

    public List<Edge<T>> run() {
        List<Edge<T>> allEdges = getAllEdges();
        List<Edge<T>> minimumSpanningTree = new ArrayList<>();
        allEdges.sort(Comparator.comparing(Edge::getWeight));

        for (Edge<T> edge : allEdges) {
            T fromNode = edge.getFromNode();
            T toNode = edge.getToNode();
            if (!geConnect(fromNode, toNode)) {
                minimumSpanningTree.add(edge);
                mapSchuiven(fromNode, toNode);
            }
        }

        return minimumSpanningTree;
    }

    private List<Edge<T>> getAllEdges() {
        List<Edge<T>> edges = new ArrayList<>();
        for (T fromNode : graph.getNodes()) {
            for (T toNode : graph.getNeighbors(fromNode)) {
                int weight = graph.getWeight(fromNode, toNode);
                edges.add(new Edge<>(fromNode, toNode, weight));
            }
        }
        return edges;
    }

    private boolean geConnect(T node1, T node2) {
        if (unionFind.get(node1) == null || unionFind.get(node2) == null) {
            return false;
        } else {
            return unionFind.get(node1).equals(unionFind.get(node2));
        }
    }

    private void mapSchuiven(T node1, T node2) {
        if (unionFind.get(node1) == null || unionFind.get(node2) == null) {
            unionFind.put(node1, node2);
            return;
        }
        T root1 = unionFind.get(node1);
        T root2 = unionFind.get(node2);
        unionFind.put(root1, root2);
    }
}