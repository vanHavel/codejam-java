package algorithm;

import data.WeightedEdge;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class MinimumSpanningTreeTest {

    @Test
    public void testSingletonMST() {
        // test with weight matrix
        int[][] weights = new int[2][2];
        weights[0][1] = 5;

        MinimumSpanningTree mst = new MinimumSpanningTree(weights);
        mst.computeMST();
        assertEquals(5, mst.getCost());
        Set<WeightedEdge<Integer>> mstEdges = mst.getEdges();
        assertEquals(1, mstEdges.size());
        WeightedEdge<Integer> fst = mstEdges.iterator().next();
        assertEquals(new WeightedEdge<Integer>(0, 1, 5), fst);

        // test with edge collection
        Vector<WeightedEdge<Integer>> vec = new Vector<>(1);
        vec.add(new WeightedEdge<Integer>(0, 1, 5));

        mst = new MinimumSpanningTree(2, vec);
        mst.computeMST();
        assertEquals(5, mst.getCost());
        mstEdges = mst.getEdges();
        assertEquals(1, mstEdges.size());
        fst = mstEdges.iterator().next();
        assertEquals(new WeightedEdge<>(0, 1, 5), fst);
    }

    @Test
    public void testMST() {
        // test with weight matrix
        int[][] weights = new int[4][];
        weights[0] = new int[] {0,1,4,3};
        weights[1] = new int[] {1,0,-1,2};
        weights[2] = new int[] {4,-1,0,5};
        weights[3] = new int[] {3,2,5,0};

        MinimumSpanningTree mst = new MinimumSpanningTree(weights);
        mst.computeMST();
        assertEquals(7, mst.getCost());
        Set<WeightedEdge<Integer>> mstEdges = mst.getEdges();
        assertEquals(3, mstEdges.size());
        assertEquals(true, mstEdges.contains(new WeightedEdge<>(0,1,1)));

        // test with collection
        Vector<WeightedEdge<Integer>> vec = new Vector<>(5);
        vec.add(new WeightedEdge<Integer>(0, 1, 1));
        vec.add(new WeightedEdge<Integer>(0, 2, 4));
        vec.add(new WeightedEdge<Integer>(0, 3, 3));
        vec.add(new WeightedEdge<Integer>(1, 3, 2));
        vec.add(new WeightedEdge<Integer>(2, 3, 5));

        mst = new MinimumSpanningTree(4, vec);
        mst.computeMST();
        assertEquals(7, mst.getCost());
        mstEdges = mst.getEdges();
        assertEquals(3, mstEdges.size());
        assertEquals(true, mstEdges.contains(new WeightedEdge<>(0,1,1)));
    }

}