package algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        stronglyConnectedComponents.computeStronglyConnectedComponents();
        assertEquals(2, stronglyConnectedComponents.getNumberOfStronglyConnectedComponents());

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
    }

}