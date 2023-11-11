import java.util.Comparator;

public class Heap<T> {
    private final T[] data;
    private final Comparator<T> comparator;
    private int size;

    public Heap(int size, Comparator<T> comparator) {
        this.data = (T[]) new Object[size];
        this.size = 0;
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void push(T data) {
        if (getSize() == this.data.length) {
            throw new IllegalStateException("Heap is full");
        } else if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        } else {
            this.data[getSize()] = data;
            size++;
            percolateUp();
        }
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return data[0];
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        data[0] = data[getSize() - 1];
        data[getSize() - 1] = null;
        size--;
        percolateDown();
        return data[0];
    }

    public T getParent(int index) {
        return data[(index - 1) / 2];
    }

    private void percolateUp() {
        int index = getSize() - 1;
        while (index > 0 && comparator.compare(data[index], getParent(index)) < 0) {
            // Swap with parent as long as the current element is less than its parent
            swap(data, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    private void swap(T[] data, int index, int parent) {
        T temp = data[index];
        data[index] = data[parent];
        data[parent] = temp;
    }

    private void percolateDown() {
        int index = 0;
        while (index * 2 + 1 < getSize()) {
            int smallerChild = index * 2 + 1;
            if (index * 2 + 2 < getSize() && comparator.compare(data[index * 2 + 1], data[index * 2 + 2]) > 0) {
                smallerChild = index * 2 + 2;
            }
            if (comparator.compare(data[index], data[smallerChild]) > 0) {
                swap(data, index, smallerChild);
                index = smallerChild;  // Move to the smaller child
            } else {
                break;
            }
        }
    }

    public void buildHeap(T[] data) {
        for (T Data : data) {
            push(Data);
        }
    }

    public String graphViz() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");
        sb.append("  node [shape=record]\n");
        sb.append("  rankdir=LR\n");
        sb.append("  edge [tailclip=false, arrowtail=dot, dir=both]\n");
        sb.append("  null [label=\"null\"]\n");
        sb.append("  null -> node0\n");
        for (int i = 0; i < getSize(); i++) {
            sb.append("  node").append(i).append(" [label=\"<f0> |<f1> ").append(data[i]).append("|<f2> \"]\n");
            if (i * 2 + 1 < getSize()) {
                sb.append("  node").append(i).append(":f0 -> node").append(i * 2 + 1).append(":f1\n");
            } else {
                sb.append("  node").append(i).append(":f0 -> null:f1\n");
            }
            if (i * 2 + 2 < getSize()) {
                sb.append("  node").append(i).append(":f2 -> node").append(i * 2 + 2).append(":f1\n");
            } else {
                sb.append("  node").append(i).append(":f2 -> null:f1\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
}
