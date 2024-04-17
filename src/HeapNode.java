import java.util.Date;

public class HeapNode<E> {
    private Date key;
    private E data;

    public HeapNode(Date key, E data) {
        this.key = key;
        this.data = data;
    }
    public Date getKey() {
        return key;
    }

    public E getData() {
        return data;
    }
}
