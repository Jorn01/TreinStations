import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
        int choice = 0;
        // keuze opties voor programma 1-5
        while (choice != 5) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. kijk de route van een station naar een ander station");
            System.out.println("2. sorteer de lijst van stations");
            System.out.println("3. print de informatie van een station");
            System.out.println("4. print de graphviz representatie van de graaf naar een file");
            System.out.println("5. exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    LookUpRoute(scanner, graph, stationMap);
                    break;
                }
                case 2: {
                    SortList(scanner, stations);
                    break;
                }
                case 3: {
                    ZoekAlgoritmes(scanner, stationMap, stations);
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

    private static void ZoekAlgoritmes(Scanner scanner, HashMap<String, station> stationMap, ArrayList<station> stations) {
        System.out.println("geef aan hou je de informatie wil zoeken");
        System.out.println("gebruik Lineair of Binary");
        String searchChoice = scanner.next();


        System.out.println("Geef het station aan waar je informatie over wilt");
        String station = scanner.next();
        System.out.println("De informatie over " + station + " is:");
        station stationInfo = stationMap.get(station);
        System.out.println("Naam: " + stationInfo.getName_Medium());
        System.out.println("Land: " + stationInfo.getCountry());
        System.out.println("Latitude: " + stationInfo.getLatitude());
        System.out.println("Longitude: " + stationInfo.getLongitude());
        station opgezochtStation = null;
        if (searchChoice.equals("Lineair")) {
            opgezochtStation = BasicAlgorithms.linearSearch(stations, station);
        } else if (searchChoice.equals("Binary")) {
            opgezochtStation = BasicAlgorithms.binarySearch(stations, station);
        }
        System.out.println(opgezochtStation);
    }

    private static void SortList(Scanner scanner, ArrayList<station> stations) {
        System.out.println("Geef aan hoe je de lijst wilt sorteren");
        System.out.println("1. Sorteer op naam merge");
        System.out.println("2. Sorteer op naam insertion");
        int sortChoice = scanner.nextInt();
        switch (sortChoice) {
            case 1: {
                BasicAlgorithms.mergeSort((String[]) stations.stream().map(station::getName_Medium).toArray(), 0, stations.size() - 1);
                break;
            }
            case 2: {
                BasicAlgorithms.insertionSort((String[]) stations.stream().map(station::getName_Medium).toArray());
                break;
            }
        }
        stations.forEach(station -> System.out.println(station.getName_Medium()));
    }

    private static void LookUpRoute(Scanner scanner, Graph<station> graph, HashMap<String, station> stationMap) {
        System.out.println("geef het station waar je vandaan komt");
        String departure = scanner.next();
        System.out.println("geef het station waar je heen wilt");
        String arrival = scanner.next();


        System.out.println("Geef aan welk algoritme je wilt gebruiken");
        System.out.println("1. Dijkstra");
        System.out.println("2. A*");

        int algoritmeChoice = scanner.nextInt();
        List<station> path;
        if (algoritmeChoice == 1) {
            Dijkstra<station> dijkstra = new Dijkstra<>(graph);
            path = dijkstra.getShortestPath(stationMap.get(departure), stationMap.get(arrival));
        } else {
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
            path = aStar.findShortestPath(stationMap.get(departure), stationMap.get(arrival));
        }
        System.out.println("de route van " + departure + " naar " + arrival + " is:");
        StringBuilder sb = new StringBuilder();
        path.forEach(station -> sb.append(station.getName_Medium()).append(" -> "));
        sb.delete(sb.length() - 4, sb.length());

        System.out.println(sb);
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