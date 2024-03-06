public class Island extends Entity {
    private int value;

    public Island(int x, int y, char value) {
        super(x,y);
        this.value = value - '0';
    }

    public int getIslandValue() {
        return value;
    }

    public void setIslandValue(int value) {
        this.value = value;
    }

    public char IslandValueToChar() {
        switch (value) {
            case 10:
                return 'a';
            case 11:
                return 'b';
            case 12:
                return 'c';
        }
        return '!';
    }

    @Override
    public String display() {
        return Integer.toString(value);
    }
}