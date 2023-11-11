import java.util.HashMap;
import java.util.*;

public class Dijkstra<T> {

    private Graph<T> graph;

    private HashMap<T, T> parent;

    private HashMap<T, Integer> distance;

    public Dijkstra(Graph<T> graph) {
        this.graph = graph;
    }

    public void findShortestPath(T source) {
        distance = new HashMap<>(50);
        parent = new HashMap<>(50);
        PriorityQueue<T> pq = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        for (T node : graph.getNodes()) {
            distance.put(node, Integer.MAX_VALUE);
            parent.put(node, null);
        }

        distance.put(source, 0);
        pq.add(source);

        while (!pq.isEmpty()) {
            T current = pq.poll();
            List<T> neighbors = graph.getNeighbors(current);

            for (T neighbor : neighbors) {
                int newDistance = distance.get(current) + graph.getWeight(current, neighbor);
                if (newDistance < distance.get(neighbor)) {
                    distance.put(neighbor, newDistance);
                    parent.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }
    }

    public List<T> getShortestPath(T source, T destination) {
        List<T> path = new ArrayList<>();
        T current = destination;

        findShortestPath(source);

        if (parent.get(current) == null) {
            return path;
        }

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);

        return path;
    }

}
