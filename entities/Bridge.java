package entities;

public class Bridge extends Entity {

    private int planks;
    private boolean isHorizontal;

    public Bridge(int x, int y, int planks, boolean isHorizontal) {
        super(x, y);
        this.planks = planks;
        this.isHorizontal = isHorizontal;
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
    public String display() {
        switch (planks) {
            case 1:
                return isHorizontal ? "-" : "|";
            case 2:
                return isHorizontal ? "=" : "\"";
            case 3:
                return isHorizontal ? "E" : "#";
            default:
                return "?";
        }
    }
}
