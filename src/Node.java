import java.util.Arrays;

import java.lang.*;

public class Node implements Comparable<Node> {
    int cost = 0;
    int f=0;
    int[][] board;
    point farmer;
    Node parent;

    Node(int[][] board, point farmer) {
        this.board = board;
        this.farmer = farmer;

    }

    Node(int[][] board, point farmer, int cost) {
        this.board = board;
        this.farmer = farmer;
        this.cost = cost;

    }

    Node(int[][] board, point farmer, int cost, Node parent) {
        this.board = board;
        this.farmer = farmer;
        this.cost = cost;
        this.parent = parent;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node otherNode = (Node) obj;
        return Arrays.deepEquals(this.board, otherNode.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);

    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }

}
