package structure;

/**
 * Created by lukashuwald on 08.02.18.
 */

// this class implements a union find structure of disjoint sets
public class UnionFind {

    private int classes;
    private int[] parents;
    private int[] ranks;

    // create new structure with classes many singleton sets
    public UnionFind(int classes) {
        this.classes = classes;
        this.parents = new int[classes];
        for (int i = 0; i < classes; ++i) {
            this.parents[i] = i;
        }
        this.ranks = new int[classes];
    }

    // get number of disjoint sets
    public int getClasses() {
        return this.classes;
    }

    // find root of a set element
    public int find(int i) {
        // iterative to avoid stack overflows
        int cur = i;
        while (cur != this.parents[cur]) {
            cur = this.parents[cur];
        }
        int root = cur;

        // path compression
        cur = i;
        while (cur != this.parents[cur]) {
            int temp = cur;
            cur = this.parents[cur];
            this.parents[temp] = root;
        }

        return root;
    }

    // unite two disjoint sets
    public void union(int i, int j) {
        int ri = this.find(i);
        int rj = this.find(j);
        if (ri != rj) {
            classes--;
            // union by rank
            if (this.ranks[ri] < this.ranks[rj]) {
                this.parents[ri] = rj;
            }
            else if (this.ranks[rj] < this.ranks[ri]) {
                this.parents[rj] = ri;
            }
            else {
                this.parents[ri] = rj;
                this.ranks[rj]++;
            }
        }
    }
}
