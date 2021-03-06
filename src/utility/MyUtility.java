package utility;

import java.util.AbstractList;
import java.util.List;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Predicate;

public class MyUtility {

    // turn string into list of characters
    public static List<Character> stringAsList(String s) {
        return new AbstractList<Character>() {
            @Override
            public Character get(int index) {
                return s.charAt(index);
            }

            @Override
            public int size() {
                return s.length();
            }
        };
    }



    // performs ternary search to obtain maximum of unimodal function -- long version
    public static <A extends Comparable<A>> long ternarySearch(LongFunction<A> f, long lo, long hi) {
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (f.apply(mid).compareTo(f.apply(mid + 1)) < 0) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // performs ternary search to obtain maximum of unimodal function -- int version
    public static <A extends Comparable<A>> int ternarySearch(IntFunction<A> f, int lo, int hi) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (f.apply(mid).compareTo(f.apply(mid + 1)) < 0) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // return first index that satisfies test
    public static long binarySearchFirst(long lo, long hi, Predicate<Long> test) {
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (test.test(mid)) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }
        return lo;
    }

    // return last index that satisfies test
    public static long binarySearchLast(long lo, long hi, Predicate<Long> test) {
        while (lo < hi) {
            long mid = hi - (hi - lo) / 2;
            if (test.test(mid)) {
                lo = mid;
            }
            else {
                hi = mid-1;
            }
        }
        return lo;
    }

    // sample from a given probability distribution
    public static int sampleFromProbabilities(double[] probs) {
        Random rand = new Random();
        double sample = rand.nextDouble();
        int index = 0;
        while (index < probs.length && sample > probs[index]) {
            sample -= probs[index];
            index++;
        }
        // to account for possibly inprecise double arithmetic
        return Math.min(index, probs.length - 1);
    }

    // raise integer to a power using square and multiply
    public static long integerPower(long base, int exponent) {
        switch (exponent) {
            case 0:
                return 1;
            case 1:
                return base;
            case 2:
                return base * base;
            default:
                int div = exponent / 2;
                int mod = exponent % 2;
                long rec = integerPower(base, div);
                long res = rec * rec;
                if (mod == 1) {
                    res *= base;
                }
                return res;
        }
    }

    // permute an int array randomly using fisher-yates shuffling
    public static void randomPermutation(int[] a) {
        int n = a.length;
        Random r  = new Random();
        for (int i = 0; i < n; ++i) {
            int offset = r.nextInt(n-i);
            int tmp = a[i];
            a[i] = a[i + offset];
            a[i + offset] = tmp;
        }
    }

    // permute a long array randomly using fisher-yates shuffling
    public static void randomPermutation(long[] a) {
        int n = a.length;
        Random r  = new Random();
        for (int i = 0; i < n; ++i) {
            int offset = r.nextInt(n-i);
            long tmp = a[i];
            a[i] = a[i + offset];
            a[i + offset] = tmp;
        }
    }

}


