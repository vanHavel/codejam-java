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
}
