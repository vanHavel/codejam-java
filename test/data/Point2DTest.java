package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point2DTest {

    @Test
    public void testDistance() {
        Point2D p1 = new Point2D(3,5);
        Point2D p2 = new Point2D(4, 6);

        assertEquals(Math.sqrt(2), p1.distanceTo(p2), 0.01);
        assertEquals(Math.sqrt(2), Point2D.distance(p1, p2), 0.01);
        assertEquals(2, Point2D.manhattanDistance(p1, p2));
    }

    @Test
    public void testOperations() {
        Point2D p1 = new Point2D(3,5);
        Point2D p2 = new Point2D(4, 6);

        assertEquals(new Point2D(7,11), Point2D.sum(p1, p2));
        assertEquals(new Point2D(-1, -1), Point2D.difference(p1, p2));
        assertEquals(new Point2D(4.5, 7.5), Point2D.scale(p1, 1.5));
    }

    @Test
    public void testInsideCircle() {
        Point2D p1 = new Point2D(3,5);
        Point2D p2 = new Point2D(4, 6);
        double eps = 1e-9;

        assertFalse(Point2D.insideCircle(p1, 1, p2));
        assertFalse(Point2D.insideCircle(p1, Math.sqrt(2) - eps, p2));
        assertTrue(Point2D.insideCircle(p1, 2, p2));
        assertTrue(Point2D.insideCircle(p1, Math.sqrt(2) + eps, p2));
    }

}