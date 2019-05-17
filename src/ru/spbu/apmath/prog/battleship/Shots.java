package ru.spbu.apmath.prog.battleship;

import java.util.ArrayList;

public class Shots {
    private static ArrayList<Shot> shots;

    Shots() {
        shots = new ArrayList<Shot>();
    }

    void addShot(int x, int y, boolean shot) {
        shots.add(new Shot(x, y, shot));
    }

    public static boolean hitSamePlace(int x, int y) {
        for (Shot shot : shots)
            if (shot.getX() == x && shot.getY() == y && shot.isShot())
                return true;
        return false;
    }

    public static String checkShot(int x, int y, boolean bool, Ships ShipsOfAI, Shots shots) {
        shots.addShot(x,y,true);
        Cell c = new Cell(x, y);
        if (!ShipsOfAI.getShips().isEmpty()) {
            for (Ship ship : ShipsOfAI.getShips()) {
                if (ship.getDecks().indexOf(c) != -1) {
                    return "bangIcon";
                }
            }
        }
        return "crossIcon";
    }


    Shot getShot(int x, int y) {
        for (Shot shot : shots)
            if (shot.getX() == x && shot.getY() == y && (!shot.isShot()))
                return shot;
        return null;
    }
}
