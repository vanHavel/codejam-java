package utility;

import java.util.AbstractList;
import java.util.List;
import java.util.function.LongFunction;

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

    // performs ternary search to obtain maximum of unimodal function
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

}
