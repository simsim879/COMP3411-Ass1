package src.entities;

import java.util.ArrayList;
import java.util.List;


public class Island extends Entity {
    private int value;

    List<Island> potentialConnections = new ArrayList<>();

    public Island(int x, int y, char value) {
        super(x,y);
        this.value = ('0' < value && '9' >= value)? (0 + value - '0') : (10 + value - 'a');
    }

    public int getIslandValue() {
        return value;
    }

    public void setIslandValue(int value) {
        this.value = value;
    }

    public char IslandValueToChar() {
        return Character.forDigit(value, 13);
    }

    @Override
    public char display() {
        return this.IslandValueToChar();
    }

    public void findPotentialConnections(Entity[][] gameMap, List<Island> islands) {
        for (Island island : islands) {
            findHorizontalConnections(gameMap, island, islands);
            findVerticalConnections(gameMap, island, islands);
        }
    }

    public void findHorizontalConnections(Entity[][] gameMap, Island island, List<Island> islands) {
        for (int col = island.getYCord() - 1; col >= 0; col--) {
            if (!(gameMap[island.getXCord()][col] instanceof Water)) {
                island.potentialConnections.add(getIslandAt(islands, island.getXCord(), col));
                break;
            }
        }
        for (int col = island.getYCord() + 1; col < gameMap[0].length; col++) {
            if (!(gameMap[island.getXCord()][col] instanceof Water)) {
                island.potentialConnections.add(getIslandAt(islands, island.getXCord(), col));
                break;
            }
        }
    }

    public void findVerticalConnections(Entity[][] gameMap, Island island, List<Island> islands) {
        for (int row = island.getXCord() - 1; row >= 0; row--) {
            if (!(gameMap[row][island.getYCord()] instanceof Water)) {
                island.potentialConnections.add(getIslandAt(islands, row, island.getYCord()));
                break;
            }
        }
        for (int row = island.getXCord() + 1; row < gameMap.length; row++) {
            if (!(gameMap[row][island.getYCord()] instanceof Water)) {
                island.potentialConnections.add(getIslandAt(islands, row, island.getYCord()));
                break;
            }
        }
    }

    public Island getIslandAt(List<Island> islands, int x, int y) {
        for (Island island : islands) {
            if (island.getXCord() == x && island.getYCord() == y) {
                return island;
            }
        }
        return null;
    }

    public List<Island> getPotentialIslands() {
        return this.potentialConnections;
    }



}