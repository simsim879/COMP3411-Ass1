package src.entities;

import java.util.ArrayList;
import java.util.List;


public class Island extends Entity {
    private final int initialValue;
    private int bridgesNeeded;

    private final List<Island> potentialConnections = new ArrayList<>();

    public Island(int x, int y, char value) {
        super(x, y);
        this.initialValue = convertCharToInt(value);
        this.bridgesNeeded = this.initialValue;
    }

    private int convertCharToInt(char value) {
        if (value >= '0' && value <= '9') {
            return value - '0';
        } else {
            return 10 + value - 'a';
        }
    }

    public int getBridgesNeeded() {
        return bridgesNeeded;
    }

    public void adjustBridgesNeeded(int delta) {
        this.bridgesNeeded += delta;
    }

    @Override
    public char display() {
        return Character.forDigit(initialValue, 13);
    }

    public void findPotentialConnections(Entity[][] gameMap) {
        scanForPotentialConnections(gameMap, true);
        scanForPotentialConnections(gameMap, false);
    }

    private void scanForPotentialConnections(Entity[][] gameMap, boolean horizontal) {
        int[] directions = {-1, 1};
        for (int direction : directions) {
            int x = getCol(), y = getRow();
            while (true) {
                if (horizontal) x += direction; else y += direction;

                if (x < 0 || x >= gameMap[0].length || y < 0 || y >= gameMap.length) {
                    break;
                }
                Entity potentialIsland = gameMap[y][x];
                
                if (potentialIsland instanceof Island) {
                    potentialConnections.add((Island) potentialIsland);
                    break;
                }
            }
        }
    }

    public List<Island> getPotentialIslands() {
        return this.potentialConnections;
    }

}