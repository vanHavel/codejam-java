package utility;

public class ArrayUtility {

    // returns minimum in long array
    public static long arrayMin(long[] a) {
        long best = Long.MAX_VALUE;
        for (int i = 0; i < a.length; ++i) {
            best = Math.min(a[i], best);
        }
        return best;
    }

    // returns maximum in long array
    public static long arrayMax(long[] a) {
        long best = Long.MIN_VALUE;
        for (int i = 0; i < a.length; ++i) {
            best = Math.max(a[i], best);
        }
        return best;
    }

    // returns sum of long array
    public static long sum(long[] a) {
        long res = 0;
        for (int i = 0; i < a.length; ++i) {
            res += a[i];
        }
        return res;
    }

    // returns sum of int array
    public static int sum(int[] a) {
        int res = 0;
        for (int i = 0; i < a.length; ++i) {
            res += a[i];
        }
        return res;
    }

}
