package data;

import java.util.Objects;
import java.util.function.Function;

public class Maybe<A> {

    private final A value;
    private final boolean present;

    public Maybe() {
        this.value = null;
        this.present = false;
    }

    public Maybe(A a) {
        this.present = true;
        this.value = a;
    }

    public boolean isPresent() {
        return this.present;
    }

    public A getValue() throws RuntimeException {
        if (this.isPresent()) {
            return this.value;
        }
        throw new RuntimeException("Tries to extract value from Nothing.");
    }

    public <B> Maybe<B> fmap(Function<A, B> f) {
        if (this.isPresent()) {
            return new Maybe(f.apply(this.value));
        }
        else {
            return new Maybe<B>();
        }
    }

    @Override
    public String toString() {
        if (this.isPresent()) {
            return "Just " + this.value.toString();
        }
        else {
            return "Nothing";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maybe<?> maybe = (Maybe<?>) o;
        return present == maybe.present &&
                Objects.equals(value, maybe.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, present);
    }
}
