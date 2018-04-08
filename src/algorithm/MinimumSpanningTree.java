package algorithm;

import data.Edge;

import java.util.Set;

public class MinimumSpanningTree {

    private int[][] weights;
    private Set<Edge> edges;
    private int cost;

    // initialize with weights array, use -1 for non-existing edges
    public MinimumSpanningTree(int[][] weights) {
        this.weights = weights;
    }

    // compute a MST using Kruskal's algorithm
    public void computeMST() {
        //TODO
    }

    // get edges of mst
    public Set<Edge> getEdges() {
       return this.edges;
    }

    // get cost of mst
    public int getCost() {
        return this.cost;
    }
}
