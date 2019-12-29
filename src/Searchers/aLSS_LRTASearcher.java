package Searchers;

import Models.Node;

import java.util.Map;

/**
 * aLSS_LRTA* implementation, with lookahead = 1
 * (because we're comparing it to LRTA* which also has lookahead = 1)
 */
public class aLSS_LRTASearcher extends AbstractRTSearcher {

    @Override
    public Node getBestNeighbor(Node currentNode, Node goalNode) {
        Node bestNeighbor = null;
        double minNeighborF = Integer.MAX_VALUE;
        boolean foundNotUpdated = false;
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

            if (!(foundNotUpdated && neighbor.updated)) { // ignore updated neighbor if already found one not updated
                if (!foundNotUpdated && !neighbor.updated) { // found first not updated
                    foundNotUpdated = true;
                    bestNeighbor = neighbor;
                    minNeighborF = neighborF;
                } else if (neighborF < minNeighborF) { // consider neighbor for new best
                    bestNeighbor = neighbor;
                    minNeighborF = neighborF;
                }
            }
        }
        if (bestNeighbor != null && currentNode.h < bestNeighbor.getF()) {
            currentNode.h = bestNeighbor.getF();
            currentNode.updated = true;
        }
        return bestNeighbor;
    }

    @Override
    public String toString() {
        return "aLSS_LRTASearcher";
    }
}
