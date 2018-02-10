package structure;

import java.util.function.LongBinaryOperator;

/**
 * Created by lukashuwald on 09.02.18.
 */

// this class implements a binary indexed tree(fenwick tree) for prefix fold computations
public class FenwickTree {

    LongBinaryOperator operation = (l1, l2) -> l1 + l2;
    long identity = 0;
    long[] array;

    // create fenwick tree for prefix sums with size many elements
    public FenwickTree(int size) {
        this.array = new long[size];
    }

    // create fenwick tree for prefix folds with size many elements and with given fold operation and identity
    public FenwickTree(int size, LongBinaryOperator operation, long identity) {
        this(size);
        this.operation = operation;
        this.identity = identity;
        for (int i = 0; i < size; ++i) {
            this.array[i] = identity;
        }
    }

    // returns index of least significant bit
    private final int lsb(int i) {
        return (i & (-i));
    }

    // perform a prefix fold of indices 0..i-1
    public final long prefixFold(int i) {
        long res = identity;
        while (i > 0) {
            res = operation.applyAsLong(res, this.array[i-1]);
            i -= this.lsb(i);
        }
        return res;
    }

    // update the i-th value by merging it with l
    public void updateValue(int i, long l) {
        while (i < this.array.length) {
            this.array[i] = operation.applyAsLong(this.array[i], l);
            i += this.lsb(i+1);
        }
    }

}
