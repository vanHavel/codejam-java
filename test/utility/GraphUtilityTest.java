package utility;

import data.Edge;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GraphUtilityTest {

    @Test
    public void testAdjacencyMatrixToAdjacencyList() {
        boolean[][] edges = new boolean[4][];
        edges[0] = new boolean[] {false, true, true, true};
        edges[1] = new boolean[] {false, false, false, false};
        edges[2] = new boolean[] {false, true, false, false};
        edges[3] = new boolean[] {false, true, true, false};
        Vector<List<Edge>> adjList = GraphUtility.adjacencyMatrixToAdjacencyList(edges);

        assertEquals(3, adjList.get(0).size());
        assertEquals(0, adjList.get(1).size());
        assertEquals(1, adjList.get(2).size());
        assertEquals(new Edge(2,1), adjList.get(2).get(0));
    }

}