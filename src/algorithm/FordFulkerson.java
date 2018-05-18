package algorithm;

import data.Tuple;

import java.util.*;

// this class computes mac flow using ford fulkerson method
// the implementation uses edmond karp and runs in O(V^3E) because of the adjacency matrix
public class FordFulkerson {

    // capacity array
    private int[][] capacities;

    // result fields
    private int[][] flow;
    private int maxFlow;

    // initialize Ford Fulkerson with array of capacities
    // the first index (0) is the source of the network, and the last index is the sink
    public FordFulkerson(int[][] capacities) {
        this.capacities = capacities;
        int n = capacities.length;
        this.flow = new int[n][n];
    }

    // calculate maxFlow value and capacities
    public void computeMaxFlow() {
        int[] parents = bfs();
        int sink = this.capacities.length - 1;
        while (parents[sink] != -1) {
            int increase = this.getIncrease(parents);
            this.increasePath(parents, increase);
            parents = bfs();
        }
        this.maxFlow = 0;
        for (int i = 0; i < capacities.length; ++i) {
            this.maxFlow += this.flow[0][i];
        }
    }

    // perform bfs from source to sink in residual network, return parents for each discovered node
    private int[] bfs() {
        int source = 0;
        int sink = this.capacities.length - 1;
        Queue<Integer> queue = new LinkedList<>();
        int[] parents = new int[this.capacities.length];
        Arrays.fill(parents, -1);
        parents[source] = 0;
        queue.add(source);
        while (!queue.isEmpty()) {
            int top = queue.poll();
            if (this.capacities[top][sink] - this.flow[top][sink] > 0) {
                parents[sink] = top;
                return parents;
            } else {
                for (int i = 0; i < sink; ++i) {
                    if (this.capacities[top][i] - this.flow[top][i] > 0 && parents[i] == -1) {
                        queue.add(i);
                        parents[i] = top;
                    }
                }
            }
        }
        return parents;
    }

    private void increasePath(int[] parents, int increase) {
        int sink = this.capacities.length - 1;
        int current = sink;
        while (parents[current] != current) {
            int previous = parents[current];
            this.flow[previous][current] += increase;
            this.flow[current][previous] -= increase;
            current = previous;
        }
    }

    private int getIncrease(int[] parents) {
        int increase = Integer.MAX_VALUE;
        int sink = this.capacities.length - 1;
        int current = sink;
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
