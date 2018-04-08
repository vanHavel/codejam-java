package algorithm;

import data.Edge;
import data.WeightedEdge;
import structure.UnionFind;

import java.util.*;

// thi class handles computing minimum spanning trees in undirected graphs
public class MinimumSpanningTree {

    private final PriorityQueue<WeightedEdge<Integer>> edges;
    private final int size;

    private Set<WeightedEdge<Integer>> mstEdges;
    private int cost;

    // initialize with weights array, use -1 for non-existing edges
    public MinimumSpanningTree(int[][] weights) {
        int n = weights.length;
        this.size = n;
        this.edges = new PriorityQueue<>(n*n,
                Comparator.comparing(e -> e.weight));
        for (int i = 0; i < n; ++i) {
            for (int j = i+1; j < n; ++j) {
                if (weights[i][j] != -1) {
                    this.edges.add(new WeightedEdge<Integer>(i,j,weights[i][j]));
                }
            }
        }
    }

    // initialize with number of vertices and a collection of weighted edges
    public MinimumSpanningTree(int n, Collection<WeightedEdge<Integer>> edges) {
        this.edges = new PriorityQueue<>(n*n,
                Comparator.comparing(e -> e.weight));
        this.edges.addAll(edges);
        this.size = n;
    }

    // compute a MST using Kruskal's algorithm
    public void computeMST() {
        this.mstEdges = new HashSet<>();
        UnionFind uf = new UnionFind(this.size);

        while (this.mstEdges.size() < this.size - 1) {
            WeightedEdge<Integer> top = edges.poll();
            if (uf.find(top.origin) != uf.find(top.target)) {
                uf.union(top.origin, top.target);
                this.cost += top.weight;
                this.mstEdges.add(top);
            }
        }
    }

    // get edges of mst
    public Set<WeightedEdge<Integer>> getEdges() {
       return this.mstEdges;
    }

    // get cost of mst
    public int getCost() {
        return this.cost;
    }
}
