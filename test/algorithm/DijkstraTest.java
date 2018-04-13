package algorithm;

import data.WeightedEdge;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void testDijkstra() {
        Vector<List<WeightedEdge<Long>>> edges = new Vector<>();
        List<WeightedEdge<Long>> e1 = new LinkedList<>();
        List<WeightedEdge<Long>> e2 = new LinkedList<>();
        List<WeightedEdge<Long>> e3 = new LinkedList<>();
        e1.add(new WeightedEdge<>(0,1,5l));
        e1.add(new WeightedEdge<>(0,2,2l));
        e3.add(new WeightedEdge<>(2,0,3l));
        e3.add(new WeightedEdge<>(2,1,1l));
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);

        Dijkstra dijkstra = new Dijkstra(edges);
        dijkstra.computeShortestPaths(0);
        long[] distances = dijkstra.getDistances();
        int[] predecessors = dijkstra.getPredecessors();
        List<WeightedEdge<Long>> p1 = dijkstra.getShortestPath(1);
        List<WeightedEdge<Long>> p0 = dijkstra.getShortestPath(0);

        assertEquals(3, distances[1]);
        assertEquals(0, distances[0]);
        assertEquals(2, distances[2]);
        assertEquals(0, predecessors[0]);
        assertEquals(2, predecessors[1]);
        assertEquals(0, predecessors[2]);
        assertEquals(2, p1.size());
        assertEquals(new WeightedEdge<>(0,2,2l), p1.get(0));
        assertEquals(new WeightedEdge<>(2,1,1l), p1.get(1));
        assertTrue(p0.isEmpty());
    }

}