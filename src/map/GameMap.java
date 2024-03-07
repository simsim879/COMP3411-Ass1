package src.map;

import src.entities.*;

public class GameMap {
    private Entity[][] gameMap;
    private int width;
    private int height;

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
                    gameMap[row][col] = new Island(col, row, element);
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
        int startCol = Math.min(col1, col2);
        int startRow = Math.min(row1, row2);
        int endCol = Math.max(col1, col2);
        int endRow = Math.max(row1, row2);
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                gameMap[startRow][startCol] = new Bridge(startRow, startCol, planks, isHorizontal);
            }
        }
    }
}
