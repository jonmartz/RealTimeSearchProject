import Models.*;
import Searchers.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // choose maps
//        String[] mapNames = {"custom", "den312d", "orz103d"};
        String[] mapNames = {"den312d", "orz103d", "orz702d", "orz900d"};

        FileWriter writer = new FileWriter("results.csv");
        writer.append("map,scenario,optimal cost,LRTA runttime,LRTA cost,aLSS-LRTA runtime,aLSS-LRTA cost\n");

        int scenarioLimit = -1;
        for (String mapName : mapNames) {
            GridGraph graph = new GridGraph("./Resources/" + mapName + ".map");
            ArrayList<Double> optimalCosts = new ArrayList<>();
            ArrayList<Node[]> scenarios = getScenarios(mapName, graph, optimalCosts);
            int scenarioId = 0;
            for (Node[] scenario : scenarios) {
                if (scenarioId == scenarioLimit)
                    break;
                double optimalCost = optimalCosts.get(scenarioId);
                scenarioId++;
                System.out.println("map = " + mapName + " scen = " + scenarioId + "/" + scenarios.size());
                writer.append(mapName+","+scenarioId+","+optimalCost);

                Node start = scenario[0];
                Node goal = scenario[1];

                ISearcher[] searchers = {
                        new LRTASearcher(),
                        new aLSS_LRTASearcher()
                };
                for (ISearcher searcher : searchers) {
                    long startTime = System.currentTimeMillis();
                    List<Node> path;
                    try {
                        path = searcher.findShortestPath(start, goal);
                        long runTime = System.currentTimeMillis() - startTime;
//                        System.out.println(searcher + " time=" + runTime + " cost="+path.size());
                        writer.append(","+runTime+","+path.size());
                    }
                    catch (OutOfMemoryError e) {
                        System.out.println(searcher+" failed");
                        writer.append(",failed,failed");
                    }
                    graph.reset();
                }
                writer.append("\n");
            }
            System.out.println();
        }
        writer.flush();
        writer.close();
    }

    private static ArrayList<Node[]> getScenarios(String mapName, GridGraph graph, ArrayList<Double> optimalCosts) throws IOException {
        File file = new File("./Resources/" + mapName + ".map.scen");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<Node[]> scenarios = new ArrayList<>();
        HashSet<Integer> buckets = new HashSet<>();
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] strings = line.trim().split("\t");
            int bucket = Integer.parseInt(strings[0]);
            if (buckets.contains(bucket))
                continue;
            buckets.add(bucket);
            int startX = Integer.parseInt(strings[4]);
            int startY = Integer.parseInt(strings[5]);
            int goalX = Integer.parseInt(strings[6]);
            int goalY = Integer.parseInt(strings[7]);
            optimalCosts.add(Double.parseDouble(strings[8]));
            Node[] nodes = {graph.getNode(startX, startY), graph.getNode(goalX, goalY)};
            scenarios.add(nodes);
        }
        return scenarios;
    }
}
