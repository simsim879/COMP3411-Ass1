package src.entities;

public abstract class Entity {
    private int x;
    private int y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getXCord() {
        return this.x;
    }

    public int getYCord() {
        return this.y;
    }

    public void setXCord(int x) {
        this.x = x;
    }

    public void setYCord(int y) {
        this.y = y;
    }

    public abstract char display();
}