public class Bridge extends Entity {

    public enum BridgeNum {
        SINGLE, DOUBLE, TRIPLE
    }

    private BridgeNum bridgeNum;
    private boolean isHorizontal;

    public Bridge(int x, int y, BridgeNum bridgeNum, boolean isHorizontal) {
        super(x, y);
        this.bridgeNum = bridgeNum;
        this.isHorizontal = isHorizontal;
    }

    public BridgeNum getBridgeNum() {
        return bridgeNum;
    }

    public void setBridgeNum(BridgeNum bridgeNum) {
        this.bridgeNum = bridgeNum;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    @Override
    public String display() {
        switch (bridgeNum) {
            case SINGLE:
                return isHorizontal ? "-" : "|";
            case DOUBLE:
                return isHorizontal ? "=" : "\"";
            case TRIPLE:
                return isHorizontal ? "E" : "#";
            default:
                return " ";
        }
    }

}
