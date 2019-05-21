package ru.spbu.apmath.prog.battleship;

import java.util.ArrayList;
import java.util.Random;


public class Shots {
    private ArrayList<Cell> shots;
    private Random random;
    private boolean isShot;
    private ArrayList<Cell> currentShip;
    private Cell lastShot;

    Shots() {
        isShot = false;
        shots = new ArrayList<Cell>();
        currentShip = new ArrayList<Cell>();

    }

    public ArrayList<Cell> getCurrentShip() {
        return currentShip;
    }

    public void setCurrentShip(ArrayList<Cell> currentShip) {
        this.currentShip = currentShip;
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
            if (shot.getLetter() == x && shot.getNumber() == y)
                return true;
        return false;
    }

    public static boolean hitTheShip(Ships ships, Cell shot) {
        for (Ship ship : ships.getShips()) {
            if (ship.getDecks().indexOf(shot) != -1) {
                return true;
            }
        }
        return false;
    }

    public Cell randomShots() {
        random = new Random();
        int x = random.nextInt(9);
        int y = random.nextInt(9);
        return new Cell(x, y);
    }

    public ArrayList<Integer> getIndexOfButtonOfCurrentShip(Cells fieldOfGamer, Shots arrayOfShots) {
        int i = 0;
        ArrayList<Integer> indexOfButton = new ArrayList<>();
        for (Cell deck : currentShip) {
            for (int dx = -1; dx < 2; dx++)
                for (int dy = -1; dy < 2; dy++) {
                    if (fieldOfGamer.getStateCell(deck.getLetter() + dx, deck.getNumber() + dy) != "busy" && fieldOfGamer.cellInField(deck.getLetter() + dx, deck.getNumber() + dy)) {
                        i = (deck.getNumber() + dy) * 10 + deck.getLetter() + dx;
                        indexOfButton.add(i);
                        arrayOfShots.addShot(new Cell(deck.getLetter() + dx, deck.getNumber() + dy));
                    }
                }
        }
        return indexOfButton;
    }

    public static String checkShot(Cell cell, Ships arrayOfShips, Shots arrayOfShots, Cells field) {
        arrayOfShots.addShot(cell);
        field.setStateCell(cell.getLetter(), cell.getNumber());
        if (!arrayOfShips.getShips().isEmpty()) {
            for (Ship ship : arrayOfShips.getShips()) {
                if (ship.getDecks().indexOf(cell) != -1) {
                    ship.checkState(field);
                    if (ship.getState() == "killed") {
                        arrayOfShots.setCurrentShip(ship.getDecks());
                        return "bangIconAround";
                    } else {
                        return "bangIcon";
                    }
                }
            }
        }
        return "crossIcon";
    }

    public Cell getLastShot(){
        return lastShot;
    }

    public void setLastShot(Cell lastShot) {
        this.lastShot = lastShot;
    }

    public static boolean isShot(Cell cell) {
        if (cell.getState() == "busy") {
            return true;
        }
        return false;
    }

    Cell getShot(int x, int y) {
        for (Cell shot : shots)
            if (shot.getLetter() == x && shot.getNumber() == y)
                return shot;
        return null;
    }

    public ArrayList<Cell> getShots(){
        return shots;
    }

}
