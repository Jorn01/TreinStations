import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class BasicAlgorithmsTest {

    @Test
    void mergeSort() {
        String[] array = {"apple", "banana", "orange", "grape", "kiwi"};

        String[] unsortedArray = Arrays.copyOf(array, array.length);

        int left = 0;
        int right = array.length - 1;

        BasicAlgorithms.mergeSort(array, left, right);

        Arrays.sort(unsortedArray);

        Assertions.assertArrayEquals(unsortedArray, array);
    }

    @Test
    void binarySearch() {
        ArrayList<station> stations = new ArrayList<>();

        stations.add(new station(1, "UTR", "Utrecht Centraal", "NL", 52.089444, 5.110278));
        stations.add(new station(2, "UTRO", "Utrecht Overvecht", "NL", 52.102778, 5.109444));
        stations.add(new station(3, "UTRL", "Utrecht Lunetten", "NL", 52.065556, 5.136389));
        stations.add(new station(4, "UTRT", "Utrecht Terwijde", "NL", 52.090278, 5.043611));
        stations.add(new station(5, "UTRZ", "Utrecht Zuilen", "NL", 52.099444, 5.123611));
        stations.add(new station(6, "UTRVR", "Utrecht Vaartsche Rijn", "NL", 52.076389, 5.119167));
        stations.add(new station(7, "UTRM", "Utrecht Maliebaan", "NL", 52.088611, 5.132778));

        String targetStation = "Utrecht Centraal";

        station target = BasicAlgorithms.binarySearch(stations, targetStation);
        Assertions.assertEquals(targetStation, target.getName_Medium());
    }

    @Test
    void linearSearch() {
        ArrayList<station> stations = new ArrayList<>();

        stations.add(new station(1, "UTR", "Utrecht Centraal", "NL", 52.089444, 5.110278));
        stations.add(new station(2, "UTRO", "Utrecht Overvecht", "NL", 52.102778, 5.109444));
        stations.add(new station(3, "UTRL", "Utrecht Lunetten", "NL", 52.065556, 5.136389));
        stations.add(new station(4, "UTRT", "Utrecht Terwijde", "NL", 52.090278, 5.043611));
        stations.add(new station(5, "UTRZ", "Utrecht Zuilen", "NL", 52.099444, 5.123611));
        stations.add(new station(6, "UTRVR", "Utrecht Vaartsche Rijn", "NL", 52.076389, 5.119167));
        stations.add(new station(7, "UTRM", "Utrecht Maliebaan", "NL", 52.088611, 5.132778));

        String targetStation = "Utrecht Centraal";

        station target = BasicAlgorithms.linearSearch(stations, targetStation);
        Assertions.assertEquals(targetStation, target.getName_Medium());
    }

    @Test
    void insertionSort() {
        String[] array = {"apple", "banana", "orange", "grape", "kiwi"};

        String[] unsortedArray = Arrays.copyOf(array, array.length);

        BasicAlgorithms.insertionSort(array);

        Arrays.sort(unsortedArray);

        Assertions.assertArrayEquals(unsortedArray, array);
    }

    @Test
    void LinearSearchNonExistendInput() {
        ArrayList<station> stations = new ArrayList<>();

        stations.add(new station(1, "UTR", "Utrecht Centraal", "NL", 52.089444, 5.110278));
        stations.add(new station(2, "UTRO", "Utrecht Overvecht", "NL", 52.102778, 5.109444));
        stations.add(new station(3, "UTRL", "Utrecht Lunetten", "NL", 52.065556, 5.136389));
        stations.add(new station(4, "UTRT", "Utrecht Terwijde", "NL", 52.090278, 5.043611));
        stations.add(new station(5, "UTRZ", "Utrecht Zuilen", "NL", 52.099444, 5.123611));
        stations.add(new station(6, "UTRVR", "Utrecht Vaartsche Rijn", "NL", 52.076389, 5.119167));
        stations.add(new station(7, "UTRM", "Utrecht Maliebaan", "NL", 52.088611, 5.132778));

        String targetStation = "nonExistendStation";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BasicAlgorithms.linearSearch(stations, targetStation);
        });
    }

    @Test
    void BinarySearchNonexistendInput() {
        ArrayList<station> stations = new ArrayList<>();

        stations.add(new station(1, "UTR", "Utrecht Centraal", "NL", 52.089444, 5.110278));
        stations.add(new station(2, "UTRO", "Utrecht Overvecht", "NL", 52.102778, 5.109444));
        stations.add(new station(3, "UTRL", "Utrecht Lunetten", "NL", 52.065556, 5.136389));
        stations.add(new station(4, "UTRT", "Utrecht Terwijde", "NL", 52.090278, 5.043611));
        stations.add(new station(5, "UTRZ", "Utrecht Zuilen", "NL", 52.099444, 5.123611));
        stations.add(new station(6, "UTRVR", "Utrecht Vaartsche Rijn", "NL", 52.076389, 5.119167));
        stations.add(new station(7, "UTRM", "Utrecht Maliebaan", "NL", 52.088611, 5.132778));

        String targetStation = "nonExistendStation";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BasicAlgorithms.binarySearch(stations, targetStation);
        });
    }
}