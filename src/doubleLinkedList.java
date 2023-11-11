public class doubleLinkedList<T extends Comparable<T>> implements Complete<T> {
    private Node<T> head;

    public doubleLinkedList() {
        this.head = null;
    }


    @Override
    public void add(T data) {
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node<>(data, null, current));
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (size() == 1 && index == 0) {
            head = null;
            return;
        }
        if (index == 0) {
            head = head.getNext();
            head.setPrevious(null);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.getPrevious().setNext(current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrevious(current.getPrevious());
            }
        }
    }

    public void removeData(T data) {
        if (head.getData().equals(data) && size() > 1) {
            head = head.getNext();
            head.setPrevious(null);
        } else if (size() == 1 && head.getData().equals(data)) {
            head = null;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                if (current.getNext().getData().equals(data)) {
                    current.setNext(current.getNext().getNext());
                    if (current.getNext() != null) {
                        current.getNext().setPrevious(current);
                    }
                    return;
                }
                current = current.getNext();
            }
            throw new IllegalArgumentException("Data not found");
        }
    }

    public void insertBefore(Node<T> newNode, Node<T> current) {
        if (current == head) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else {
            newNode.setNext(current);
            newNode.setPrevious(current.getPrevious());
            current.getPrevious().setNext(newNode);
            current.setPrevious(newNode);
        }
    }

    public void insertAfter(Node<T> newNode, Node<T> current) {
        if (current.getNext() == null) {
            current.setNext(newNode);
            newNode.setPrevious(current);
        } else {
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
        }
    }

    @Override
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return sizeRecursive(head);
    }

    private int sizeRecursive(Node<T> head) {
        if (head == null) {
            return 0;
        } else {
            return 1 + sizeRecursive(head.getNext());
        }
    }


    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        Node<T> current = head;
        if (current == null) {
            return null;
        }
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("doubleLinkedList{");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        sb.append("}");
        return sb.toString();
    }
}
