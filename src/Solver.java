package src;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import src.entities.Island;
import src.map.GameMap;

public class Solver {
    private GameMap gameMap;

    private List<Island> islands;

    public Solver(GameMap gameMap) {
        this.gameMap = gameMap;
        this.islands = gameMap.getIslandList();
        // for (int i = 0; i < islands.size(); i++) {
        //     Island currentIsland = islands.get(i);
        //     System.out.println(currentIsland.display());
        // }
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

        if (currentIsland.getBridgesNeed() == 0) {
            return solve();
        }

        for (Island targetIsland : currentIsland.getPotentialIslands()) {
            for (int bridgeCount = 1; bridgeCount <= 3; bridgeCount++) {
                if (gameMap.canPlaceBridges(currentIsland, targetIsland, bridgeCount)) {
                    gameMap.addBridges(targetIsland, currentIsland, bridgeCount);
                    if (forwardCheck() && solve()) {
                        return true;
                    }
                    gameMap.removeBridges(targetIsland, currentIsland, bridgeCount);
                }
            }
        }
        return false;
    }


    private boolean isSolved(List<Island> islands) {
        return islands.stream().allMatch(island -> island.getBridgesNeed() == 0);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Island getNextIslandUsingMRV(List<Island> islands) {
        Optional<Island> selectedIsland = islands.stream()
            .filter(island -> island.getBridgesNeed() > 0)
            .min(Comparator.comparingInt(island -> calculatePossibleConnections(island)));

        return selectedIsland.orElse(null);
    }

    private int calculatePossibleConnections(Island island) {
        long connections = island.getPotentialIslands().stream()
                .filter(targetIsland -> gameMap.canPlaceBridges(island, targetIsland, 1))
                .count();
        
        return (int) connections;
    }
    

    private boolean forwardCheck() {
        for (Island island : islands) {
            if (island.getBridgesNeed() > 0 && !canStillSolve(island)) {
                return false;
            }
        }
        return true;
    }

    private boolean canStillSolve(Island island) {
        for (Island potentialIsland : island.getPotentialIslands()) {
            for (int bridgeCount = 1; bridgeCount <= 3; bridgeCount++) {
                if (gameMap.canPlaceBridges(island, potentialIsland, bridgeCount)) {
                    return true;
                }
            }
        }
        return false;
    }

}
