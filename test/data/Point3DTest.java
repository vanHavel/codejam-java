package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    public void testDistance() {
        Point3D p1 = new Point3D(1,2,3);
        Point3D p2 = new Point3D(1,5,-1);

        assertEquals(5, Point3D.distance(p1, p2));
        assertEquals(7, Point3D.manhattanDistance(p1, p2));
    }

}