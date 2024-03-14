package src;

import java.util.List;
import src.entities.Island;
import src.map.GameMap;

public class Solver {
    private GameMap gameMap;

    private List<Island> islands;

    public Solver(GameMap gameMap) {
        this.gameMap = gameMap;
        this.islands = gameMap.getIslandList();
    }
    public boolean trySolve() {
        return solve();
    }

    private boolean solve() {
        Island currentIsland = getNextIslandUsingMRV(islands);
        if (isSolved(islands)) {
            return true;
        }
        if (currentIsland == null) {
            return false;
        }

        for (Island targetIsland : currentIsland.getPotentialIslands()) {
            for (int planks = 1; planks <= 3; planks++) {
                if (gameMap.canPlaceBridges(currentIsland, targetIsland, planks)) {
                    gameMap.addBridges(targetIsland, currentIsland, planks);
                    if (solve()) {
                        return true;
                    }
                    gameMap.removeBridges(targetIsland, currentIsland, planks);
                }
            }
        }
        return false;
    }


    private boolean isSolved(List<Island> islands) {
        return islands.stream().allMatch(island -> island.getBridgesNeeded() == 0);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    private Island getNextIslandUsingMRV(List<Island> islands) {
        return islands.stream()
                .filter(island -> island.getBridgesNeeded() > 0)
                .min((island1, island2) -> 
                     Integer.compare(calculatePossibleConnections(island1), 
                                     calculatePossibleConnections(island2)))
                .orElse(null);
    }

    private int calculatePossibleConnections(Island island) {
        return (int) island.getPotentialIslands().stream()
        .filter(targetIsland -> gameMap.canPlaceBridges(island, targetIsland, 1))
        .count();
    }
}
