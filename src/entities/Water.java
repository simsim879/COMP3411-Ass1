package src.entities;

public class Water extends Entity {

    public Water(int x, int y) {
        super(x, y);
    }

    @Override
    public char display() {
        return ' ';
    }
}
