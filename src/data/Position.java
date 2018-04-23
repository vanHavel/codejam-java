package data;

import java.util.Objects;

// this class represents a 2D integer position in Space
public class Position {

    public final int x;
    public final int y;


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // manhattan distance
    public int manhattanDistanceTo(Position other) {
        return Position.manhattanDistance(this, other);
    }
    public static int manhattanDistance(Position p1, Position p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
