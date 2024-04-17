import java.util.Date;

public interface Heap<E> {
    void add(Date key, E element);
    HeapNode removeMin();
    HeapNode peek();
    boolean isEmpty();
    int size();
}
