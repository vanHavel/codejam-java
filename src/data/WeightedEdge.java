package data;

import java.util.Objects;

// this class represents a weighted edge of a graph
public class WeightedEdge<W> extends Edge {

    public final W weight;

    public WeightedEdge(int origin, int target, W weight) {
        super(origin, target);
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WeightedEdge<?> that = (WeightedEdge<?>) o;
        return Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight);
    }

    @Override
    public String toString() {
        return "WeightedEdge{" +
                "weight=" + weight +
                ", origin=" + origin +
                ", target=" + target +
                '}';
    }
}
