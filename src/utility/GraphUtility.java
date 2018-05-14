package utility;

import data.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GraphUtility {

    // transform adjacency matrix to adjacency list
    public static Vector<List<Edge>> adjacencyMatrixToAdjacencyList(boolean[][] edges) {
        int n = edges.length;
        Vector<List<Edge>> vec = new Vector(n);

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
    public static Vector<List<Edge>> bipartiteAdjacencyMatrixToAdjacencyList(boolean[][] edges) {
        int n = edges.length;
        int m = (n == 0 ? 0 : edges[0].length);
        int size = n + m;
        Vector<List<Edge>> vec = new Vector(size);
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
}
