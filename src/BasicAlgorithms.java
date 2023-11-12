import java.util.ArrayList;
import java.util.Comparator;

public class BasicAlgorithms {


    public static void mergeSort(String[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    private static void merge(String[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] leftArray = new String[n1];
        String[] rightArray = new String[n2];

        // Copy data
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);
        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }


    static station binarySearch(ArrayList<station> stations, String stationOption) {
        stations.sort(Comparator.comparing(station::getName_Medium));

        int left = 0;
        int right = stations.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int compareResult = stations.get(mid).getName_Medium().compareToIgnoreCase(stationOption);

            if (compareResult == 0) {
                return stations.get(mid);
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        throw new IllegalArgumentException("niet gevonden");
    }

    public static station linearSearch(ArrayList<station> stations, String target) {
        for (station station : stations) {
            if (station.getName_Medium().equalsIgnoreCase(target)) {
                return station; // Station gevonden
            }
        }
        throw new IllegalArgumentException("niet gevonden");// Station niet gevonden
    }

    public static void insertionSort(String[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            String key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
