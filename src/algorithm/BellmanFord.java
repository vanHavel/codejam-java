package algorithm;

import data.WeightedEdge;
import utility.GraphUtility;

import java.util.Collection;
import java.util.Vector;

// this class implements the bellmann ford algorithm for computing shortest paths
// with possibly negative edge weights. Negative cycles can be detected.
public class BellmanFord {

    // the graph's adjacency list
    private Vector<? extends Collection<WeightedEdge<Long>>> adjacencyList;

    // result fields
    private long[] distances;
    private int[] predecessors;
    private boolean negativeCycle = false;

    // initialize with adjacency list
    public BellmanFord(Vector<? extends Collection<WeightedEdge<Long>>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    // initialize with adjacency matrix
    public BellmanFord(long[][] weights) {
        this(GraphUtility.longWeightMatrixToAdjacencyList(weights));
    }

    // compute all shortest paths from the source in O(VE) and predecessors for each node
    public void computeShortestPaths(int source) {
        final long VERY_LARGE = 1 << 60;
        int n = this.adjacencyList.size();
        this.distances = new long[n];
        this.predecessors = new int[n];
        for (int i = 0; i < n; ++i) {
            this.distances[i] = VERY_LARGE;
            this.predecessors[i] = -1;
        }
        this.distances[source] = 0;
        this.predecessors[source] = source;

        // compute shortest paths
        for (int iteration = 0; iteration < n-1; ++iteration) {
            for (int node = 0; node < n; ++node) {
                for (WeightedEdge<Long> edge : this.adjacencyList.get(node)) {
                    // relax edge
                    if (this.distances[node] + edge.weight < this.distances[edge.target]) {
                        this.distances[edge.target] = this.distances[node] + edge.weight;
                        this.predecessors[edge.target] = node;
                    }
                }
            }
        }

        // check for negative cycles
        for (int node = 0; node < n; ++node) {
            for (WeightedEdge<Long> edge : this.adjacencyList.get(node)) {
                if (this.distances[node] + edge.weight < this.distances[edge.target]) {
                    this.negativeCycle = true;
                }
            }
        }
    }

    // check for negative cycle
    public boolean hasNegativeCycle() {
        return this.negativeCycle;
    }

    // get distances from the source
    public long[] getDistances() {
        return this.distances;
    }

    // get predecessors on shortest path aborescence
    public int[] getPredecessors() {
        return this.predecessors;
    }
}
