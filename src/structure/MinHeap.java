package structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

// this class implements a min heap that stores elements of type A ordered by some key
public class MinHeap<A> {

    // parent i -> children 2i+1, 2i+2
    private long[] heap;
    private Vector<A> idToElements;
    private Map<A, Integer> elementsToId;
    private int size;

    // initialize minHeap with maximum size and key extractor function for objects
    public MinHeap(int maxSize) {
        this.heap = new long[maxSize];
        this.idToElements = new Vector<>(maxSize);
        this.elementsToId = new HashMap<>();
        this.size = 0;
    }

    // insert a new item into the heap
    public void insert(A element, long key) {
        if (size == heap.length) {
            throw new IllegalStateException("Can not insert into full heap");
        }
        this.heap[this.size] = key;
        this.idToElements.set(this.size, element);
        this.elementsToId.put(element, this.size);
        this.pushUp(this.size);
        this.size++;
    }

    // move an element up in the heap to preserve the heap invariant
    private void pushUp(int index) {
        int parent = (index - 1) / 2;
        while (parent >= 0 && heap[index] < heap[parent]) {
            this.swap(index, parent);

            index = parent;
            parent = (parent - 1) / 2;
        }
    }

    // swap two elements at given indices
    private void swap(int i, int j) {
        long tmp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = tmp;

        A atIndex = this.idToElements.get(i);
        A atParent = this.idToElements.get(j);
        this.idToElements.set(i, atParent);
        this.idToElements.set(j, atIndex);
        this.elementsToId.replace(atIndex, j);
        this.elementsToId.replace(atParent, i);
    }

    // look at element at the top of the heap
    public A peek() {
        return this.idToElements.get(0);
    }

    // remove element from the top of the heap
    public A poll() {
        if (this.size == 0) {
            throw new IllegalStateException("Can not poll from empty heap.");
        }
        else if (this.size == 1) {
            this.size = 0;
            return this.idToElements.get(0);
        }
        A top = this.idToElements.get(0);
        this.swap(0, size - 1);
        this.size--;
        this.heapify(0);
        return top;
    }

    // ensure heap structure from subheap rooted at i
    private void heapify(int i) {
        int left = 2*i+1;
        int right = 2*i+2;
        int minimum = i;
        if (left < this.size && this.heap[left] < this.heap[i]) {
            minimum = left;
        }
        if (right < this.size && this.heap[right] < this.heap[minimum]) {
            minimum = right;
        }
        if (minimum != i) {
            this.swap(i, minimum);
            heapify(minimum);
        }
    }

    // decrease the key associated with some elements
    public void decreaseKey(A element, long newKey) {
        int index = this.elementsToId.get(element);
        this.heap[index] = newKey;
        this.pushUp(index);
    }

    // delete an element from the heap by decreasing key to smallest possible value and then polling it
    public void delete(A element) {
        this.decreaseKey(element, Long.MIN_VALUE);
        this.poll();
    }

}
