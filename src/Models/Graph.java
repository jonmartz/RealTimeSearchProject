package Models;

import java.util.*;

/**
 * Represents a graph
 */
public class Graph {
    public ArrayList<Node> nodes = new ArrayList<>();

    public void addNode(Node node){
        nodes.add(node);
    }

    /**
     * Return a random node
     */
    public Node getRandomNode() {
        Random rand = new Random();
        return nodes.get(rand.nextInt(nodes.size()));
    }

    /**
     * Reset the state of the nodes in the graph, for doing more than one search in the graph
     */
    public void reset() {
        for (Node node : nodes){
            node.visited = false;
        }
    }
}
