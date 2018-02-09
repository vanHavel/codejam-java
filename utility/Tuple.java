package utility;

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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        else if (! (o instanceof Tuple<?,?>)) {
            return false;
        }
        else {
            Tuple<?,?> other = (Tuple <?,?>) o;
            return (this.fst.equals(other.fst) && this.snd.equals(other.snd));
        }
    }

    public int hashCode() {
        return 17 * this.fst.hashCode() + this.snd.hashCode();
    }
}
