package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import src.map.*;
public class Hashi {
    public static void main(String[] args) throws IOException {
        String puzzle = new String(Files.readAllBytes(Paths.get(args[0])));
        String[] rows = puzzle.split("\n");

        int numRows = rows.length;
        int numCols = rows[0].length();

        char[][] inputMap = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                inputMap[i][j] = rows[i].charAt(j);
            }
        }

        GameMap map = new GameMap(inputMap,numCols,numRows);
        Solver solver = new Solver(map);
        solver.solve();
        solver.getGameMap().displayMap();
    }
}
