package structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    public void testTrie() {
        Trie<Character> trie = new Trie<>();
        // insertion
        trie.insert(Trie.stringAsList("abc"));
        trie.insert(Trie.stringAsList("abd"));
        trie.insert(Trie.stringAsList("aaef"));
        trie.insert(Trie.stringAsList("aae"));
        trie.insert(Trie.stringAsList("zuf"));

        // test search
        assertEquals(true, trie.search(Trie.stringAsList("abc")));
        assertEquals(true, trie.search(Trie.stringAsList("aaef")));
        assertEquals(true, trie.search(Trie.stringAsList("zuf")));
        assertEquals(false, trie.search(Trie.stringAsList("ab")));
        assertEquals(false, trie.search(Trie.stringAsList("aae")));
        assertEquals(false, trie.search(Trie.stringAsList("abe")));

        // test containsPrefix
        assertEquals(true, trie.containsPrefix(Trie.stringAsList("")));
        assertEquals(true, trie.containsPrefix(Trie.stringAsList("a")));
        assertEquals(true, trie.containsPrefix(Trie.stringAsList("ab")));
        assertEquals(true, trie.containsPrefix(Trie.stringAsList("abd")));
        assertEquals(true, trie.containsPrefix(Trie.stringAsList("aae")));
        assertEquals(false, trie.search(Trie.stringAsList("abcd")));
        assertEquals(false, trie.search(Trie.stringAsList("d")));

        // test longestPrefix
        assertEquals(Trie.stringAsList("abc"),
                     trie.longestPrefix(Trie.stringAsList("abcd")));
        assertEquals(Trie.stringAsList("aa"),
                trie.longestPrefix(Trie.stringAsList("aafg")));
        assertEquals(Trie.stringAsList("a"),
                trie.longestPrefix(Trie.stringAsList("adc")));
        assertEquals(Trie.stringAsList("zuf"),
                trie.longestPrefix(Trie.stringAsList("zuffenhausen")));
        assertEquals(Trie.stringAsList(""),
                trie.longestPrefix(Trie.stringAsList("hzu")));

    }

}