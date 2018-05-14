package structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTreeTest {

    @Test
    public void testSegmentTree() {
        long[] array = new long[] {10, 15, 4, 32, 10, 5};
        SegmentTree segmentTree = new SegmentTree(array);

        // global minimum
        assertEquals(2, segmentTree.rangeMinimumQuery(0, 5));

        // singleton queries
        assertEquals(0, segmentTree.rangeMinimumQuery(0, 0));
        assertEquals(3, segmentTree.rangeMinimumQuery(3, 3));

        // segment queries
        assertEquals(0, segmentTree.rangeMinimumQuery(0, 1));
        assertEquals(2, segmentTree.rangeMinimumQuery(1, 4));
        assertEquals(5, segmentTree.rangeMinimumQuery(3, 5));
        assertEquals(4, segmentTree.rangeMinimumQuery(3, 4));

        // update
        segmentTree.update(2, 80);
        assertEquals(5, segmentTree.rangeMinimumQuery(0, 5));
        assertEquals(3, segmentTree.rangeMinimumQuery(2, 3));
        segmentTree.update(0, -1);
        assertEquals(0, segmentTree.rangeMinimumQuery(0, 5));
        assertEquals(4, segmentTree.rangeMinimumQuery(1, 4));
    }

}