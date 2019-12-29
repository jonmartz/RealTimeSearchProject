import Models.*;
import Searchers.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // choose maps
        String[] mapNames = {"den312d"};
        for (String mapName : mapNames) {
            System.out.println("map: " + mapName + "\n");

            String mapPath = "./Resources/" + mapName + ".map";
            GridGraph graph = new GridGraph(mapPath);

            // choose start and goal
//        GridNode start = graph.getNode(1, 1);
//        GridNode goal = graph.getNode(5, 3);
            GridNode start = (GridNode) graph.getRandomNode();
            GridNode goal = (GridNode) graph.getRandomNode();
            while (start == goal)
                goal = (GridNode) graph.getRandomNode();

            // choose searchers
            ISearcher[] searchers = {
                    new LRTASearcher(),
                    new aLSS_LRTASearcher()
            };

            for (ISearcher searcher : searchers) {
                long startTime = System.currentTimeMillis();
                List<Node> path = searcher.findShortestPath(start, goal);
                long runTime = System.currentTimeMillis() - startTime;
                System.out.println(searcher + ": " + runTime + " ms");
//            for (Node node : path) System.out.println(node);
            }
        }
    }
}
