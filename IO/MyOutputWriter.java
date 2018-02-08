package IO;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by lukashuwald on 05.02.18.
 */
public class MyOutputWriter {

    private PrintWriter writer;

    public MyOutputWriter(OutputStream out) {
        this.writer = new PrintWriter(out);
    }

    public MyOutputWriter(Writer w) {
        this.writer = new PrintWriter(w);
    }

    public void printLine(String s) {
        this.writer.println(s);
    }

    public void printSolution(int testNumber, String s) {
        this.writer.println("Case #" + testNumber + ": " + s);
    }

    public void printSolution(int testNumber, int i) {
        this.writer.println("Case #" + testNumber + ": " + i);
    }

    public void printSolution(int testNumber, long l) {
        this.writer.println("Case #" + testNumber + ": " + l);
    }

    public void printSolution(int testNumber, float f) {
        this.writer.println("Case #" + testNumber + ": " + f);
    }

    public void printSolution(int testNumber, double d) {
        this.writer.println("Case #" + testNumber + ": " + d);
    }

    public void printSolution(int testNumber, char c) {
        this.writer.println("Case #" + testNumber + ": " + c);
    }

    public void printImpossible(int testNumber) {
        this.writer.println("Case #" + testNumber + ": " + "IMPOSSIBLE");
    }

    public void close() {
        this.writer.close();
    }
}
