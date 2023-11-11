import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<station> stations = new Utils().csvReader("resources/stations.csv");
        ArrayList<track> tracks = new Utils().csvReader("resources/tracks.csv");

        HashMap<String, station> stationMap = new HashMap<>(10);
        for (station s : stations) {
            if (s.getCountry().equals("NL")) {
                stationMap.put(s.getCode(), s);
            }
        }

        Graph<station> graph = new Graph<>() {
            @Override
            public String toWebGraphViz() {
                StringBuilder sb = new StringBuilder();
                sb.append("digraph {\n");
                for (station node : getNodes()) {
                    sb.append("  ").append(node.getCode()).append(";\n");
                }
                for (station fromNode : getNodes()) {
                    for (station destinationNode : getNeighbors(fromNode)) {
                        sb.append("  ").append(fromNode.getCode()).append(" -> ").append(destinationNode.getCode()).append(" [label=\"").append(getWeight(fromNode, destinationNode)).append("\"];\n");
                    }
                }
                sb.append("}");
                return sb.toString();
            }
        };
        for (station s : stations) {
            if (s.getCountry().equals("NL")) {
                graph.addNode(s);
            }
        }
        for (track t : tracks) {
            station departure = stationMap.get(t.getDeparture().toUpperCase());
            station arrival = stationMap.get(t.getArrival().toUpperCase());
            if (departure == null || arrival == null) {
                System.err.println("track " + t + " could not be added to the graph");
            } else {
                int duration = t.getDuration();
                graph.addEdge(departure, arrival, duration);
            }
        }

        // keuze opties voor programma 1-5
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. kijk de route van een station naar een ander station");
            System.out.println("2. sorteer de lijst van stations");
            System.out.println("3. print de informatie van een station");
            System.out.println("4. print de graphviz representatie van de graaf naar een file");
            System.out.println("5. exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("geef het station waar je vandaan komt");
                    String departure = scanner.next();
                    System.out.println("geef het station waar je heen wilt");
                    String arrival = scanner.next();
                    AStar<station> aStar = new AStar<>(graph) {

                        @Override
                        protected double heuristic(station node, station goal) {
                            // Assuming Node has latitude and longitude properties
                            double distance = calculateHaversineDistance(node.getLatitude(), node.getLongitude(), goal.getLatitude(), goal.getLongitude());
                            return distance;
                        }

                        private double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
                            // Radius of the Earth in kilometers
                            final double R = 6371.0;

                            // Convert latitude and longitude from degrees to radians
                            double lat1Rad = Math.toRadians(lat1);
                            double lon1Rad = Math.toRadians(lon1);
                            double lat2Rad = Math.toRadians(lat2);
                            double lon2Rad = Math.toRadians(lon2);

                            // Calculate differences
                            double dLat = lat2Rad - lat1Rad;
                            double dLon = lon2Rad - lon1Rad;

                            // Haversine formula
                            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

                            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

                            // Distance in kilometers
                            double distance = R * c;

                            return distance;
                        }
                    };
                    List<station> path = aStar.findShortestPath(stationMap.get(departure), stationMap.get(arrival));
                    System.out.println("de route van " + departure + " naar " + arrival + " is:");
                    StringBuilder sb = new StringBuilder();
                    path.forEach(station -> sb.append(station.getName_Medium()).append(" -> "));
                    sb.delete(sb.length() - 4, sb.length());

                    System.out.println(sb.toString());
                    break;
                }
                case 2: {
                    System.out.println("Geef aan hoe je de lijst wilt sorteren");
                    System.out.println("1. Sorteer op naam");
                    System.out.println("2. Sorteer op land");
                    System.out.println("3. Sorteer op latitude");
                    System.out.println("4. Sorteer op longitude");
                    int sortChoice = scanner.nextInt();
                    switch (sortChoice) {
                        case 1: {
                            stations.sort(Comparator.comparing(station::getName_Medium));
                            break;
                        }
                        case 2: {
                            stations.sort(Comparator.comparing(station::getCountry));
                            break;
                        }
                        case 3: {
                            stations.sort(Comparator.comparingDouble(station::getLatitude));
                            break;
                        }
                        case 4: {
                            stations.sort(Comparator.comparingDouble(station::getLongitude));
                            break;
                        }
                    }
                    stations.forEach(station -> System.out.println(station.getName_Medium()));
                }
                case 3: {
                    System.out.println("Geef het station aan waar je informatie over wilt");
                    String station = scanner.next();
                    System.out.println("De informatie over " + station + " is:");
                    station stationInfo = stationMap.get(station);
                    System.out.println("Naam: " + stationInfo.getName_Medium());
                    System.out.println("Land: " + stationInfo.getCountry());
                    System.out.println("Latitude: " + stationInfo.getLatitude());
                    System.out.println("Longitude: " + stationInfo.getLongitude());
                    break;
                }
                case 4: {
                    String webGraphvizRepresentation = graph.toWebGraphViz();
                    writeWebGraphvizToFile(webGraphvizRepresentation, "graphviz.dot");
                    break;
                }
            }
        }
    }

    public static void writeWebGraphvizToFile(String webGraphvizRepresentation, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(webGraphvizRepresentation);
            System.out.println("WebGraphviz representation written to " + fileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}