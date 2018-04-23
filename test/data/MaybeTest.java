package data;

import org.junit.jupiter.api.Test;

import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class MaybeTest {

    @Test
    public void testMaybe() {
        Maybe<Integer> nothing = new Maybe<>();
        Maybe<Integer> just5 = new Maybe<>(5);

        assertFalse(nothing.isPresent());
        assertTrue(just5.isPresent());

        assertEquals(5, (int) just5.getValue());
        assertThrows(RuntimeException.class, () -> nothing.getValue());

        assertEquals("Nothing", nothing.toString());
        assertEquals("Just 5", just5.toString());

        UnaryOperator<Integer> succer = i -> i + 1;
        assertEquals(nothing, nothing.fmap(succer));
        assertEquals(new Maybe<>(6), just5.fmap(succer));

    }

}