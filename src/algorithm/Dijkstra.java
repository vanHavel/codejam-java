package algorithm;

import data.WeightedEdge;
import structure.MinHeap;
import utility.GraphUtility;

import java.util.*;

// this class implements Dijkstra's shortest path algorithm on simple, directed graphs
public class Dijkstra {

    private final List<? extends Collection<WeightedEdge<Long>>> adjacencyList;
    private long[] distances;
    private int[] predecessors;

    // initialize with adjacency list of weighted edge. Distances must be non-negative
    public Dijkstra(List<? extends Collection<WeightedEdge<Long>>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        this.distances = new long[adjacencyList.size()];
        this.predecessors = new int[adjacencyList.size()];
    }

    // initialize with weight matrix
    public Dijkstra(long[][] weights) {
        this(GraphUtility.longWeightMatrixToAdjacencyList(weights));
    }

    // compute all shortest path from a given source in O(E log V)
    public void computeShortestPaths(int source) {
        // initialize distances and predecessors
        for (int i = 0; i < this.distances.length; ++i) {
            this.distances[i] = -1;
            this.predecessors[i] = -1;
        }
        this.distances[source] = 0;
        this.predecessors[source] = source;
        // initialize heap
        MinHeap<Integer> heap = new MinHeap<>(adjacencyList.size());
        for (WeightedEdge<Long> e : adjacencyList.get(source)) {
            heap.insert(e.target, e.weight);
            this.predecessors[e.target] = source;
        }
        while (!heap.isEmpty()) {
            // we have found the shortest path to the head of the queue
            long distance = heap.minimumKey();
            int node = heap.poll();
            this.distances[node] = distance;
            for (WeightedEdge<Long> e : adjacencyList.get(node)) {
                // only process nodes that are not yet finished
                if (this.distances[e.target] == -1) {
                    // discovering node for the first time
                    if (!heap.contains(e.target)) {
                        heap.insert(e.target, this.distances[node] + e.weight);
                        this.predecessors[e.target] = node;
                    }
                    // updating existing node
                    else if (heap.getKey(e.target) > this.distances[node] + e.weight) {
                        heap.decreaseKey(e.target, this.distances[node] + e.weight);
                        this.predecessors[e.target] = node;
                    }
                }
            }
        }
    }

    // get distances from the source to all nodes
    // if the node is not reachable, the distance is -1
    public long[] getDistances() {
        return this.distances;
    }

    // get predecessors on the shortest paths from the source to a node
    // the source has itself as predecessor
    // unreachable nodes have predecessors -1
    public int[] getPredecessors() {
        return this.predecessors;
    }

    // get the shortest path from the source to the (reachable) target
    // throws exception when target is not reachable
    public List<WeightedEdge<Long>> getShortestPath(int target) {
        if (this.predecessors[target] == -1) {
            throw new IllegalArgumentException("Target " + target + " is not reachable.");
        }
        else {
            LinkedList<WeightedEdge<Long>> path = new LinkedList<>();
            while (this.predecessors[target] != target) {
                int predecessor = this.predecessors[target];
                int goal = target;
                WeightedEdge<Long> e = this.adjacencyList.get(predecessor).stream()
                        .filter(f -> f.target == goal)
                        .findFirst()
                        .get();
                path.addFirst(e);
                target = this.predecessors[target];
            }
            return path;
        }
    }

}
