package Searchers;

import Models.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract real-time search where the method for choosing the best neighbor must be implemented
 */
public abstract class AbstractRTSearcher implements ISearcher {

    @Override
    public List<Node> findShortestPath(Node startNode, Node goalNode) {
        List<Node> path = new ArrayList<>();
        path.add(startNode);

        startNode.h = startNode.getInitialH(goalNode);
        startNode.visited = true;
        Node currentNode = startNode;

        while (currentNode != goalNode) {
            currentNode.g = 0; // real time, so current is actually the new start
            Node bestNeighbor = getBestNeighbor(currentNode, goalNode);
            if (bestNeighbor == null) // no solution
                return new ArrayList<>();
            currentNode = bestNeighbor;
            path.add(currentNode);
        }
        return path;
    }

    /**
     * Get the best neighbor to move to from current neighbor
     * @param currentNode move from
     * @param goalNode of search
     * @return best neighbor of current node given goal
     */
    public abstract Node getBestNeighbor(Node currentNode, Node goalNode);
}
