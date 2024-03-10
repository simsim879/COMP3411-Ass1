package src;

import java.util.ArrayList;
import java.util.List;
import src.entities.Island;
import src.map.GameMap;

public class Solver {
    private GameMap gameMap;

    private List<Island> islands = new ArrayList<>();

    public Solver(GameMap gameMap) {
        this.gameMap = gameMap;
        this.islands = gameMap.getIslandList();
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

        for (Island targetIsland : currentIsland.findPotentialConnections(gameMap.getMap(), gameMap.getIslandList())) {
            for (int bridgeCount = 1; bridgeCount <= 3; bridgeCount++) {
                if (gameMap.canPlaceBridges(currentIsland, targetIsland, bridgeCount)) {
                    gameMap.addBridges(targetIsland, currentIsland, bridgeCount);
                    currentIsland.setIslandValue(currentIsland.getIslandValue() - bridgeCount);
                    targetIsland.setIslandValue(targetIsland.getIslandValue() - bridgeCount);
                    if (solve(islandIndex + 1)) {
                        return true;
                    }
                    gameMap.removeBridges(targetIsland, currentIsland);
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

    public GameMap getGameMap() {
        return gameMap;
    }
}
