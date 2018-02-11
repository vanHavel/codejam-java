package algorithm;

import data.Edge;
import data.Tuple;

import java.util.HashSet;
import java.util.Set;

// this class computes maximum bipartite matchings using Ford Fulkerson
public class BipartiteMatching {

    private boolean[][] edges;

    public BipartiteMatching(boolean[][] edges) {
        this.edges = edges;
    }

    // the complexity is O((n+m)^3)
    public Tuple<Integer, Set<Edge>> maximumBipartiteMatching() {
        int n = this.edges.length;
        int m = this.edges[0].length;
        int size = n + m + 2;
        int[][] capacities = new int[size][size];
        // source edges
        for (int i = 1; i <= n; ++i) {
            capacities[0][i] = 1;
        }
        // sink edges
        for (int i = n + 1; i < size - 1; ++i) {
            capacities[i][size - 1] = 1;
        }
        // internal edges
        for (int i = 1; i < n; ++i) {
            for (int j = n + 1; j < size - 1; ++j) {
                capacities[i][j] = this.edges[i - 1][j - n - 1] ? 1 : 0;
            }
        }
        // compute maxFlow using Ford Fulkerson
        FordFulkerson fordFulkerson = new FordFulkerson(capacities);
        Tuple<Integer, int[][]> maxFlow = fordFulkerson.maxFlow();
        // recover matching
        Set<Edge> matching = new HashSet<>();
        for (int i = 1; i < n; ++i) {
            for (int j = n + 1; j < size - 1; ++j) {
                if (maxFlow.snd[i][j] == 1) {
                    matching.add(new Edge(i - 1, j - n - 1));
                }
            }
        }
        return new Tuple<>(maxFlow.fst, matching);
    }
}
