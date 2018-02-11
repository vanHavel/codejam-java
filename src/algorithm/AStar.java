package algorithm;

import data.Tuple;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

// this class implements the A* pathfinding/search algorithm
public class AStar<S> {

    private Function<S, Collection<Tuple<S, Integer>>> successors;
    private Predicate<S> goalTest;
    private ToIntFunction<S> heuristic;
    private PriorityQueue<Node<S>> openList;
    private HashSet<S> closedList;

    // node class for AStar. Contains state, cost to reach it and heuristic value.
    private class Node<S> implements Comparable<Node<S>>{
        public S state;
        public int cost;
        public int h;

        public Node(S state, int cost, int h) {
            this.state = state;
            this.cost = cost;
            this.h = h;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return cost == node.cost &&
                    h == node.h &&
                    Objects.equals(state, node.state);
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, cost, h);
        }

        @Override
        public int compareTo(Node<S> o) {
            return Integer.compare(this.cost + this.h, o.cost + o.h);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "state=" + state +
                    ", cost=" + cost +
                    ", h=" + h +
                    '}';
        }
    }

    // initialize class with initial state, successor function, goal predicate and heuristic
    public AStar(S initialState, Function<S, Collection<Tuple<S, Integer>>> successors, Predicate<S> goalTest, ToIntFunction<S> heuristic) {
        this.successors = successors;
        this.goalTest = goalTest;
        this.heuristic = heuristic;
        this.openList = new PriorityQueue<>();
        this.openList.add(new Node<S>(initialState, 0, heuristic.applyAsInt(initialState)));
        this.closedList = new HashSet<>();
    }

    // perform AStar search and return final state and cost to reach it
    // returns null if no solution exists
    public Tuple<S, Integer> search() {
        while (! this.openList.isEmpty()) {
            Node<S> head = this.openList.poll();
            // found goal
            if (this.goalTest.test(head.state)) {
                return new Tuple<>(head.state, head.cost);
            }
            // found already visited state
            else if (this.closedList.contains(head.state)){
                continue;
            }
            else {
                // found new state: add to closed list
                this.closedList.add(head.state);
                // add successors to open list
                Collection<Tuple<S, Integer>> succs = this.successors.apply(head.state);
                for (Tuple<S, Integer> t : succs) {
                    if (!this.closedList.contains(t.fst)) {
                        Node<S> newNode = new Node<>(t.fst, head.cost + t.snd, this.heuristic.applyAsInt(t.fst));
                        this.openList.add(newNode);
                    }
                }

            }
        }
        return null;

    }
}
