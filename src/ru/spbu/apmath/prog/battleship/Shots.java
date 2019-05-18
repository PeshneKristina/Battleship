package ru.spbu.apmath.prog.battleship;

import java.util.ArrayList;
import java.util.Random;


public class Shots {
    private ArrayList<Cell> shots;
    private Random random;
    private boolean isShot;
    Shots() {
        isShot= false;
        shots = new ArrayList<Cell>();
    }

    public boolean isShot() {
        return isShot;
    }

    public void setShot(boolean shot) {
        isShot = shot;
    }

    void addShot(Cell cell) {
        shots.add(cell);
    }

    public boolean hitSamePlace(int x, int y) {
        for (Cell shot : shots)
            if (shot.getLetter() == x && shot.getNumber() == y )
                return true;
        return false;
    }

    public Cell randomShots(){
        random = new Random();
        int x = random.nextInt(9);
        int y = random.nextInt(9);
        return new Cell(x,y);
    }

    public static String checkShot(Cell cell, Ships arrayOfShips, Shots arrayOfShots) {
        arrayOfShots.addShot(cell);
        cell.setState("busy");
        if (!arrayOfShips.getShips().isEmpty()) {
            for (Ship ship : arrayOfShips.getShips()) {
                if (ship.getDecks().indexOf(cell) != -1) {
                    ship.checkState();
                    return "bangIcon";
                }
            }
        }
        return "crossIcon";
    }

    public static boolean isShot(Cell cell){
        if (cell.getState()=="busy"){
        return true;
        }
        return false;
    }

    Cell getShot(int x, int y) {
        for (Cell shot : shots)
            if (shot.getLetter() == x && shot.getNumber() == y )
                return shot;
        return null;
    }
}
