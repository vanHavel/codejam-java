package data;

import java.util.Objects;

public class Edge {

    public int origin;
    public int target;


    public Edge(int origin, int target) {
        this.origin = origin;
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return origin == edge.origin &&
                target == edge.target;
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, target);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "origin=" + origin +
                ", target=" + target +
                '}';
    }
}
