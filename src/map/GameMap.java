package src.map;

import src.entities.*;

public class GameMap {
    private Entity[][] gameMap;
    private int width;
    private int height;

    public GameMap(char[][] inputMap) {
        this.width = inputMap.length;
        this.height = inputMap[0].length;
        this.gameMap = new Entity[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                char element = inputMap[col][row];
                if (element == '.') {
                    gameMap[col][row] = new Water(col,row);
                } else {
                    gameMap[col][row] = new Island(col, row, element);
                }
            }
        }
    }

    public void displayMap() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(gameMap[col][row].display());
            }
            System.out.println();
        }
    }

    public void addBridge(int col, int row, Bridge bridge) {
        if (col >= 0 && col < width && row >= 0 && row < height) {
            gameMap[col][row] = bridge;
        }
    }
}
