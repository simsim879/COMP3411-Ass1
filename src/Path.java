package src;

import src.entities.Island;

public class Path {
    public int startCol;
    public int startRow;
    public int endCol;
    public int endRow;
    public boolean isHorizontal;

    public Path(Island island1, Island island2) {
        int col1 = island1.getCol();
        int col2 = island2.getCol();
        int row1 = island1.getRow();
        int row2 = island2.getRow();
        this.isHorizontal = (row1 == row2);

        int startCol = isHorizontal ? Math.min(col1, col2) + 1 : Math.min(col1, col2);
        int startRow = isHorizontal ? Math.min(row1, row2) : Math.min(row1, row2) + 1;
        int endCol = isHorizontal ? Math.max(col1, col2) - 1 : Math.max(col1, col2);
        int endRow = isHorizontal ? Math.max(row1, row2) : Math.max(row1, row2) - 1;

        this.startCol = startCol;
        this.startRow = startRow;
        this.endCol = endCol;
        this.endRow = endRow;
    }
}