package Searchers;

import Models.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LRTASearch implements ISearch {

    private List<Node> path;
    private Node currentNode;

    @Override
    public List<Node> findShortestPath(Node startNode, Node goalNode) {
        path = new ArrayList<>();
        path.add(startNode);

        startNode.visited = true;
        startNode.h = startNode.getH(goalNode);
        currentNode = startNode;

        while (currentNode != goalNode) {

            // find neighbor with lowest f
            Node bestNeighbor = null;
            double minNeighborF = Integer.MAX_VALUE;
            for (Map.Entry<Node, Double> neighborEntry : currentNode.neighbors.entrySet()) {
                Node neighbor = neighborEntry.getKey();
                double cost = neighborEntry.getValue();

                neighbor.g = currentNode.g + cost;
                if (!neighbor.visited) {
                    // set initial neighbor's initial h
                    neighbor.visited = true;
                    neighbor.h = neighbor.getH(goalNode);
                }
                double neighborF = neighbor.getF();
                if (neighborF < minNeighborF) {
                    bestNeighbor = neighbor;
                    minNeighborF = neighborF;
                }
            }
            currentNode.h = Math.max(currentNode.h, minNeighborF);

            // advance to neighbor
            currentNode = bestNeighbor;
            path.add(currentNode);
        }
        return path;
    }
}
