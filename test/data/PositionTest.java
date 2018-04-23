package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void testManhattanDistance() {
        Position p1 = new Position(3,5);
        Position p2 = new Position(4,7);

        assertEquals(3, p1.manhattanDistanceTo(p2));
        assertEquals(3, Position.manhattanDistance(p1, p2));
    }

}