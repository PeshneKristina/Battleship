package ru.spbu.apmath.prog.battleship;

import java.util.ArrayList;
import java.util.Random;


public class Ships {

    private final int CELL_SIZE = 40;
    private static ArrayList<Ship> ships = new ArrayList<>();
    private static ArrayList<Cell> fourDecks = new ArrayList<>();
    private static ArrayList<Cell> threeDecks1 = new ArrayList<>();
    private static ArrayList<Cell> threeDecks2 = new ArrayList<>();
    private static ArrayList<Cell> twoDecks1 = new ArrayList<>();
    private static ArrayList<Cell> twoDecks2 = new ArrayList<>();
    private static ArrayList<Cell> twoDecks3 = new ArrayList<>();
    private static ArrayList<Cell> oneDecks1 = new ArrayList<>();
    private static ArrayList<Cell> oneDecks2 = new ArrayList<>();
    private static ArrayList<Cell> oneDecks3 = new ArrayList<>();
    private static ArrayList<Cell> oneDecks4 = new ArrayList<>();
    static ArrayList<Cell> arrayOfDecks = new ArrayList<>();
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

    public static ArrayList<Cell> formShip(int x, int y, int numberOfDecks, int amountOfClick) {
        Cell deck = new Cell(x, y);
        arrayOfDecks = formArrayOfDecks(amountOfClick, numberOfDecks);
        arrayOfDecks.add(deck);
        if (arrayOfDecks.size() == numberOfDecks) {
            Ship ship = new Ship(arrayOfDecks);
            ships.add(ship);
        }
        return arrayOfDecks;
    }

    public static boolean check(int x, int y, int numberOfDecks, int amountOfClick) {
        Cell deck = new Cell(x, y);
        arrayOfDecks = formArrayOfDecks(amountOfClick, numberOfDecks);
        if (isOverlayOrTouch1(deck) || !(arrayOfDecks.size() < numberOfDecks)) {
            return false;
        }
        return true;
    }

    public static boolean isOverlayOrTouch1(Cell cell) {
        if (ships.isEmpty()) return false;
        for (Ship ship : ships) {
            if (ship.isOverlayOrTouchCell1(cell, ship)) return true;
        }
        return false;
    }

    public static ArrayList<Cell> formArrayOfDecks(int amountOfClick, int numberOfDecks) {
        if ((numberOfDecks == 4) && (amountOfClick == 1)) return fourDecks;
        if ((numberOfDecks == 3) && (amountOfClick == 1)) return threeDecks1;
        if ((numberOfDecks == 3) && (amountOfClick == 2)) return threeDecks2;
        if ((numberOfDecks == 2) && (amountOfClick == 1)) return twoDecks1;
        if ((numberOfDecks == 2) && (amountOfClick == 2)) return twoDecks2;
        if ((numberOfDecks == 2) && (amountOfClick == 3)) return twoDecks3;
        if ((numberOfDecks == 1) && (amountOfClick == 1)) return oneDecks1;
        if ((numberOfDecks == 1) && (amountOfClick == 2)) return oneDecks2;
        if ((numberOfDecks == 1) && (amountOfClick == 3)) return oneDecks3;
        return oneDecks4;
    }

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


    public static boolean checkSetInRow(int amountOfClick, int finalI, int numberOfDecks) {
        ArrayList<Cell> decks = formArrayOfDecks(amountOfClick, numberOfDecks);
        if (decks.isEmpty()) {
            return true;
        }
        if (decks.size() == 1) {
            int x = decks.get(0).getLetter();
            int y = decks.get(0).getNumber();
            if ((finalI == ((y + 1) * 10 + x)) || (finalI == ((y - 1) * 10 + x)) || (finalI == (y * 10 + x + 1)) || (finalI == (y * 10 + x - 1))) {
                return true;
            }

        }
        if (decks.size() == 2) {
            int x = decks.get(0).getLetter();
            int y = decks.get(0).getNumber();
            int x1 = decks.get(1).getLetter();
            int y1 = decks.get(1).getNumber();
            if (((x - x1) == 0) && ((y - y1) > 0)) {
                if ((finalI == ((y + 1) * 10 + x)) || (finalI == ((y1 - 1) * 10 + x))) {
                    return true;
                }
            }

            if (((x - x1) == 0) && ((y - y1) < 0)) {
                if ((finalI == ((y - 1) * 10 + x)) || (finalI == ((y1 + 1) * 10 + x))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x - x1) > 0)) {
                if ((finalI == (y * 10 + (x + 1))) || (finalI == (y * 10 + (x1 - 1)))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x - x1) < 0)) {
                if ((finalI == (y * 10 + (x - 1))) || (finalI == (y * 10 + (x1 + 1)))) {
                    return true;
                }
            }
        }
        if (decks.size() == 3) {
            int x = decks.get(0).getLetter();
            int y = decks.get(0).getNumber();
            int x1 = decks.get(1).getLetter();
            int y1 = decks.get(1).getNumber();
            int x2 = decks.get(2).getLetter();
            int y2 = decks.get(2).getNumber();
            if (((x - x1) == 0) && ((y - y2) == 2)) {
                if ((finalI == ((y + 1) * 10 + x)) || (finalI == ((y2 - 1) * 10 + x))) {
                    return true;
                }
            }

            if (((x - x1) == 0) && ((y - y2) == -2)) {
                if ((finalI == ((y - 1) * 10 + x)) || (finalI == ((y2 + 1) * 10 + x))) {
                    return true;
                }
            }

            if (((x - x1) == 0) && ((y1 - y2) == 2)) {
                if ((finalI == ((y1 + 1) * 10 + x)) || (finalI == ((y2 - 1) * 10 + x))) {
                    return true;
                }
            }

            if (((x - x1) == 0) && ((y1 - y2) == -2)) {
                if ((finalI == ((y1 - 1) * 10 + x)) || (finalI == ((y2 + 1) * 10 + x))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x - x2) == 2)) {
                if ((finalI == (y * 10 + (x + 1))) || (finalI == (y * 10 + (x2 - 1)))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x - x2) == -2)) {
                if ((finalI == (y * 10 + (x - 1))) || (finalI == (y * 10 + (x2 + 1)))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x1 - x2) == 2)) {
                if ((finalI == (y * 10 + (x1 + 1))) || (finalI == (y * 10 + (x2 - 1)))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x1 - x2) == -2)) {
                if ((finalI == (y * 10 + (x1 - 1))) || (finalI == (y * 10 + (x2 + 1)))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean thisTypeIsPlaced(int number){
       int amount = 0;
       if (fourDecks.size()==4) amount = 1;
       if (threeDecks1.size()==3 && threeDecks2.size() == 3 ) amount = 2;
       if (twoDecks1.size()==2 && twoDecks2.size() == 2 && twoDecks3.size() == 2 ) amount = 3;
       if (oneDecks1.size()==1 && oneDecks2.size() == 1 && oneDecks3.size() == 1 && oneDecks4.size() == 1 ) amount = 3;
       if (amount == number) return true;
       return false;
    }
}

