package algorithm;

import data.Edge;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StronglyConnectedComponentsTest {

    @Test
    public void testStronglyConnectedComponents() {
        boolean[][] edges = new boolean[5][];
        edges[0] = new boolean[] {false, true, true, false, false};
        edges[1] = new boolean[] {true, false, false, true, true};
        edges[2] = new boolean[] {false, false, false, true, false};
        edges[3] = new boolean[] {false, false, false, false, true};
        edges[4] = new boolean[] {false, false, true, false, false};

        StronglyConnectedComponents stronglyConnectedComponents = new StronglyConnectedComponents(edges);

        // test size
        stronglyConnectedComponents.computeStronglyConnectedComponents();
        assertEquals(2, stronglyConnectedComponents.getNumberOfStronglyConnectedComponents());

        // test components
        List<Set<Integer>> sccs = stronglyConnectedComponents.getStronglyConnectedComponents();
        Set<Integer> scc1 = new HashSet<>();
        Set<Integer> scc2 = new HashSet<>();
        scc1.add(0);
        scc1.add(1);
        scc2.add(2);
        scc2.add(3);
        scc2.add(4);
        assertTrue(sccs.contains(scc1));
        assertTrue(sccs.contains(scc2));

        // test component indices
        int[] indices = stronglyConnectedComponents.getComponentIndices();
        assertFalse(indices[0] == indices[2]);
        assertTrue(indices[3] == indices[4]);
        assertTrue(indices[0] == indices[1]);

        // test dag
        List<List<Edge>> dag = stronglyConnectedComponents.getComponentDAG();
        assertEquals(2, dag.size());
        assertTrue(dag.contains(new ArrayList<>()));
    }

}