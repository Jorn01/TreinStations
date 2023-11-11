public interface Complete<T extends Comparable<T>> {
    void add(T data);

    boolean contains(T data);

    boolean isEmpty();

    int size();

}
