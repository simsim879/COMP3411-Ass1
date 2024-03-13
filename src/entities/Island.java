package src.entities;

import java.util.ArrayList;
import java.util.List;


public class Island extends Entity {
    private int value = 0;

    List<Island> potentialConnections = new ArrayList<>();

    public Island(int x, int y, char value) {
        super(x,y);
        this.value = ('0' < value && '9' >= value)? (0 + value - '0') : (10 + value - 'a');
    }

    public int getIslandValue() {
        return value;
    }

    public void setIslandValue(int value) {
        this.value += value;
    }

    public char IslandValueToChar() {
        return Character.forDigit(value, 13);
    }

    @Override
    public char display() {
        return this.IslandValueToChar();
    }

    public void findPotentialConnections(Entity[][] gameMap) {
            findHorizontalConnections(gameMap);
            findVerticalConnections(gameMap);
    }

    private void findHorizontalConnections(Entity[][] gameMap) {
        for (int col = this.getCol() - 1; col >= 0; col--) {
            Entity potIsland = gameMap[this.getRow()][col];
            if (potIsland instanceof Island) {
                this.potentialConnections.add((Island) potIsland);
                break;
            }
        }

        for (int col = this.getCol() + 1; col < gameMap[0].length; col++) {
            Entity potIsland = gameMap[this.getRow()][col];
            if (potIsland instanceof Island) {
                this.potentialConnections.add((Island) potIsland);
                break;
            }
        }
    }

    private void findVerticalConnections(Entity[][] gameMap) {
        for (int row = this.getRow() - 1; row >= 0; row--) {
            Entity pot_island = gameMap[row][this.getCol()];
            if (pot_island instanceof Island) {
                this.potentialConnections.add((Island)pot_island);
                break;
            }
        }
        for (int row = this.getRow() + 1; row < gameMap.length; row++) {
            Entity pot_island = gameMap[row][this.getCol()];
            if (pot_island instanceof Island) {
                this.potentialConnections.add((Island) pot_island);
                break;
            }
        }
    }

    public List<Island> getPotentialIslands() {
        return this.potentialConnections;
    }

}