import java.util.HashMap;
import java.util.*;

public abstract class AStar<T> {

    private Graph<T> graph;

    public AStar(Graph<T> graph) {
        this.graph = graph;
    }

    public List<T> findShortestPath(T start, T goal) {
        HashMap<T, Double> gScore = new HashMap<>(50);
        HashMap<T, Double> fScore = new HashMap<>(50);
        HashMap<T, T> cameFrom = new HashMap<>(50);
        PriorityQueue<T> openSet = new PriorityQueue<>(Comparator.comparingDouble(fScore::get));

        gScore.put(start, 0.0);
        fScore.put(start, heuristic(start, goal));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            T current = openSet.poll();

            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            for (T neighbor : graph.getNeighbors(current)) {
                double tentativeGScore = gScore.get(current) + graph.getWeight(current, neighbor);

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, goal));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    // Define your heuristic function (admissible, meaning it never overestimates the cost to reach the goal)
    private double heuristic(T node, T goal) {
        return 0.0;
    }
    private List<T> reconstructPath(HashMap<T, T> cameFrom, T current) {
        List<T> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }

        Collections.reverse(path);
        return path;
    }

    protected abstract double heuristic(station node, station goal);
}
