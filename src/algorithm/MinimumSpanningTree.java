package algorithm;

import data.Edge;
import structure.UnionFind;

import java.util.*;

// thi class handles computing minimum spanning trees in undirected graphs
public class MinimumSpanningTree {

    private int[][] weights;
    private Set<Edge> mstEdges;
    private int cost;

    // initialize with weights array, use -1 for non-existing edges
    public MinimumSpanningTree(int[][] weights) {
        this.weights = weights;
    }

    // compute a MST using Kruskal's algorithm
    public void computeMST() {
        int n = this.weights.length;
        Queue<Edge> edges = new PriorityQueue<>(n*n,
                Comparator.comparing(e -> this.weights[e.origin][e.target]));

        for (int i = 0; i < n; ++i) {
            for (int j = i+1; j < n; ++j) {
                if (this.weights[i][j] != -1) {
                    edges.add(new Edge(i,j));
                }
            }
        }

        this.mstEdges = new HashSet<>();
        UnionFind uf = new UnionFind(n);
        
        while (this.mstEdges.size() < n - 1) {
            Edge top = edges.poll();
            if (uf.find(top.origin) != uf.find(top.target)) {
                uf.union(top.origin, top.target);
                this.cost += this.weights[top.origin][top.target];
                this.mstEdges.add(top);
            }
        }
    }

    // get edges of mst
    public Set<Edge> getEdges() {
       return this.mstEdges;
    }

    // get cost of mst
    public int getCost() {
        return this.cost;
    }
}
