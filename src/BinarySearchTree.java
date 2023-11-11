public class BinarySearchTree<T extends Comparable<T>> implements Complete<T> {
    private T root;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> right;

    public BinarySearchTree(T root) {
        this.root = root;
    }


    public void add(T data) {
        if (data == null) {
            return;
        }
        if (data.compareTo(this.root) <= 0) {
            if (this.left == null) {
                this.left = new BinarySearchTree<>(data);
            } else {
                this.left.add(data);
            }
        } else {
            if (this.right == null) {
                this.right = new BinarySearchTree<>(data);
            } else {
                this.right.add(data);
            }
        }
    }

    public BinarySearchTree<T> getLeft() {
        return left;
    }

    public BinarySearchTree<T> getRight() {
        return right;
    }

    public T getRoot() {
        return root;
    }

    public boolean contains(T data) {
        if (this.root == null) {
            return false;
        } else {
            if (data.compareTo(this.root) == 0) {
                return true;
            } else if (data.compareTo(this.root) <= 0) {
                if (this.left == null) {
                    return false;
                } else {
                    return this.left.contains(data);
                }
            } else {
                if (this.right == null) {
                    return false;
                } else {
                    return this.right.contains(data);
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public int size() {
        return getSize();
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public void remove(T data) {
        if (data == null || !contains(data)) {
        } else {
            removeRecursive(data, this);
        }
    }


    public void removeRecursive(T data, BinarySearchTree<T> tree) {
        if (tree.root == data) {
            if (tree.isLeaf()) {
                tree.root = null;
                tree.left = null;
                tree.right = null;
            } else {
                tree.root = tree.right.root;
                tree.left = tree.right.left;
                tree.right = tree.right.right;
            }
        } else if (data.compareTo(tree.root) < 0) {
            removeRecursive(data, tree.left);
        } else {
            removeRecursive(data, tree.right);
        }
    }

    public String printPreOrder() {
        String result = "";
        if (this.root == null) {
            return result;
        } else {
            result += this.root.toString() + " ";
            if (this.left != null) {
                result += this.left.printPreOrder();
            }
            if (this.right != null) {
                result += this.right.printPreOrder();
            }
        }
        return result;
    }

    public String printInOrder() {
        String result = "";
        if (this.root == null) {
            return result;
        } else {
            if (this.left != null) {
                result += this.left.printInOrder();
            }
            result += this.root.toString() + " ";
            if (this.right != null) {
                result += this.right.printInOrder();
            }
        }
        return result;
    }

    public String printPostOrder() {
        String result = "";
        if (this.root == null) {
            return result;
        } else {
            if (this.left != null) {
                result += this.left.printPostOrder();
            }
            if (this.right != null) {
                result += this.right.printPostOrder();
            }
            result += this.root.toString() + " ";
        }
        return result;
    }

    public int getSize() {
        int size = 0;
        if (this.root == null) {
            return size;
        } else {
            size++;
            if (this.left != null) {
                size += this.left.getSize();
            }
            if (this.right != null) {
                size += this.right.getSize();
            }
        }
        return size;
    }

    public String generateDot() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph BST {\n");

        generateDot(root, dot);

        dot.append("}\n");
        return dot.toString();
    }

    private void generateDot(T data, StringBuilder dot) {
        if (data != null) {
            // Check if the node is a leaf (both left and right are null)
            if (left == null && right == null) {
                return;  // Skip adding leaves to the DOT string
            }
            if (left != null && left.root != null) {
                dot.append("  ").append(data.toString()).append(" -> ").append(left.root.toString()).append(";\n");
                left.generateDot(left.root, dot);
            }

            if (right != null && right.root != null) {
                dot.append("  ").append(data.toString()).append(" -> ").append(right.root.toString()).append(";\n");
                right.generateDot(right.root, dot);
            }
        }
    }

    public int getHeight(T data) {
        int height = 0;
        if (this.root == null) {
            return height;
        } else {
            if (data.compareTo(this.root) == 0) {
                return height;
            } else if (data.compareTo(this.root) < 0) {
                height = this.left.getHeight(data);
            } else {
                height = this.right.getHeight(data);
            }
        }
        return height + 1;
    }

    public int getDepth(T data) {
        int depth = 0;
        if (this.root == null) {
            return depth;
        } else {
            if (data.compareTo(this.root) == 0) {
                return depth;
            } else if (data.compareTo(this.root) < 0) {
                depth = this.left.getDepth(data);
            } else {
                depth = this.right.getDepth(data);
            }
        }
        return depth + 1;
    }
}

