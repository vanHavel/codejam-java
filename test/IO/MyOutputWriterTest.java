package IO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MyOutputWriterTest {

    private ByteArrayOutputStream stringStream;
    private MyOutputWriter writer;

    @BeforeEach
    public void setUp() {
        stringStream = new ByteArrayOutputStream();
        writer = new MyOutputWriter(stringStream);
    }

    @Test
    public void testPrintLine() throws IOException {
        writer.printLine("Test.");
        writer.flush();
        assertEquals("Test.\n", stringify(stringStream));
    }

    @Test
    public void testPrintImpossible() throws IOException {
        writer.printImpossible(4);
        writer.flush();
        assertEquals("Case #4: IMPOSSIBLE\n", stringify(stringStream));
    }

    @Test
    public void testPrintSolutionString() throws IOException {
        writer.printSolution(1, "Test.");
        writer.flush();
        assertEquals("Case #1: Test.\n", stringify(stringStream));
    }

    @Test
    public void testPrintSolutionChar() throws IOException {
        writer.printSolution(1, "c");
        writer.flush();
        assertEquals("Case #1: c\n", stringify(stringStream));
    }

    @Test
    public void testPrintSolutionInt() throws IOException {
        writer.printSolution(1, 2);
        writer.flush();
        assertEquals("Case #1: 2\n", stringify(stringStream));
    }

    @Test
    public void testPrintSolutionLong() throws IOException {
        writer.printSolution(1, 2l);
        writer.flush();
        assertEquals("Case #1: 2\n", stringify(stringStream));
    }

    @Test
    public void testPrintSolutionFloat() throws IOException {
        writer.printSolution(1, 2.0);
        writer.flush();
        assertEquals("Case #1: 2.0\n", stringify(stringStream));
    }

    @Test
    public void testPrintSolutionDouble() throws IOException {
        writer.printSolution(1, 2.0d);
        writer.flush();
        assertEquals("Case #1: 2.0\n", stringify(stringStream));
    }

    private String stringify(ByteArrayOutputStream stringStream) {
        return new String(stringStream.toByteArray(), StandardCharsets.UTF_8);
    }
}