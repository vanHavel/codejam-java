package structure;

// this class implements the segment tree data structure for efficient dynamic range minimum queries
public class SegmentTree {

    // the original array
    private long[] array;

    // the segment tree
    private int[] segmentTree;

    // construct min segment tree with array
    public SegmentTree(long[] array) {
        this.array = array;
        int n = array.length;
        this.segmentTree = new int[4*n];
        this.buildSegmentTree(1, 0, n-1);
    }

    // build the original segment tree from the array
    private void buildSegmentTree(int index, int leftBound, int rightBound) {
        // singleton segment -> index is unique
        if (leftBound == rightBound) {
            this.segmentTree[index] = leftBound;
        }
        // build tree recursively
        else {
            int middle = (leftBound + rightBound) / 2;
            this.buildSegmentTree(2 * index, leftBound, middle);
            this.buildSegmentTree(2 * index + 1, middle + 1, rightBound);
            int leftMinIndex = this.segmentTree[2 * index];
            int rightMinIndex = this.segmentTree[2 * index + 1];
            this.segmentTree[index] = (this.array[leftMinIndex] < this.array[rightMinIndex] ?
                    leftMinIndex : rightMinIndex);
        }
    }

    // perform rangeMinimumQuery(i, j) starting at segment index with given bounds
    public int rangeMinimumQuery(int i, int j) {
        int n = this.array.length;
        return this.rangeMinimumQuery(1, 0, n-1, i, j);
    }

    // recursive helper function for queries
    private int rangeMinimumQuery(int index, int leftBound, int rightBound, int i, int j) {
        // query outside segment
        if (i > rightBound || j < leftBound) { return -1; }
        // query inside segment
        if (leftBound >= i && rightBound <= j) { return this.segmentTree[index]; }

        // otherwise divide
        int middle = (leftBound + rightBound) / 2;
        int leftMinIndex = this.rangeMinimumQuery(2 * index, leftBound, middle, i, j);
        int rightMinIndex = this.rangeMinimumQuery(2 * index + 1, middle + 1, rightBound, i, j);

        // and conquer
        if (leftMinIndex == -1) {
            return rightMinIndex;
        }
        else if (rightMinIndex == -1) {
            return leftMinIndex;
        }
        else {
            return (this.array[leftMinIndex] < this.array[rightMinIndex] ? leftMinIndex : rightMinIndex);
        }
    }

    // update array index i to given value
    public void update(int i, long value) {
        int n = this.array.length;
        this.array[i] = value;
        this.update(1, 0, n-1, i);
    }

    // recursive helper function for update
    private void update(int index, int leftBound, int rightBound, int i) {
        if (leftBound == rightBound) { return; }
        int middle = (leftBound + rightBound) / 2;
        int left = 2 * index;
        int right = 2 * index + 1;

        if (i <= middle) {
            update(left, leftBound, middle, i);
        }
        else {
            update(right, middle + 1, rightBound, i);
        }
        int leftMinIndex = this.segmentTree[2 * index];
        int rightMinIndex = this.segmentTree[2 * index + 1];
        this.segmentTree[index] = (this.array[leftMinIndex] < this.array[rightMinIndex] ?
                leftMinIndex : rightMinIndex);
    }


}
