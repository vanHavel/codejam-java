package IO;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by lukashuwald on 05.02.18.
 */
public class MyInputReader {

    private Scanner scanner;

    public MyInputReader(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public int nextInt() {
        return this.scanner.nextInt();
    }

    public long nextLong() {
        return this.scanner.nextLong();
    }

    public String nextString() {
        return this.scanner.next();
    }

    public String next() {
        return this.scanner.next();
    }

    public String nextLine() {
        return this.scanner.nextLine();
    }

    public double nextDouble() {
        return this.scanner.nextDouble();
    }

    public float nextFloat() {
        return this.scanner.nextFloat();
    }

    public boolean hasNext() {
        return this.scanner.hasNext();
    }

    public char nextStandAloneChar() {
        String next = this.scanner.next();
        if (next.length() == 1) {
            return next.charAt(0);
        }
        else {
            throw new RuntimeException("Expected character, got string '" + next + "'");
        }
    }

    public char nextChar() {
        Pattern delim = this.scanner.delimiter();
        this.scanner.useDelimiter("");
        char c = this.scanner.next().charAt(0);
        this.scanner.useDelimiter(delim);
        return c;
    }

    public int[] nextIntArray(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = this.scanner.nextInt();
        }
        return res;
    }

    public long[] nextLongArray(int n) {
        long[] res = new long[n];
        for (int i = 0; i < n; ++i) {
            res[i] = this.scanner.nextLong();
        }
        return res;
    }

    public int[][] nextIntMatrix(int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i][j] = this.scanner.nextInt();
            }
        }
        return res;
    }

    public String[] nextStringArray(int n) {
        String[] res = new String[n];
        for (int i = 0; i < n; ++i) {
            res[i] = this.scanner.next();
        }
        return res;
    }

    public String[] nextLineArray(int n) {
        String[] res = new String[n];
        Pattern delim = this.scanner.delimiter();
        this.scanner.useDelimiter("\\n");
        for (int i = 0; i < n; ++i) {
            res[i] = this.scanner.next();
        }
        this.scanner.useDelimiter(delim);
        return res;
    }

    public char[][] nextCharMatrix(int n, int m) {
        String[] lines = this.nextLineArray(n);
        char[][] res = new char[n][m];
        for (int i = 0; i < n; ++i) {
            res[i] = lines[i].toCharArray();
        }
        return res;
    }

    public int[][] nextDigitMatrix(int n, int m) {
        String[] lines = this.nextLineArray(n);
        int[][] res = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                res[i][j] = lines[i].charAt(j);
            }
        }
        return res;
    }

}
