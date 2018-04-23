package IO;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MyInputReaderTest {

    @Test
    public void testNextChar() {
        String string = "ab";
        MyInputReader reader = new MyInputReader(streamify(string));
        assertEquals('a', reader.nextChar());
        assertEquals('b', reader.nextChar());
        assertThrows(RuntimeException.class, reader::nextChar);
    }

    @Test
    public void testNextStandaloneChar() {
        String string = "a b cd";
        MyInputReader reader = new MyInputReader(streamify(string));
        assertEquals('a', reader.nextStandAloneChar());
        assertEquals('b', reader.nextStandAloneChar());
        assertThrows(RuntimeException.class, reader::nextStandAloneChar);
    }

    @Test
    public void testNextInt() {
        String string = "1 -5 abc";
        MyInputReader reader = new MyInputReader(streamify(string));
        assertEquals(1, reader.nextInt());
        assertEquals(-5, reader.nextInt());
        assertThrows(RuntimeException.class, reader::nextInt);
    }

    @Test
    public void testNextLong() {
        String string = "1 -5 abc";
        MyInputReader reader = new MyInputReader(streamify(string));
        assertEquals(1l, reader.nextLong());
        assertEquals(-5l, reader.nextLong());
        assertThrows(RuntimeException.class, reader::nextLong);
    }

    @Test
    public void testNextFloat() {
        Locale.setDefault(Locale.US);
        String string = "1.0 -5 abc";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertEquals(1, reader.nextFloat());
        assertEquals(-5, reader.nextFloat());
        assertThrows(RuntimeException.class, reader::nextFloat);
    }

    @Test
    public void testNextDouble() {
        Locale.setDefault(Locale.US);
        String string = "1 -5.0 abc";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertEquals(1, reader.nextDouble());
        assertEquals(-5, reader.nextDouble());
        assertThrows(RuntimeException.class, reader::nextDouble);
    }

    @Test
    public void testNextString() {
        String string = "ab cd";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertEquals("ab", reader.next());
        assertEquals("cd", reader.nextString());
    }

    @Test
    public void testHasNext() {
        String string = "ab cd";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertTrue(reader.hasNext());
        reader.next();
        assertTrue(reader.hasNext());
        reader.next();
        assertFalse(reader.hasNext());
    }

    @Test
    public void testNextLine() {
        String string = "ab cd\ndef";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertEquals("ab cd", reader.nextLine());
        assertEquals("def", reader.nextLine());
    }

    @Test
    public void testNextIntArray() {
        String string = "10 12 3";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertArrayEquals(new int[] {10,12,3}, reader.nextIntArray(3));
    }

    @Test
    public void testNextLongArray() {
        String string = "10 12 3";
        MyInputReader reader = new MyInputReader(streamify(string));
        assertArrayEquals(new long[] {10,12,3}, reader.nextLongArray(3));
    }

    @Test
    public void testNextIntMatrix() {
        String string = "10 12\n3 4";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertArrayEquals(new int[][] {new int[] {10,12}, new int[] {3, 4}}, reader.nextIntMatrix(2,2));
    }

    @Test
    public void testNextLineArray() {
        String string = "ab\n\ncd";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertArrayEquals(new String[] {"ab", "", "cd"}, reader.nextLineArray(3));
    }

    @Test
    public void testNextStringArray() {
        String string = "ab cd ef";
        MyInputReader reader = new MyInputReader(streamify(string));
        assertArrayEquals(new String[] {"ab", "cd", "ef"}, reader.nextStringArray(3));
    }

    @Test
    public void testNextCharMatrix() {
        String string = "abc\ndef";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertArrayEquals(new char[][] {new char[] {'a','b','c'}, new char[] {'d','e','f'}}, reader.nextCharMatrix(2,3));
    }

    @Test
    public void testNextDigitMatrix() {
        String string = "123\n456";
        MyInputReader reader = new MyInputReader(streamify(string));

        assertArrayEquals(new int[][]{new int[] {1,2,3}, new int[]{4,5,6}}, reader.nextDigitMatrix(2,3));
    }


    private InputStream streamify(String string) {
        return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    }


}