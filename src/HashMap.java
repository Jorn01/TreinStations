import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HashMap<K, V> {
    public MapEntry<K, V>[] HashMap;
    private Integer size;
    private int searchINDEXNUMBER;

    public HashMap(Integer size) {
        this.size = size;
        this.HashMap = new MapEntry[size];
    }

    public void put(K key, V entry) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        } else if (entry == null) {
            throw new IllegalArgumentException("entry cannot be null");
        }
        if ((double) size() / size > 0.75) {
            extendTheList(key, entry);
        } else {
            putRecursive(key, entry, 0);
        }
    }

    public boolean inKeySet(K key) {
        List<K> keys = getKeys();
        for (K keyInKeys : keys) {
            if (Objects.equals(keyInKeys, key)) {
                return true;
            }
        }

        return false;
    }

    private void putRecursive(K key, V entry, int filledIndexes) {
        int currentIndex = (getIndexHash(key) + filledIndexes) % size;  // Use modulo to wrap around
        currentIndex = returnPositive(currentIndex);
        if (isMapFilledAt(currentIndex) && filledIndexes < size) {
            putRecursive(key, entry, filledIndexes + 1);
        } else {
            this.HashMap[currentIndex] = new MapEntry<>(key, entry);
        }
    }

    public V get(K key) {
        return getRecursive(key, 0);
    }

    private V getRecursive(K key, int indexSearch) {
        int currentIndex = (getIndexHash(key) + indexSearch) % size;  // Use modulo to wrap around
        currentIndex = returnPositive(currentIndex);

        MapEntry<K, V> currentEntry = this.HashMap[currentIndex];

        if (currentEntry != null && Objects.equals(currentEntry.getKey(), key) && !currentEntry.isDeleted()) {
            this.searchINDEXNUMBER = currentIndex;
            return currentEntry.getValue();
        } else if (isMapFilledAt(currentIndex)) {
            return getRecursive(key, indexSearch + 1);
        } else {
            return null;  // Key not found
        }
    }


    private int returnPositive(int currentIndex) {
        if (currentIndex < 0) {
            return currentIndex * -1;
        }
        return currentIndex;
    }

    public void remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        } else if (!inKeySet(key)) {
            throw new IllegalArgumentException("key does not exist");
        }
        removeRecursive(key, 0);
    }

    private void removeRecursive(K key, int indexSearch) {
        int currentIndex = (getIndexHash(key) + indexSearch) % size;  // Use modulo to wrap around
        currentIndex = returnPositive(currentIndex);

        MapEntry<K, V> currentEntry = this.HashMap[currentIndex];

        if (currentEntry != null && Objects.equals(currentEntry.getKey(), key)) {
            this.HashMap[currentIndex].setDeleted();
        }
    }

    int size() {
        int filledIndexes = 0;
        for (int i = 0; i < size; i++) {
            if (this.HashMap[i] != null) {
                filledIndexes++;
            }
        }
        return filledIndexes;
    }

    public boolean isEmpty() {
        return size() == 0 || Arrays.stream(this.HashMap).filter(Objects::nonNull).allMatch(MapEntry::isDeleted);
    }


    private int getIndexHash(K key) {
        return key.hashCode() % size;
    }

    private boolean isMapFilledAt(int index) {
        return this.HashMap[index] != null;
    }

    public List<K> getKeys() {
        K[] keys = (K[]) new Object[size];
        for (int i = 0; i < size; i++) {
            if (this.HashMap[i] != null) {
                keys[i] = this.HashMap[i].getKey();
            }
        }
        List<K> keysList = new ArrayList<>();
        for (K key : keys) {
            if (key != null) {
                keysList.add(key);
            }
        }
        return keysList;
    }


    private void extendTheList(K key, V value) {
        HashMap<K, V> newHashMap = new HashMap<K, V>(this.HashMap.length * 2);

        for (K keyOldList : getKeys()) {
            if (keyOldList != null) {
                newHashMap.put(keyOldList, get(keyOldList));
            }
        }
        newHashMap.put(key, value);
        this.HashMap = newHashMap.HashMap;
        this.size = newHashMap.size;
        System.out.println("size : " + size + "length : " + HashMap.length);
    }


    @Override
    public String toString() {
        return "HashMap{" +
                "HashMap=" + Arrays.toString(HashMap) +
                ", size=" + size +
                '}';
    }
}