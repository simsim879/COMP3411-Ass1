package src;

import java.util.ArrayList;
import java.util.List;

import src.entities.Entity;
import src.entities.Island;
import src.entities.Water;

public class Solver {
    private Entity[][] gameMap;

    private List<Island> islands = new ArrayList<>();

    public Solver(Entity[][] gameMap, List<Island> islandList) {
        this.gameMap = gameMap;
        this.islands = islandList;
    }

    public boolean solve() {
        return solve(0);
    }

    private boolean solve(int islandIndex) {
        if (islandIndex == islands.size()) {
            return allIslandsSatisfied();
        }

        Island currentIsland = islands.get(islandIndex);
        if (currentIsland.getIslandValue() == 0) {
            return solve(islandIndex + 1);
        }

        for (Island targetIsland : currentIsland.getPotentialIslands()) {
            for (int bridgeCount = 1; bridgeCount <= 3; bridgeCount++) {
                if (canPlaceBridges(currentIsland, targetIsland, bridgeCount)) {
                    gameMap.addBridge(currentIsland.getXCord(), currentIsland.getYCord(), targetIsland.getXCord(), targetIsland.getYCord());
                    currentIsland.setIslandValue(currentIsland.getIslandValue() - bridgeCount);
                    targetIsland.setIslandValue(targetIsland.getIslandValue() - bridgeCount);

                    if (solve(islandIndex + 1)) {
                        return true;
                    }
                    gameMap.removeBridges(currentIsland.getXCord(), currentIsland.getYCord(), targetIsland.getXCord(), targetIsland.getYCord());
                    currentIsland.setIslandValue(currentIsland.getIslandValue() + bridgeCount);
                    targetIsland.setIslandValue(targetIsland.getIslandValue() + bridgeCount);
                }
            }
        }
        return false;
    }

    public boolean canPlaceBridges(Island currentIsland, Island targetIsland, int bridgeCount) {

        boolean isHorizontal = currentIsland.getYCord() == targetIsland.getYCord();
        boolean isVertical = currentIsland.getXCord() == targetIsland.getXCord();

        if (isHorizontal) {
            for (int x = Math.min(currentIsland.getXCord(), targetIsland.getXCord()) + 1; x < Math.max(currentIsland.getXCord(), targetIsland.getXCord()); x++) {
                if (!(gameMap[x][currentIsland.getXCord()] instanceof Water) || targetIsland.getIslandValue() == 0) {
                    return false;
                }
            }
        } else if(isVertical) {
            for (int y = Math.min(currentIsland.getYCord(), targetIsland.getYCord()) + 1; y < Math.max(currentIsland.getYCord(), targetIsland.getYCord()); y++) {
                if (!(gameMap[currentIsland.getXCord()][y] instanceof Water) || targetIsland.getIslandValue() == 0) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private boolean allIslandsSatisfied() {
        for (Island island : islands) {
            if (island.getIslandValue() != 0) {
                return false;
            }
        }
        return true;
    }

}
