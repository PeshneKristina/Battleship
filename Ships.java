package ru.spbu.apmath.prog.project;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static ru.spbu.apmath.prog.project.Ship.formShip;

public class Ships {

    private final int CELL_SIZE = 40;
    private ArrayList<Ship> ships = new ArrayList<>();
    private final int[] PATTERN = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    private Random random;

    public Ships(int fieldSize) {
        random = new Random();
        for (int i = 0; i < PATTERN.length; i++) {
            Ship ship;
            do {
                int x = random.nextInt(fieldSize);
                int y = random.nextInt(fieldSize);
                int position = random.nextInt(2);
                ship = new Ship(x, y, "alive", PATTERN[i], position);
            } while (ship.isOutOfField(0, fieldSize - 1) || isOverlayOrTouch(ship));
            ships.add(ship);
        }

    }

    public void addShip(int x, int y, int numberOfDecks, ArrayList<Cell> arrayOfDecks) {
            Ship ship = new Ship(arrayOfDecks);
            ships.add(ship);
            }

    public boolean check(int x, int y, int numberOfDecks, ArrayList<Cell> arrayOfDecks){
        Cell deck = new Cell(x,y);
        if (isOverlayOrTouch1(deck)) arrayOfDecks.add(deck);
        if (arrayOfDecks.size() == numberOfDecks) {
            Ship ship = new Ship(arrayOfDecks);
            ships.add(ship);
        }
    }

    boolean isOverlayOrTouch1(Cell cell) {
        for (Ship ship : ships) if (ship.isOverlayOrTouchCell1(cell, ship)) return true;
        return false;
    }

    //самостоятельное расположение кораблей

    boolean isOverlayOrTouch(Ship currentShip) {
        for (Ship ship : ships) if (ship.isOverlayOrTouch(currentShip)) return true;
        return false;
    }

    public boolean checkSurvivors() {
        for (Ship ship : ships) {
            if (ship.getState() == "alive") {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
}

