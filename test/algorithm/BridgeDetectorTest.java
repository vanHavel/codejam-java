package algorithm;

import data.Edge;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BridgeDetectorTest {

    @Test
    public void testBridgeDetection() {
        boolean[][] edges = new boolean[5][];
        edges[0] = new boolean[] {false, true, false, false, false};
        edges[1] = new boolean[] {true, false, true, false, false};
        edges[2] = new boolean[] {false, true, false, true, true};
        edges[3] = new boolean[] {false, false, true, false, true};
        edges[4] = new boolean[] {false, false, true, true, false};

        BridgeDetector bridgeDetector = new BridgeDetector(edges);
        bridgeDetector.computeBridges();

        // test bridges
        Set<Edge> bridges = bridgeDetector.getBridges();
        assertEquals(2, bridges.size());
        assertTrue(bridges.contains(new Edge(0,1))
                || bridges.contains(new Edge(1, 0)));
        assertTrue(bridges.contains(new Edge(2,1))
                || bridges.contains(new Edge(1, 2)));

        // test articulation points
        Set<Integer> points = bridgeDetector.getArticulationPoints();
        assertEquals(2, points.size());
        assertTrue(points.contains(1));
        assertTrue(points.contains(2));
    }

}