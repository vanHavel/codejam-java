package structure;

import utility.MyUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    public void testTrie() {
        Trie<Character> trie = new Trie<>();
        // insertion
        trie.insert(MyUtility.stringAsList("abc"));
        trie.insert(MyUtility.stringAsList("abd"));
        trie.insert(MyUtility.stringAsList("aaef"));
        trie.insert(MyUtility.stringAsList("aae"));
        trie.insert(MyUtility.stringAsList("zuf"));

        // test search
        assertEquals(true, trie.search(MyUtility.stringAsList("abc")));
        assertEquals(true, trie.search(MyUtility.stringAsList("aaef")));
        assertEquals(true, trie.search(MyUtility.stringAsList("zuf")));
        assertEquals(false, trie.search(MyUtility.stringAsList("ab")));
        assertEquals(false, trie.search(MyUtility.stringAsList("aae")));
        assertEquals(false, trie.search(MyUtility.stringAsList("abe")));

        // test containsPrefix
        assertEquals(true, trie.containsPrefix(MyUtility.stringAsList("")));
        assertEquals(true, trie.containsPrefix(MyUtility.stringAsList("a")));
        assertEquals(true, trie.containsPrefix(MyUtility.stringAsList("ab")));
        assertEquals(true, trie.containsPrefix(MyUtility.stringAsList("abd")));
        assertEquals(true, trie.containsPrefix(MyUtility.stringAsList("aae")));
        assertEquals(false, trie.search(MyUtility.stringAsList("abcd")));
        assertEquals(false, trie.search(MyUtility.stringAsList("d")));

        // test longestPrefix
        assertEquals(MyUtility.stringAsList("abc"),
                     trie.longestPrefix(MyUtility.stringAsList("abcd")));
        assertEquals(MyUtility.stringAsList("aa"),
                trie.longestPrefix(MyUtility.stringAsList("aafg")));
        assertEquals(MyUtility.stringAsList("a"),
                trie.longestPrefix(MyUtility.stringAsList("adc")));
        assertEquals(MyUtility.stringAsList("zuf"),
                trie.longestPrefix(MyUtility.stringAsList("zuffenhausen")));
        assertEquals(MyUtility.stringAsList(""),
                trie.longestPrefix(MyUtility.stringAsList("hzu")));

    }

}