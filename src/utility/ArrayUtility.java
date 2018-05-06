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

    // reverse an int array in place
    public static void reverse(int[] a) {
        int n = a.length;
        for (int i = 0; i < n / 2; ++i) {
            int tmp = a[i];
            a[i] = a[n-1-i];
            a[n-1-i] = tmp;
        }
    }

    // reverse a long array in place
    public static void reverse(long[] a) {
        int n = a.length;
        for (int i = 0; i < n / 2; ++i) {
            long tmp = a[i];
            a[i] = a[n-1-i];
            a[n-1-i] = tmp;
        }
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
