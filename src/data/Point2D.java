package data;

import java.util.Objects;

// this data class represents a point with real coordinates in 2D space
public class Point2D {

    public double x;
    public double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /// euclidian distance
    public static double distance(Point2D p1, Point2D p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    // manhattan distance
    public static double manhattanDistance(Point2D p1, Point2D p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    // scale a point by a constant
    public static Point2D scale(Point2D point, double factor) {
        return new Point2D(point.x * factor, point.y * factor);
    }

    // add two points together
    public static Point2D sum(Point2D p1, Point2D p2) {
        return new Point2D(p1.x + p2.x, p1.y + p2.y);
    }

    // compute the point / vector p1 - p2
    public static Point2D difference(Point2D p1, Point2D p2) {
        return new Point2D(p1.x - p2.x, p1.y - p2.y);
    }

    // compute whether point is in the circle with given center and radius
    public static boolean insideCircle(Point2D center, double radius, Point2D point) {
        return point.distanceTo(center) <= radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Double.compare(point2D.x, x) == 0 &&
                Double.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    // member function for euclidian distance
    public double distanceTo(Point2D other) {
        return Point2D.distance(this, other);
    }


}
