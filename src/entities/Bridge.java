package src.entities;

public class Bridge extends Entity {

    private int planks;
    private boolean isHorizontal;

    public Bridge(int row, int col, int planks, boolean horizontal) {
        super(row, col);
        this.planks = planks;
        this.isHorizontal = horizontal;
    }

    public int getPlanks() {
        return planks;
    }


    public void setPlanks(int planks) {
        this.planks = planks;
    }


    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    @Override
    public char display() {
        switch (planks) {
            case 1:
                return isHorizontal ? '-' : '|';
            case 2:
                return isHorizontal ? '=' : '\"';
            case 3:
                return isHorizontal ? 'E' : '#';
            default:
                return '?';
        }
    }
}
