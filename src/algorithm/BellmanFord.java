package algorithm;

import data.WeightedEdge;
import utility.GraphUtility;

import java.util.List;
import java.util.Vector;

// this class implements the bellmann ford algorithm for computing shortest paths
// with possibly negative edge weights. Negative cycles can be detected.
public class BellmanFord {

    // the graph's adjacency list
    private Vector<List<WeightedEdge<Long>>> adjacencyList;

    // result fields
    private long[] distances;
    private boolean negativeCycle = false;

    // initialize with adjacency list
    public BellmanFord(Vector<List<WeightedEdge<Long>>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    // initialize with adjacency matrix
    public BellmanFord(long[][] weights) {
        this(GraphUtility.longWeightMatrixToAdjacencyList(weights));
    }

    // compute all shortest paths from the source in O(E)
    public void computeShortestPaths(int source) {
        final long VERY_LARGE = 1 << 60;
        int n = this.adjacencyList.size();
        this.distances = new long[n];
        for (int i = 0; i < n; ++i) {
            this.distances[i] = VERY_LARGE;
        }
        this.distances[source] = 0;

        // compute shortest paths
        for (int iteration = 0; iteration < n-1; ++iteration) {
            for (int node = 0; node < n; ++node) {
                for (WeightedEdge<Long> edge : this.adjacencyList.get(node)) {
                    this.distances[edge.target]
                            = Math.min(this.distances[edge.target], this.distances[node] + edge.weight);
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
}
