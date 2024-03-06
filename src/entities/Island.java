package src.entities;

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
        return Character.forDigit(value, 13);
    }

    @Override
    public char display() {
        return this.IslandValueToChar();
    }
}