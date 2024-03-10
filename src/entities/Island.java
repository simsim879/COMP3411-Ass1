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

    public List<Island> findPotentialConnections(Entity[][] gameMap, List<Island> islands) {
        for (Island island : islands) {
            findHorizontalConnections(gameMap, island, islands);
            findVerticalConnections(gameMap, island, islands);
        }
        return this.potentialConnections;
    }

    public void findHorizontalConnections(Entity[][] gameMap, Island island, List<Island> islands) {
        for (int col = island.getCol() - 1; col >= 0; col--) {
            Entity potIsland = gameMap[island.getRow()][col];
            if (potIsland instanceof Island) {
                island.potentialConnections.add((Island) potIsland);
                break;
            }
        }

        for (int col = island.getCol() + 1; col < gameMap[0].length; col++) {
            Entity potIsland = gameMap[island.getRow()][col];
            if (potIsland instanceof Island) {
                island.potentialConnections.add((Island) potIsland);
                break;
            }
        }
    }

    public void findVerticalConnections(Entity[][] gameMap, Island island, List<Island> islands) {
        for (int row = island.getRow() - 1; row >= 0; row--) {
            Entity pot_island = gameMap[row][island.getCol()];
            if (pot_island instanceof Island) {
                island.potentialConnections.add((Island)pot_island);
                break;
            }
        }
        for (int row = island.getRow() + 1; row < gameMap.length; row++) {
            Entity pot_island = gameMap[row][island.getCol()];
            if (pot_island instanceof Island) {
                island.potentialConnections.add((Island) pot_island);
                break;
            }
        }
    }

    public List<Island> getPotentialIslands() {
        return this.potentialConnections;
    }

}