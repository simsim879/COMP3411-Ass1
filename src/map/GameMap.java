package src.map;

import java.util.ArrayList;
import java.util.List;

import src.entities.*;

public class GameMap {
    private Entity[][] gameMap;
    private int width;
    private int height;
    List<Island> islands = new ArrayList<>();

    public GameMap(char[][] inputMap, int width, int height) {
        this.width = width;
        this.height = height;
        this.gameMap = new Entity[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                char element = inputMap[row][col];
                if (element == '.') {
                    gameMap[row][col] = new Water(col,row);
                } else {
                    Island newIsland = new Island(col, row, element);
                    gameMap[row][col] = newIsland;
                    islands.add(newIsland);
                }
            }
        }
    }

    public void displayMap() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(gameMap[row][col].display());
            }
            System.out.println();
        }
    }

    public void addBridge(int row1, int col1, int row2, int col2, int planks, boolean isHorizontal) {
        int startCol = isHorizontal ? Math.min(col1, col2) + 1 : Math.min(col1, col2);
        int startRow = isHorizontal ? Math.min(row1, row2) : Math.min(row1, row2) + 1;
        int endCol = isHorizontal ? Math.max(col1, col2) - 1 : Math.max(col1, col2);
        int endRow = isHorizontal ? Math.max(row1, row2) : Math.max(row1, row2) - 1;
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                gameMap[row][col] = new Bridge(row, col, planks, isHorizontal);
            }
        }
    }

    public void removeBridge(int row1, int col1, int row2, int col2, boolean isHorizontal) {
        int startCol = isHorizontal ? Math.min(col1, col2) + 1 : Math.min(col1, col2);
        int startRow = isHorizontal ? Math.min(row1, row2) : Math.min(row1, row2) + 1;
        int endCol = isHorizontal ? Math.max(col1, col2) - 1 : Math.max(col1, col2);
        int endRow = isHorizontal ? Math.max(row1, row2) : Math.max(row1, row2) - 1;
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                gameMap[row][col] = new Water(row, col);
            }
        }
    }

    public Entity[][] getGameMap(GameMap map) {
        return map.gameMap;
    }

    public List<Island> getIslandList() {
        return this.islands;
    }
}
