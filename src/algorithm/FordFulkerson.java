package algorithm;

import java.util.*;

// this class computes mac flow using ford fulkerson method
// the implementation uses edmond karp and runs in O(V^3E) because of the adjacency matrix
public class FordFulkerson {

    // capacity array
    private int[][] capacities;
    // source and sink
    private int source;
    private int sink;

    // result fields
    private int[][] flow;
    private int maxFlow;

    // initialize Ford Fulkerson with array of capacities
    // the first index (0) is the source of the network, and the last index is the sink
    public FordFulkerson(int[][] capacities) {
        this(capacities, 0, capacities.length-1);
    }
    // initialize ford fulkerson with capacities and source and sink
    public FordFulkerson(int[][] capacities, int source, int sink) {
        this.capacities = capacities;
        int n = capacities.length;
        this.flow = new int[n][n];
        this.source = source;
        this.sink = sink;
    }

    // calculate maxFlow value and capacities
    public void computeMaxFlow() {
        int[] parents = bfs();
        while (parents[this.sink] != -1) {
            int increase = this.getIncrease(parents);
            this.increasePath(parents, increase);
            parents = bfs();
        }
        this.maxFlow = 0;
        for (int i = 0; i < capacities.length; ++i) {
            this.maxFlow += this.flow[this.source][i];
        }
    }

    // perform bfs from source to sink in residual network, return parents for each discovered node
    private int[] bfs() {
        Queue<Integer> queue = new ArrayDeque<>(this.capacities.length);
        int[] parents = new int[this.capacities.length];
        Arrays.fill(parents, -1);
        parents[this.source] = 0;
        queue.add(this.source);
        while (!queue.isEmpty()) {
            int top = queue.poll();
            if (this.capacities[top][this.sink] - this.flow[top][this.sink] > 0) {
                parents[this.sink] = top;
                return parents;
            } else {
                for (int i = 0; i < this.capacities.length; ++i) {
                    if (this.capacities[top][i] - this.flow[top][i] > 0 && parents[i] == -1) {
                        queue.add(i);
                        parents[i] = top;
                    }
                }
            }
        }
        return parents;
    }

    // increase the flow along augmenting path
    private void increasePath(int[] parents, int increase) {
        int current = this.sink;
        while (parents[current] != current) {
            int previous = parents[current];
            this.flow[previous][current] += increase;
            this.flow[current][previous] -= increase;
            current = previous;
        }
    }

    // get maximum possible increase along augmenting path
    private int getIncrease(int[] parents) {
        int increase = Integer.MAX_VALUE;
        int current = this.sink;
        while (parents[current] != current) {
            int previous = parents[current];
            increase = Math.min(increase, this.capacities[previous][current] - this.flow[previous][current]);
            current = previous;
        }
        return increase;
    }


    // get max flow array
    public int[][] getMaxFlow() {
        return this.flow;
    }

    // get max flow value
    public int getMaxFlowValue() {
        return this.maxFlow;
    }
}
