package structure;

import java.util.*;

// this class implement a trie (prefix tree) that contains sequences of the generic type A
public class Trie<A> {

    private Map<A, Trie<A>> successors;

    public Map<A, Trie<A>> getSuccessors() {
        return successors;
    }

    public Trie() {
        this.successors = new HashMap<>();
    }

    public boolean isLeaf() {
        return this.successors.isEmpty();
    }

    // insert a new list of elements into the trie
    public void insert(List<A> input) {
        List<A> list = new LinkedList<>(input);
        if (list.isEmpty()) {
            return;
        }
        A head = list.get(0);
        list.remove(0);
        if (this.successors.containsKey(head)) {
            Trie<A> subTrie = this.successors.get(head);
            subTrie.insert(list);
        }
        else {
            Trie<A> subTrie = new Trie<>();
            this.successors.put(head, subTrie);
            subTrie.insert(list);
        }
    }

    // search for the subtrie corresponding to given prefix
    private Trie<A> searchSubTrie(List<A> input) {
        List<A> list = new LinkedList<>(input);
        if (list.isEmpty()) {
            return this;
        }
        A head = list.get(0);
        list.remove(0);
        if (this.successors.containsKey(head)) {
            return this.successors.get(head).searchSubTrie(list);
        }
        else {
            return null;
        }
    }

    // return longest prefix of given list that is in the trie
    public List<A> longestPrefix(List<A> input) {
        List<A> list = new LinkedList<>(input);
        if (list.isEmpty()) {
            LinkedList<A> res = new LinkedList<>();
            return res;
        }
        A head = list.get(0);
        list.remove(0);
        if (this.successors.containsKey(head)) {
            List<A> rec = this.successors.get(head).longestPrefix(list);
            rec.add(0, head);
            return rec;
        }
        else {
            LinkedList<A> res = new LinkedList<>();
            return res;
        }
    }

    // check whether the trie contains a list with given prefix
    public boolean containsPrefix(List<A> input) {
        return this.searchSubTrie(input) != null;
    }

    // search whether trie contains a leaf corresponding to input sequence
    public boolean search(List<A> input) {
        Trie<A> subTrie = this.searchSubTrie(input);
        return subTrie != null && subTrie.isLeaf();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trie<?> trie = (Trie<?>) o;
        return Objects.equals(successors, trie.successors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(successors);
    }

}
