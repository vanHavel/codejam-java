package structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FenwickTreeTest {

    @Test
    public void testPrefixSumFenwickTree() {
        FenwickTree fenwickTree = new FenwickTree(10);
        for (int i = 0; i < 10; ++i) {
            fenwickTree.updateValue(i, i+1);
        }
        assertEquals(55, fenwickTree.prefixFold(10));
        assertEquals(0, fenwickTree.prefixFold(0));
        assertEquals(6, fenwickTree.prefixFold(3));
        assertEquals(21, fenwickTree.prefixFold(6));

        fenwickTree.updateValue(2, 20);
        assertEquals(30, fenwickTree.prefixFold(4));
    }

    @Test
    public void testPrefixMaxFenwickTree() {
        FenwickTree fenwickTree = new FenwickTree(10, Math::max, Long.MIN_VALUE);
        for (int i = 0; i < 10; ++i) {
            fenwickTree.updateValue(i, i+1);
        }
        assertEquals(Long.MIN_VALUE, fenwickTree.prefixFold(0));
        assertEquals(10, fenwickTree.prefixFold(10));
        assertEquals(4, fenwickTree.prefixFold(4));
    }
}