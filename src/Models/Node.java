package Models;

import java.util.HashMap;

/**
 * Represents a node in a graph.
 */
public class Node {
    public int id; // incremental
    public HashMap<Node, Double> neighbors = new HashMap<>(); // maps neighbors to costs
    public double g; // distance from start node
    public double h; // heuristic from goal node
    public boolean visited = false;

    /**
     * Get heuristic from node
     * @return
     */
    public double getH(Node node) {
        return Integer.MAX_VALUE;
    }

    /**
     * Constructor
     */
    public Node(int id) {
        this.id = id;
    }

    /**
     * Add neighbor to self's neighbors and self to neighbor's neighbors
     */
    public void addNeighbor(Node neighbor, double cost){
        neighbors.put(neighbor, cost);
        neighbor.neighbors.put(this, cost);
    }

    public double getF() {
        return g + h;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
