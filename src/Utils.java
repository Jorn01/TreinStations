import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Utils {
    public <T> ArrayList<T> csvReader(String path) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        // index 1 is identifier
        ArrayList<String> rawData = new ArrayList<>();
        rawData.add(path);


        assert scanner != null;
        scanner.nextLine();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");
            rawData.addAll(Arrays.asList(fields));
        }
        scanner.close();
        return convertRawDataTo(rawData);
    }

    public <T> ArrayList<T> convertRawDataTo(ArrayList<String> rawData) {
        String identifier = rawData.get(0);
        rawData.subList(0, 1).clear();
        ArrayList<T> objects = new ArrayList<>();

        switch (identifier) {
            case "resources/stations.csv":
                for (int i = 0; i < rawData.size(); i += 11) {
                    int id = Integer.parseInt(rawData.get(i));
                    String code = rawData.get(i + 1);
                    String name_Medium = rawData.get(i + 4);
                    String country = rawData.get(i + 7);
                    float latitude = Float.parseFloat(rawData.get(i + 9));
                    float longitude = Float.parseFloat(rawData.get(i + 10));
                    objects.add((T) new station(id, code, name_Medium, country, latitude, longitude));
                }
                return objects;
            case "resources/tracks.csv":
                for (int i = 0; i < rawData.size(); i += 5) {
                    String departure = rawData.get(i);
                    String arrival = rawData.get(i + 1);
                    int duration = Integer.parseInt(rawData.get(i + 3));
                    objects.add((T) new track(departure, arrival, duration));
                }
                return objects;
            default:
                return null;
        }
    }

}
