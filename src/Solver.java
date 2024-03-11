package src;

import java.util.ArrayList;
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
        if (isSolved(islands)) {
            return true;
        }
        if (islandIndex >= islands.size()) {
            return false;
        }

        Island currentIsland = islands.get(islandIndex);
        if (currentIsland.getIslandValue() == 0) {
            return solve(islandIndex + 1);
        }
        
        for (Island targetIsland : currentIsland.getPotentialIslands()) {
            // System.out.printf("%d at (%d, %d):%c target: (%d,%d) \n",islandIndex, currentIsland.getCol(), currentIsland.getRow(),currentIsland.display(),targetIsland.getCol(),targetIsland.getRow());
            for (int bridgeCount = 1; bridgeCount <= 3; bridgeCount++) {
                if (gameMap.canPlaceBridges(currentIsland, targetIsland, bridgeCount)) {
                    gameMap.addBridges(targetIsland, currentIsland, bridgeCount);
                    currentIsland.setIslandValue(currentIsland.getIslandValue() - bridgeCount);
                    targetIsland.setIslandValue(targetIsland.getIslandValue() - bridgeCount);
                    if (solve(islandIndex + 1)) {
                        return true;
                    }
                    System.out.printf("%d at (%d, %d):%c target: (%d,%d) \n",islandIndex, currentIsland.getCol(), currentIsland.getRow(),currentIsland.display(),targetIsland.getCol(),targetIsland.getRow());
                    gameMap.removeBridges(targetIsland, currentIsland);
                    currentIsland.setIslandValue(currentIsland.getIslandValue() + bridgeCount);
                    targetIsland.setIslandValue(targetIsland.getIslandValue() + bridgeCount);
                }
            }
        }
        System.out.println(islandIndex);
        return solve(islandIndex + 1);
    }


    private boolean isSolved(List<Island> islands) {
        return islands.stream().allMatch(island -> island.getIslandValue() == 0);
    }
    

    public GameMap getGameMap() {
        return gameMap;
    }
}
