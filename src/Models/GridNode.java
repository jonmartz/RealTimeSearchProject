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
    private double getManhattanDistance(GridNode node) {
        return Math.abs(node.x-x)+Math.abs(node.y-y);
    }

    /**
     * Get the octile distance. May generate heuristic depressions
     * @param node to measure distance from
     * @return octile distance
     */
    private double getOctileDistance(GridNode node) {
        int x_delta = Math.abs(x-node.x);
        int y_delta = Math.abs(y-node.y);
        return (x_delta + y_delta) + (Math.sqrt(2) - 2) * Math.min(x_delta, y_delta);
    }

    @Override
    public double getInitialH(Node node) {
        if (manualInitialH)
            return h;
        return getOctileDistance((GridNode)node);
//        return getManhattanDistance((GridNode)node);
    }

    @Override
    public String toString() {
        return "("+x+", "+y+") h="+h;
    }
}
