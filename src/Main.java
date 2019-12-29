import Models.*;
import Searchers.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String mapName = "test";
        String mapPath = "./Resources/"+mapName+".map";
        GridGraph graph = new GridGraph(mapPath);
        GridNode start = graph.getNode(1, 1);
        GridNode goal = graph.getNode(5, 3);
        ISearch search = new LRTASearch();
        List<Node> path = search.findShortestPath(start, goal);
        for (Node node : path)
            System.out.println(node);
    }
}
