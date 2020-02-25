package Models;

import java.util.HashMap;

/**
 * Represents a node in a graph.
 */
public class Node {
    public int id; // incremental
    public HashMap<Node, Double> neighbors = new HashMap<>(); // maps each neighbor to the cost of moving to it
    public double g; // distance from start node
    public double h; // heuristic from goal node
    public boolean visited = false;
    public boolean updated = false; // to be used by aLSS_LRTASearcher
    public boolean manualInitialH = false; // to indicate a manual h value was set

    public Node(int id) {
        this.id = id;
    }

    /**
     * get initial heuristic from node
     */
    public double getInitialH(Node node) {
        if (manualInitialH)
            return h;
        return Integer.MAX_VALUE;
    }

    /**
     * add neighbor to self's neighbors and self to neighbor's neighbors
     */
    public void addNeighbor(Node neighbor, double cost){
        neighbors.put(neighbor, cost);
        neighbor.neighbors.put(this, cost);
    }

    /**
     * get f = g + h
     */
    public double getF() {
        return g + h;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public void setInitialH(int h) {
        this.h = h;
        manualInitialH = true;
    }
}
