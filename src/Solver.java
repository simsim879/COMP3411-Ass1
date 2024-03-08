package src;

import java.util.List;

import src.entities.Entity;
import src.entities.Island;

public class Solver {
    private Entity[][] gameMap;

    private List<Island> islands;

    public Solver(Entity[][] gameMap) {
        this.gameMap = gameMap;
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
                    gameMap.addBridge(currentIsland.getXCord(), currentIsland.getYCord(), targetIsland.getXCord(), targetIsland.getYCord(), bridgeCount);
                    currentIsland.setIslandValue(currentIsland.getIslandValue() - bridgeCount);
                    targetIsland.setIslandValue(targetIsland.getIslandValue() - bridgeCount);

                    if (solve(islandIndex + 1)) {
                        return true;
                    }
                    removeBridges(currentIsland, targetIsland, bridgeCount);
                    currentIsland.setIslandValue(currentIsland.getIslandValue() + bridgeCount);
                    targetIsland.setIslandValue(targetIsland.getIslandValue() + bridgeCount);
                }
            }
        }
        return false;
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
