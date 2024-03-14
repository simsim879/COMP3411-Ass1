package src.map;

import java.util.ArrayList;
import java.util.List;

import src.Path;
import src.entities.*;

public class GameMap {
    private Entity[][] map;
    private int width;
    private int height;
    List<Island> islands = new ArrayList<>();

    public GameMap(char[][] inputMap, int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Entity[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                char element = inputMap[row][col];
                if (element == '.') {
                    map[row][col] = new Water(col,row);
                } else {
                    Island newIsland = new Island(col, row, element);
                    map[row][col] = newIsland;
                    islands.add(newIsland);
                }
            }
        }
        for (Island island : islands) {
            island.findPotentialConnections(map);
        }
        // for (Island island : islands) {
        //     System.out.println(island.display());
        // }
    }

    public void displayMap() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(map[row][col].display());
            }
            System.out.println();
        }
    }

    public void addBridges(Island island1, Island island2, int planks) {
        Path path = new Path(island1, island2);
        for (int row = path.startRow; row <= path.endRow; row++) {
            for (int col = path.startCol; col <= path.endCol; col++) {
                map[row][col] = new Bridge(row, col, planks, path.isHorizontal);
            }
        }
        island1.setBridgesNeed(-planks);
        island2.setBridgesNeed(-planks);
    }

    public void removeBridges(Island island1, Island island2) {
        Path path = new Path(island1, island2);
        int planks = 0;
        for (int row = path.startRow; row <= path.endRow; row++) {
            for (int col = path.startCol; col <= path.endCol; col++) {
                planks = ((Bridge) map[row][col]).getPlanks();
                map[row][col] = new Water(row, col);
            }
        }
        island1.setBridgesNeed(planks);
        island2.setBridgesNeed(planks);
    }

    public boolean canPlaceBridges(Island currentIsland, Island targetIsland, int planks) {

        if (planks > currentIsland.getBridgesNeed() || planks > targetIsland.getBridgesNeed()) {
            return false;
        }
        Path path = new Path(currentIsland, targetIsland);
        for (int row = path.startRow; row <= path.endRow; row++) {
            for (int col = path.startCol; col <= path.endCol; col++) {
                if (!(map[row][col] instanceof Water)) {
                    return false;
                }
            }
        }

        return true;

    }

    public Entity[][] getMap() {
        return this.map;
    }

    public List<Island> getIslandList() {
        return this.islands;
    }
}
