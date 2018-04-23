package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitUtilityTest {

    @Test
    public void testIntegerBitOperations() {
        int i = 0b101;
        assertTrue(BitUtility.isSet(i, 0));
        assertTrue(BitUtility.isSet(i, 2));
        assertFalse(BitUtility.isSet(i, 1));
        assertFalse(BitUtility.isSet(i, 3));
        assertEquals(2, BitUtility.popCount(i));

        // test setting bits
        i = BitUtility.setBit(i, 7);
        i = BitUtility.setBit(i, 31);
        assertTrue(BitUtility.isSet(i, 7));
        assertTrue(BitUtility.isSet(i, 31));
        assertEquals(4, BitUtility.popCount(i));

        // test clearing bits
        i = BitUtility.clearBit(i, 2);
        i = BitUtility.clearBit(i, 31);
        assertFalse(BitUtility.isSet(i, 2));
        assertFalse(BitUtility.isSet(i, 31));
        assertEquals(2, BitUtility.popCount(i));

        // test operations that do not change bit patterns
        int j = BitUtility.setBit(i, 0);
        j = BitUtility.clearBit(j, 1);
        assertTrue(i == j);
    }

    @Test
    public void testLongBitOperations() {
        long l = 0b101L;
        assertTrue(BitUtility.isSet(l, 0));
        assertTrue(BitUtility.isSet(l, 2));
        assertFalse(BitUtility.isSet(l, 32));
        assertFalse(BitUtility.isSet(l, 3));
        assertEquals(2, BitUtility.popCount(l));

        // test setting bits
        l = BitUtility.setBit(l, 7);
        l = BitUtility.setBit(l, 63);
        assertTrue(BitUtility.isSet(l, 7));
        assertTrue(BitUtility.isSet(l, 63));
        assertEquals(4, BitUtility.popCount(l));

        // test clearing bits
        l = BitUtility.clearBit(l, 2);
        l = BitUtility.clearBit(l, 63);
        assertFalse(BitUtility.isSet(l, 2));
        assertFalse(BitUtility.isSet(l, 63));
        assertEquals(2, BitUtility.popCount(l));

        // test operations that do not change bit patterns
        long m = BitUtility.setBit(l, 0);
        m = BitUtility.clearBit(m, 1);
        assertTrue(m == l);
    }

}