package Models;

/**
 * Represents a grid node, with has also an x and y coordinates
 */
public class GridNode extends Node{

    public int x; // column of node
    public int y; // row of node

    /**
     * Constructor
     * @param x col of node
     * @param y row of node
     */
    public GridNode(int id, int x, int y) {
        super(id);
        this.x = x;
        this.y = y;
    }

    /**
     * Get the manhattan distance
     * @param node to measure distance from
     * @return manhattan distance
     */
    private int getManhattanDistance(GridNode node) {
        return Math.abs(node.x-x)+Math.abs(node.y-y);
    }

    @Override
    public double getH(Node node) {
        return getManhattanDistance((GridNode)node);
    }

    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }
}
