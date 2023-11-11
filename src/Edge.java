public class Edge<T> {
    private T fromNode;
    private T toNode;
    private int weight;

    public Edge(T fromNode, T toNode, int weight) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.weight = weight;
    }

    public T getFromNode() {
        return fromNode;
    }

    public T getToNode() {
        return toNode;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "fromNode=" + fromNode +
                ", toNode=" + toNode +
                ", weight=" + weight +
                '}';
    }
}
