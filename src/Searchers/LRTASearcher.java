package Searchers;

import Models.Node;

import java.util.Map;

/**
 * LRTA* implementation
 *
 */
public class LRTASearcher extends AbstractRTSearcher {

    @Override
    public Node getBestNeighbor(Node currentNode, Node goalNode) {
        Node bestNeighbor = null;
        double minNeighborF = Integer.MAX_VALUE;
        for (Map.Entry<Node, Double> neighborEntry : currentNode.neighbors.entrySet()) {

            // get neighbor's features
            Node neighbor = neighborEntry.getKey();
            double cost = neighborEntry.getValue();
            neighbor.g = currentNode.g + cost;
            if (!neighbor.visited) {
                // set initial neighbor's initial h
                neighbor.visited = true;
                neighbor.h = neighbor.getInitialH(goalNode);
            }
            double neighborF = neighbor.getF();
            if (neighborF < minNeighborF) {
                bestNeighbor = neighbor;
                minNeighborF = neighborF;
            }
        }
        if (bestNeighbor != null)
            currentNode.h = Math.max(currentNode.h, bestNeighbor.getF());
        return bestNeighbor;
    }

    @Override
    public String toString() {
        return "LRTASearcher";
    }
}
