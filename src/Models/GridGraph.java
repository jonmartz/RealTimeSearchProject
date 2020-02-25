package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a grid graph, like the Dragon Age Origins maps
 */
public class GridGraph extends Graph {

    public int[][] intGrid; // -1 if wall, or if of the node contained by the cell
    public GridNode[][] nodeGrid; // actual pointers to all nodes

    private GridGraph(){}

    /**
     * Create a grid graph from a .map file
     * @param mapPath of the .map file
     */
    public GridGraph(String mapPath) {
        try {
            File file = new File(mapPath);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // get dimensions
            reader.readLine(); // ignore type
            String line = reader.readLine();
            int rows = Integer.parseInt(line.trim().split(" ")[1]);
            line = reader.readLine();
            int cols = Integer.parseInt(line.trim().split(" ")[1]);
            reader.readLine(); // ignore word "map"

            // if no metadata
//            String line;
//            int rows = 0;
//            int cols = 0;
//            while ((line = reader.readLine()) != null) {
//                if (cols == 0) cols = line.length();
//                rows++;
//            }
//            reader = new BufferedReader(new FileReader(file));

            nodeGrid = new GridNode[rows][cols];
            intGrid = new int[rows][cols];

            // make node grid
            int id = 0;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                if (line.charAt(0) == 's'){
                    start.setInitialH(Integer.parseInt(line.trim().split("=")[1]));
                    continue;
                }
                for (int col = 0; col < line.length(); col++){
                    char c = line.charAt(col);
                    intGrid[row][col] = -1;
                    if (c != 'T'){
                        intGrid[row][col] = id;
                        // new node
                        GridNode node = new GridNode(id++, col, row);
                        if (Character.isDigit(c))
                            node.setInitialH(Integer.parseInt(Character.toString(c)));
                        else if (c == 's')
                            start = node;
                        else if (c == 'g')
                            goal = node;
                        addNode(node);
                        nodeGrid[row][col] = node;

                        // connect node with upper and left nodes
                        if (row > 0 && nodeGrid[row-1][col] != null) node.addNeighbor(nodeGrid[row-1][col], 1);
                        if (col > 0 && nodeGrid[row][col-1] != null) node.addNeighbor(nodeGrid[row][col-1], 1);
                    }
                }
                row++;
            }
            reset();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get node with the indicated coordinates
     * @param x node's column
     * @param y node's row
     */
    public GridNode getNode(int x, int y) {
        return nodeGrid[y][x];
    }
}