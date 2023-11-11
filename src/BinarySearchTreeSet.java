public class BinarySearchTreeSet<T extends Comparable<T>> extends BinarySearchTree<T> {
    public BinarySearchTreeSet(T root) {
        super(root);
    }

    @Override
    public void add(T data) {
        if (data == null) {
            return;
        }
        if (super.contains(data)) {
            return;
        }
        super.add(data);
    }
}
