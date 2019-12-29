package Searchers;

import Models.Node;

import java.util.List;

public interface ISearch {

    /**
     * Find a shortest path from start to goal.
     * @param start node
     * @param goal node
     * @return Models.Node path
     */
    List<Node> findShortestPath(Node start, Node goal);
}
