package ru.spbu.apmath.prog.battleship;

public class Shot {
    private int x, y;
    private boolean shot;

    Shot(int x, int y, boolean shot) {
        this.x = x;
        this.y = y;
        this.shot = shot;
    }

    int getX() { return x; }
    int getY() { return y; }
    boolean isShot() { return shot; }
}
