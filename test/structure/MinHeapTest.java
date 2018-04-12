package structure;

import algorithm.MinimumSpanningTree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    @Test
    void testHeap() {
        MinHeap<String> heap = new MinHeap<>(4);

        // extracting from empty heap should fail
        assertEquals(0, heap.getSize());
        assertThrows(IllegalStateException.class, heap::peek);
        assertThrows(IllegalStateException.class, heap::poll);

        // testing inserting and polling one element
        heap.insert("london", 6);
        assertEquals(1, heap.getSize());
        assertEquals("london", heap.peek());
        assertEquals(1, heap.getSize());
        assertEquals("london", heap.poll());
        assertEquals(0, heap.getSize());
        assertThrows(IllegalStateException.class, heap::peek);

        // testing multiple elements
        heap.insert("london", 6);
        heap.insert("paris", 5);
        heap.insert("virginia", 8);
        heap.insert("rome", 4);
        assertEquals(4, heap.getSize());
        assertEquals("rome", heap.peek());

        // test inserting too much
        assertThrows(IllegalStateException.class, () -> heap.insert("washington", 10));

        // test decrease key
        heap.decreaseKey("virginia", 2);
        assertEquals(4, heap.getSize());
        assertEquals("virginia", heap.peek());

        // test delete
        heap.delete("rome");
        assertEquals(3, heap.getSize());

        // test order
        assertEquals("virginia", heap.poll());
        assertEquals("paris", heap.poll());
        assertEquals("london", heap.poll());
        assertEquals(0, heap.getSize());
        assertThrows(IllegalStateException.class, heap::poll);
    }

    @Test
    public void testHeapSort() {
        int[] nums = new int[] {23,4,6,86,4,-24,0,-2,-764,-4,42,64,2,64};
        MinHeap<Integer> heap = new MinHeap<>(100);
        for (int i = 0; i < nums.length; ++i) {
            heap.insert(nums[i], nums[i]);
        }
        int[] heaped = new int[nums.length];
        for (int i = 0; i < heaped.length; ++i) {
            heaped[i] = heap.poll();
        }
        Arrays.sort(nums);
        assertArrayEquals(nums, heaped);
    }

}