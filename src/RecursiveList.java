import java.util.Iterator;

public class RecursiveList<T> implements Iterable<T> {
    private T data;
    private RecursiveList<T> rest;

    public RecursiveList() {
        this.data = null;
        this.rest = null;
    }

    public T getData() {
        return data;
    }

    public boolean isEmpty() {
        return getData() == null;
    }

    public int getCount() {
        if (isEmpty()) {
            assert rest == null;
            return 0;
        } else {
            assert rest != null;
            return 1 + rest.getCount();
        }
    }


    public T get(int index) {
        if (isEmpty()) {
            assert rest == null;
            return null;
        } else if (index == 0) {
            assert rest != null;
            return getData();
        } else {
            assert rest != null;
            return rest.get(index - 1);
        }
    }

    public int size() {
        if (isEmpty()) {
            assert rest == null;
            return 0;
        } else {
            assert rest != null;
            return 1 + rest.size();
        }
    }


    public boolean contains(T data) {
        if (isEmpty()) {
            assert rest == null;
            return false;
        } else if (getData().equals(data)) {
            assert rest != null;
            return true;
        } else {
            assert rest != null;
            return rest.contains(data);
        }
    }

    public void add(T data) {
        if (isEmpty()) {
            assert rest == null;
            this.data = data;
            this.rest = new RecursiveList<T>();
        } else {
            this.rest.add(data);
        }
    }


    public T peekHead() {
        if (isEmpty()) {
            assert rest == null;
            return null;
        } else {
            assert rest != null;
            return getData();
        }
    }

    public T peekTail() {
        return (isEmpty() ? null : rest.isEmpty() ? getData() : rest.peekTail());
    }

    public String toString() {
        if (isEmpty()) {
            return "";
        } else if (rest.isEmpty()) {
            return getData().toString();
        } else {
            return getData().toString() + ", " + rest.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator(this);
    }

    class MyIterator implements Iterator<T> {
        private RecursiveList<T> current;

        private MyIterator(RecursiveList<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null && !current.isEmpty();
        }

        @Override
        public T next() {
            if (current == null) {
                return null;
            } else {
                T data = current.getData();
                current = current.rest;
                return data;
            }
        }
    }


}
