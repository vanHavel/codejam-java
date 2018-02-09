package utility;

// this data class represents a point in 2D space
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
}
