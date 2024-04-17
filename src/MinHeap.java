import java.util.Arrays;
import java.util.Date;

public class MinHeap<E> implements Heap<E> {
    private static int CAPACITY = 50;
    private Object[] heapArray;
    private int size;

    public MinHeap() {
        heapArray = new Object[CAPACITY];
        size = 0;
    }

    public MinHeap(int capacity) {
        this.CAPACITY = capacity;
        heapArray = new Object[capacity];
        size = 0;
    }

    public MinHeap(HeapNode[] arr) {
        if (this.CAPACITY < arr.length) {
            this.CAPACITY = arr.length;
        }
        heapArray = arr;
        size = arr.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void updateArray(HeapNode[] newArr) {
        heapArray = newArr;
    }

    @Override
    public void add(Date key, E data) {
        if ( size == CAPACITY ) {
            CAPACITY *= 2;
            Object[] arr = new Object[CAPACITY];
            for(int i=0; i < heapArray.length; i++) {
                arr[i] = heapArray[i];
            }
            heapArray = arr;
        }
        int index = size;
        heapArray[index] = new HeapNode<>(key, data);
        size++;
        heapIfUp(index);
    }


    public HeapNode removeMin() {
        if (isEmpty()) {
            throw new IllegalStateException(("Heap is empty..."));
        }
        HeapNode minData = peek();
        heapArray[0] = heapArray[size - 1];
        size--;
        heapIfDown(0);
        return minData;
    }

    public HeapNode peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty...");
        }
        return ((HeapNode<E>) heapArray[0]);
    }

    public Date peekKey() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty...");
        }
        return ((HeapNode<E>) heapArray[0]).getKey();
    }

    private void heapIfUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heapArray[index], heapArray[parentIndex]) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapIfDown(int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < size && compare(heapArray[leftChild], heapArray[smallest]) < 0) {
            smallest = leftChild;
        }
        if (rightChild < size && compare(heapArray[rightChild], heapArray[smallest]) < 0) {
            smallest = rightChild;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapIfDown(smallest);
        }
    }

    private void swap(int i, int j) {
        Object temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    private int compare(Object a, Object b) {
        return (((HeapNode<E>) a).getKey().compareTo(((HeapNode<E>) b).getKey()));
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.println(heapArray[i]);
        }
    }

    public HeapNode[] returnHeap() {
        HeapNode[] res = new HeapNode[size];
        for (int i = 0; i < size; i++) {
            res[i] = ((HeapNode<E>) heapArray[i]);
        }

        return res;
    }

    public void remove(int index) {
        heapArray[index] = null;
        for (int i = index; i < index - 1; i++) {
            heapArray[i] = heapArray[i+1];
        }
        heapIfUp(index);
    }
}
