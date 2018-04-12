package algorithm;

import data.Pair;
import data.WeightedEdge;
import structure.MinHeap;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

// this class implements Dijkstra's shortest path algorithm
public class Dijkstra {

    private final Vector<List<WeightedEdge<Long>>> adjacencyLists;
    private long[] distances;
    private int[] predecessors;

    // initialize with adjacency list of weighted edge. Distances must be nonnegative.
    public Dijkstra(Vector<List<WeightedEdge<Long>>> adjacencyLists) {
        this.adjacencyLists = adjacencyLists;
    }

    // compute all shortest path from a given source
    public void computeShortestPaths(int source) {
        // initialize distances and predecessors
        for (int i = 0; i < this.distances.length; ++i) {
            this.distances[i] = -1;
            this.predecessors[i] = -1;
        }
        this.distances[source] = 0;
        // initialize heap
        MinHeap<Integer> heap = new MinHeap<>(adjacencyLists.size());
        for (WeightedEdge<Long> e : adjacencyLists.get(source)) {
            heap.insert(e.target, e.weight);
        }
        while (!heap.isEmpty()) {
            // we have found the shortest path to the head of the queue
            long distance = heap.minimumKey();
            int node = heap.poll();
            this.distances[node] = distance;
            for (WeightedEdge<Long> e : adjacencyLists.get(node)) {
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
                WeightedEdge<Long> e = this.adjacencyLists.get(predecessor).stream()
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
