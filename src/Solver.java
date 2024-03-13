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
        // for (int i = 0; i < islands.size(); i++) {
        //     Island currentIsland = islands.get(i);
        //     System.out.println(currentIsland.display());
        // }
    }

    public boolean solve() {
        return solve(0);
    }

    private boolean solve(int islandIndex) {
        Island currentIsland = getNextIslandUsingMRV(islands);
        if (isSolved(islands)) {
            return true;
        }
        if (currentIsland == null) {
            return false;
        }

        if (currentIsland.getBridgesNeed() == 0) {
            return solve(islandIndex + 1);
        }

        for (Island targetIsland : currentIsland.getPotentialIslands()) {
            for (int bridgeCount = 1; bridgeCount <= 3; bridgeCount++) {
                if (gameMap.canPlaceBridges(currentIsland, targetIsland, bridgeCount)) {
                    gameMap.addBridges(targetIsland, currentIsland, bridgeCount);
                    System.out.printf("%d at (%d, %d):%c target: (%d, %d): %c \n",islandIndex, currentIsland.getCol(), currentIsland.getRow(),currentIsland.display(),targetIsland.getCol(),targetIsland.getRow(),currentIsland.display());
                    if (solve(islandIndex + 1)) {
                        return true;
                    }
                    gameMap.removeBridges(targetIsland, currentIsland);
                }
            }
        }
        // System.out.println(islandIndex);
        return false;
    }


    private boolean isSolved(List<Island> islands) {
        return islands.stream().allMatch(island -> island.getBridgesNeed() == 0);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Island getNextIslandUsingMRV(List<Island> islands) {
        Island mrvIsland = null;
        int minConnections = Integer.MAX_VALUE;
    
        for (Island island : islands) {
            int possibleConnections = calculatePossibleConnections(island);
            if (possibleConnections < minConnections && island.getBridgesNeed() > 0) {
                minConnections = possibleConnections;
                mrvIsland = island;
            }
        }
    
        return mrvIsland;
    }

    private int calculatePossibleConnections(Island island) {
        int connections = 0;
        // Assuming each island stores a list of potential islands it can connect to.
        for (Island targetIsland : island.getPotentialIslands()) {
            // For simplicity, assume any potential connection is valid.
            // You might need a more sophisticated check here, considering the current state of the board.
            if (gameMap.canPlaceBridges(island, targetIsland, 1)) {
                connections++;
            }
        }
        return connections;
    }
}
