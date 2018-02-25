package data;

import java.util.Objects;

// this class represents a 2D integer position in Space
public class Position {

    public int x;
    public int y;


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int manhattanDistance(Position other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
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

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }
}
