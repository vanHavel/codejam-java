package algorithm;

import data.Edge;
import data.Tuple;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

// this class computes maximum bipartite matchings using augmenting paths
public class BipartiteMatching {

    private Vector<List<Edge>> adjacencyList;

    // construct with adjacency matrix
    public BipartiteMatching(boolean[][] edges) {

    }
    // construct with adjacency list
    public BipartiteMatching(Vector<List<Edge>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    // the complexity is O(VE)
    public void computeMaximumMathcing() {

    }
}
