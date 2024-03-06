public class Island extends Entity {
    private int value;

    public Island(int x, int y, char value) {
        super(x,y);
        this.value = ('0' < value && '9' >= value)? (0 + value - '0') : (10 + value - 'a');
    }

    public int getIslandValue() {
        return value;
    }

    public void setIslandValue(int value) {
        this.value = value;
    }

    public char IslandValueToChar() {
        return (char) ((value > 9)? (value - 10 + 'a') : (value - 10 + '0'));
    }

    @Override
    public String display() {
        return Integer.toString(value);
    }
}