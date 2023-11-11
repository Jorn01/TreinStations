import java.util.List;

public class Graph<T> {

    // first T is the node, second T is the neighbor, Integer is the weight
    private HashMap<T, HashMap<T, Integer>> graph;

    public Graph() {
        this.graph = new HashMap<>(10);
    }

    public void addNode(T node) {
        if (node == null) {
            throw new IllegalArgumentException("node cannot be null");
        }
        if (!containsKey(node)) {
            graph.put(node, new HashMap<>(10));
        }
    }

    public boolean containsKey(T node) {
        return graph.inKeySet(node);
    }

    public void addEdge(T fromNode, T destinationNode, Integer weight) {
        if (fromNode == null || destinationNode == null || weight == null) {
            throw new IllegalArgumentException("node or weight cannot be null");
        }
        if (!containsKey(fromNode) || !containsKey(destinationNode)) {
            throw new IllegalArgumentException("node does not exist");
        }
        graph.get(fromNode).put(destinationNode, weight);
    }


    public List<T> getNeighbors(T node) {
        if (node == null) {
            throw new IllegalArgumentException("node cannot be null");
        }
        if (!containsKey(node)) {
            throw new IllegalArgumentException("node does not exist");
        }
        return graph.get(node).getKeys();
    }

    public List<T> getNodes() {
        return graph.getKeys();
    }

    public int size() {
        return graph.size();
    }

    public Integer getWeight(T fromNode, T destinationNode) {
        if (fromNode == null || destinationNode == null) {
            throw new IllegalArgumentException("node cannot be null");
        }
        if (!containsKey(fromNode) || !containsKey(destinationNode)) {
            throw new IllegalArgumentException("node does not exist");
        }
        return graph.get(fromNode).get(destinationNode);
    }

    public boolean containsEdge(T fromNode, T destinationNode) {
        if (fromNode == null || destinationNode == null) {
            throw new IllegalArgumentException("node cannot be null");
        }
        if (!containsKey(fromNode) || !containsKey(destinationNode)) {
            throw new IllegalArgumentException("node does not exist");
        }
        return graph.get(fromNode).inKeySet(destinationNode);
    }

    public String toWebGraphViz() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");
        for (T node : getNodes()) {
            sb.append("  ").append(node).append(";\n");
        }
        for (T fromNode : getNodes()) {
            for (T destinationNode : getNeighbors(fromNode)) {
                sb.append("  ").append(fromNode).append(" -> ").append(destinationNode).append(" [label=\"").append(getWeight(fromNode, destinationNode)).append("\"];\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}

