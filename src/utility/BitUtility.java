package utility;

public class BitUtility {

    // test whether given bit is set
    public static boolean isSet(int i, int bit) {
        return (i & (1 << bit)) != 0;
    }
    public static boolean isSet(long l, int bit) {
        return (l & (1 << bit)) != 0;
    }

    // set given bit
    public static int setBit(int i, int bit) {
        return (i | (1 << bit));
    }
    public static long setBit(long l, int bit) {
        return (l | (1 << bit));
    }

    // clear given bit
    public static int clearBit(int i, int bit) {
        return (i & ~(1 << bit));
    }
    public static long clearBit(long l, int bit) {
        return (l & ~(1 << bit));
    }

    // return number of set bits
    public static int popCount(int i) {
        int count = 0;
        for (int bit = 0; bit < 32; ++bit) {
            if (isSet(i, bit)) {
                count++;
            }
        }
        return count;
    }
    public static int popCount(long l) {
        int count = 0;
        for (int bit = 0; bit < 64; ++bit) {
            if (isSet(l, bit)) {
                count++;
            }
        }
        return count;
    }

}
