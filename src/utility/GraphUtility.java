package utility;

import data.Edge;
import data.WeightedEdge;

import java.util.ArrayList;
import java.util.List;

public class GraphUtility {

    // transform adjacency matrix to adjacency list
    public static List<List<Edge>> adjacencyMatrixToAdjacencyList(boolean[][] edges) {
        int n = edges.length;
        List<List<Edge>> vec = new ArrayList<>(n);

        for (int i = 0; i < n; ++i) {
            List<Edge> list = new ArrayList<>(n);
            for (int j = 0; j < n; ++j) {
                if (edges[i][j]) {
                    list.add(new Edge(i,j));
                }
            }
            vec.add(list);
        }
        return vec;
    }

    // transform connection matrix of bipartite graph into adjacency list
    public static List<List<Edge>> bipartiteAdjacencyMatrixToAdjacencyList(boolean[][] edges) {
        int n = edges.length;
        int m = (n == 0 ? 0 : edges[0].length);
        int size = n + m;
        List<List<Edge>> vec = new ArrayList<>(size);
        for (int i = 0; i < n; ++i) {
            vec.add(new ArrayList<>(m));
        }
        for (int i = 0; i < m; ++i) {
            vec.add(new ArrayList<>(n));
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (edges[i][j]) {
                    vec.get(i).add(new Edge(i, j + n));
                    vec.get(j + n).add(new Edge(j + n, i));
                }
            }
        }

        return vec;
    }

    // transforms long matrix of positive weights into adjacency list containing nonzero weights
    public static List<List<WeightedEdge<Long>>> longWeightMatrixToAdjacencyList(long[][] weights) {
        int n = weights.length;
        List<List<WeightedEdge<Long>>> vec = new ArrayList<>(n);

        for (int i = 0; i < n; ++i) {
            List<WeightedEdge<Long>> list = new ArrayList<>(n);
            for (int j = 0; j < n; ++j) {
                if (weights[i][j] != 0) {
                    list.add(new WeightedEdge<>(i,j,weights[i][j]));
                }
            }
            vec.add(list);
        }
        return vec;
    }
}
