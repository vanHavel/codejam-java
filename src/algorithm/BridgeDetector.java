package algorithm;

import data.Edge;
import utility.GraphUtility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

// this class finds bridges and articulation points in directed graphs
public class BridgeDetector {

    // the graph's adjacency list
    private Vector<List<Edge>> adjacencyList;

    // fields used in computation
    private int[] parents;
    private int[] visitCounter;
    private int[] lowestNodeReachable;
    private int searchCounter = 0;

    // result fields
    Set<Integer> articulationPoints = new HashSet<>();
    Set<Edge> bridges = new HashSet<>();

    // construct with adjacency list
    public BridgeDetector(Vector<List<Edge>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    // construct with adjacency matrix
    public BridgeDetector(boolean[][] edges) {
        this(GraphUtility.adjacencyMatrixToAdjacencyList(edges));
    }

    // computes all bridges and articulation points
    public void computeBridges() {
        // init fields
        int n = this.adjacencyList.size();
        this.visitCounter = new int[n];
        this.lowestNodeReachable = new int[n];
        this.parents = new int[n];
        for (int i = 0; i < n; ++i) {
            this.visitCounter[i] = -1;
        }

        // depth first search from each node
        for (int i = 0; i < n; ++i) {
            if (this.visitCounter[i] == -1) {
                int rootChildren = bridgeSearch(i);
                if (rootChildren > 1) {
                    // special case: root is articulation points
                    articulationPoints.add(i);
                }
            }
        }
    }

    // recursive depth first search for bridges and articulation points
    // returns number of children that are explored from this node
    private int bridgeSearch(int i) {
        visitCounter[i] = this.searchCounter;
        lowestNodeReachable[i] = this.searchCounter;
        this.searchCounter++;

        int children = 0;
        // check neighbors
        for (Edge e : this.adjacencyList.get(i)) {
            int target = e.target;
            if (this.visitCounter[target] == -1) {
                this.parents[target] = i;
                children++;

                bridgeSearch(target);

                // add articulation points and bridges
                if (lowestNodeReachable[target] >= visitCounter[i]) {
                    this.articulationPoints.add(i);
                }
                if (lowestNodeReachable[target] > visitCounter[i]) {
                    this.bridges.add(e);
                }

                // update lowest node
                this.lowestNodeReachable[i] =
                        Math.min(this.lowestNodeReachable[i], this.lowestNodeReachable[target]);

            }
            // back edge, update lowest node
            else if (target != this.parents[i]) {
                this.lowestNodeReachable[i] =
                        Math.min(this.lowestNodeReachable[i], this.visitCounter[target]);
            }
        }

        return children;
    }

    // obtain articulation points
    public Set<Integer> getArticulationPoints() {
        return this.articulationPoints;
    }

    // obtain bridges
    public Set<Edge> getBridges() {
        return this.bridges;
    }

}
