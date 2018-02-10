package data;

import java.util.Objects;

/**
 * Created by lukashuwald on 09.02.18.
 */

// generic tuple data class
public class Tuple<A,B> {

    public A fst;
    public B snd;

    public Tuple(A a, B b) {
        this.fst = a;
        this.snd = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(fst, tuple.fst) &&
                Objects.equals(snd, tuple.snd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fst, snd);
    }
}
