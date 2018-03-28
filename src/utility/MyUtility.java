package utility;

import java.util.AbstractList;
import java.util.List;

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

    public static long arrayMin(long[] a) {
        long best = Long.MAX_VALUE;
        for (int i = 0; i < a.length; ++i) {
            best = Math.min(a[i], best);
        }
        return best;
    }

    public static long arrayMax(long[] a) {
        long best = Long.MIN_VALUE;
        for (int i = 0; i < a.length; ++i) {
            best = Math.max(a[i], best);
        }
        return best;
    }

}
