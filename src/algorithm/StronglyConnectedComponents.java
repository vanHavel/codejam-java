package algorithm;

import data.Edge;
import utility.GraphUtility;

import java.util.*;

// this class computes the strongly connected components of a given directed graph
public class StronglyConnectedComponents {

    // the graph's adjacency list
    private Vector<List<Edge>> adjacencyList;

    // fields used in the computation
    private int[] visitCounter;
    private int[] lowestNodeReachable;
    private boolean[] visited;
    private Stack<Integer> nodeStack;
    private int searchCounter = 0;
    private int[] componentCount;

    // result field
    private LinkedList<Set<Integer>> stronglyConnectedComponents = new LinkedList<>();

    // construct with adjacency list
    public StronglyConnectedComponents(Vector<List<Edge>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    // construct with adjacency matrix
    public StronglyConnectedComponents(boolean[][] edges) {
        this(GraphUtility.adjacencyMatrixToAdjacencyList(edges));
    }

    // compute SCCs using Tarjan's algorithm
    public void computeStronglyConnectedComponents() {
        int n = this.adjacencyList.size();
        this.visited = new boolean[n];
        this.visitCounter = new int[n];
        Arrays.fill(this.visitCounter, -1);
        this.lowestNodeReachable = new int[n];
        this.searchCounter = 0;
        this.nodeStack = new Stack<>();
        this.componentCount = new int[n];

        for (int i = 0; i < n; ++i) {
            if (visitCounter[i] == -1) {
                tarjan(i);
            }
        }
    }

    // recursive depth first search using tarjan's algorithm
    private void tarjan(int i) {
        visitCounter[i] = this.searchCounter;
        lowestNodeReachable[i] = this.searchCounter;
        this.searchCounter++;
        nodeStack.push(i);
        visited[i] = true;

        // depth first search and update lowest node reachable
        for (Edge edge : this.adjacencyList.get(i)) {
            int target = edge.target;
            if (visitCounter[target] == -1) {
                tarjan(target);
            }
            if (visited[target]) {
                lowestNodeReachable[i] = Math.min(lowestNodeReachable[i], lowestNodeReachable[target]);
            }
        }

        // triggered at root of SCC
        if (visitCounter[i] == lowestNodeReachable[i]) {
            int sccCount = this.stronglyConnectedComponents.size();
            this.stronglyConnectedComponents.add(new HashSet<>());
            int j;
            // pop the SCC
            do {
                j = nodeStack.pop();
                visited[j] = false;
                this.stronglyConnectedComponents.getLast().add(j);
                this.componentCount[j] = sccCount;
            } while (i != j);
        }

    }

    // get the number of SCCs
    public int getNumberOfStronglyConnectedComponents() {
        return this.stronglyConnectedComponents.size();
    }

    // get list of the SCCs
    public List<Set<Integer>> getStronglyConnectedComponents() {
        return this.stronglyConnectedComponents;
    }

    // obtain DAG of strongly connected components
    public Vector<List<Edge>> getComponentDAG() {
        // collect all edges between components in O(E)
        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < this.adjacencyList.size(); ++i) {
            for (Edge e : this.adjacencyList.get(i)) {
                if (this.componentCount[e.target] != this.componentCount[e.origin]) {
                    edges.add(new Edge(this.componentCount[e.origin], this.componentCount[e.target]));
                }
            }
        }

        // create adjacency list
        int c = this.stronglyConnectedComponents.size();
        Vector<List<Edge>> vec = new Vector<>(c);
        for (int i = 0; i < c; ++i) {
            vec.add(new ArrayList<>());
        }
        for (Edge e : edges) {
            vec.get(e.origin).add(new Edge(e.origin, e.target));
        }
        return vec;
    }

    // get indices of SCC for each node
    public int[] getComponentIndices() {
        return this.componentCount;
    }

}
