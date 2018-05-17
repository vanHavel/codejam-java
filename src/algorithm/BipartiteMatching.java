package algorithm;

import data.Edge;
import data.Tuple;
import utility.GraphUtility;

import java.util.*;

// this class computes maximum bipartite matchings
public class BipartiteMatching {

    // graph information: edges and partition sizes
    private List<? extends Collection<Edge>> adjacencyList;
    private int n;
    private int m;

    // internal fields for algorithm
    private int[] matchPartner;
    private boolean[] visited;

    // result fields
    private int matchingSize = 0;
    private Set<Edge> matching;

    // construct with bipartite connection matrix
    // (edges[i][j]: edge between first set node i and second set node j)
    public BipartiteMatching(boolean[][] edges) {
        this.n = edges.length;
        this.m = (n == 0 ? 0 : edges[0].length);
        this.adjacencyList = GraphUtility.bipartiteAdjacencyMatrixToAdjacencyList(edges);
    }
    // construct with adjacency list and partition sizes
    // the first n indices into the list must be the first partition
    public BipartiteMatching(List<? extends Collection<Edge>> adjacencyList, int n, int m) {
        this.adjacencyList = adjacencyList;
        this.n = n;
        this.m = m;
    }

    // using augmenting paths, the complexity is O(VE)
    public void computeMaximumMatching() {
        // initialize all right nodes as unmatched
        this.matchPartner = new int[m];
        for (int i = 0; i < this.m; ++i) {
            this.matchPartner[i] = -1;
        }
        // iterate over all left nodes and augment
        for (int i = 0; i < this.n; ++i) {
            this.visited = new boolean[n];
            this.matchingSize += augmentFrom(i);
        }
        // get matching Edges
        this.matching = new HashSet<>();
        for (int i = 0; i < this.m; ++i) {
            if (this.matchPartner[i] != -1) {
                this.matching.add(new Edge(this.matchPartner[i], i + this.n));
            }
        }
    }

    // search for augmenting path from starting left node, return 1 if found, 0 otherwise
    private int augmentFrom(int leftNode) {
        if (this.visited[leftNode]) { return 0; }
        this.visited[leftNode] = true;
        for (Edge edge : this.adjacencyList.get(leftNode)) {
            int rightNode = edge.target - n;
            // unmatched node
            if (matchPartner[rightNode] == -1) {
                matchPartner[rightNode] = leftNode;
                return 1;
            }
            // try to recursively augment
            else {
                int rec = augmentFrom(matchPartner[rightNode]);
                if (rec == 1) {
                    matchPartner[rightNode] = leftNode;
                    return 1;
                }
            }
        }
        // no augmentation found
        return 0;
    }

    public int getMatchingSize() {
        return this.matchingSize;
    }

    public Set<Edge> getMatching() {
        return this.matching;
    }
}
