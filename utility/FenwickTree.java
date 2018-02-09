package utility;

import java.util.function.LongBinaryOperator;

/**
 * Created by lukashuwald on 09.02.18.
 */
public class FenwickTree {

    LongBinaryOperator operation = (l1, l2) -> l1 + l2;
    long identity = 0;
    long[] array;

    public FenwickTree(int size) {
        this.array = new long[size];
    }

    public FenwickTree(int size, LongBinaryOperator operation, long identity) {
        this(size);
        this.operation = operation;
        this.identity = identity;
        for (int i = 0; i < size; ++i) {
            this.array[i] = identity;
        }
    }

    private final int lsb(int i) {
        return (i & (-i));
    }

    public final long prefixFold(int i) {
        long res = identity;
        while (i > 0) {
            res = operation.applyAsLong(res, this.array[i-1]);
            i -= this.lsb(i);
        }
        return res;
    }

    public void updateValue(int i, long l) {
        while (i < this.array.length) {
            this.array[i] = operation.applyAsLong(this.array[i], l);
            i += this.lsb(i+1);
        }
    }

}
