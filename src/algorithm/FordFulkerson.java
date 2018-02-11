package algorithm;

import data.Tuple;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class FordFulkerson {

    private int[][] capacities;
    private int[][] flow;

    // initialize Ford Fulkerson with array of capacities
    // the first index (0) is the source of the network, and the last index is the sink
    public FordFulkerson(int[][] capacities) {
        this.capacities = capacities;
        int n = capacities.length;
        this.flow = new int[n][n];
    }

   public Tuple<Integer, int[][]> maxFlow() {
        LinkedList<Integer> path = dfs();
        if (path == null) {
            int increase = this.getIncrease(path);
            this.increasePath(path, increase);
        }
        int maxFlow = 0;
        for (int i = 0; i < capacities.length; ++i) {
            maxFlow += flow[0][i];
        }
        return new Tuple(maxFlow, flow);
   }

    private LinkedList<Integer> dfs() {
        int source = 0;
        int sink = this.capacities.length - 1;
        Stack<Integer> stack = new Stack<>();
        LinkedList<Integer> path = new LinkedList<>();
        stack.push(source);
        path.push(source);
        while (! stack.isEmpty()) {
            int top = stack.pop();
            path.push(top);
            if (this.capacities[top][sink] - this.flow[top][sink] > 0) {
                path.push(sink);
                return path;
            }
            else {
                boolean found = false;
                for (int i = 0; i < sink; ++i) {
                    if (this.capacities[top][i] - this.flow[top][i] > 0) {
                        stack.push(i);
                        found = true;
                    }
                }
                if (!found) {
                    path.pop();
                }
            }
        }
        return null;
    }

    private void increasePath(LinkedList<Integer> path, int increase) {
        int current = path.peekFirst();
        Iterator<Integer> it = path.iterator();
        it.next();
        while (it.hasNext()) {
            int next = it.next();
            this.flow[current][next] += increase;
            this.flow[next][current] -= increase;
        }
    }

    private int getIncrease(LinkedList<Integer> path) {
        int current = path.peekFirst();
        int increase = Integer.MAX_VALUE;
        Iterator<Integer> it = path.iterator();
        it.next();
        while (it.hasNext()) {
            int next = it.next();
            int residual = this.capacities[current][next] - this.flow[current][next];
            increase = Math.min(increase, residual);
            current = next;
        }
        return increase;
    }
}
