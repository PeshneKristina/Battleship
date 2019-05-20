package ru.spbu.apmath.prog.battleship;


import java.util.ArrayList;

public class Ship {
    private String state;
    private int length;
    private ArrayList<Cell> decks = new ArrayList<Cell>();


    public Ship(int x, int y, String state, int length, int position) {
        this.length = length;
        this.state = state;
        for (int i = 0; i < length; i++) {
            decks.add(new Cell(x + i * ((position == 1) ? 0 : 1), y + i * ((position == 1) ? 1 : 0)));
        }
    }


    public Ship(ArrayList<Cell> decks) {
        this.state = state;
        this.length = decks.size();
        this.decks = decks;
    }


    public void checkState(Cells field) {
        int amount = 0;
        for (int i = 0; i < length; i++) {
            if (field.getStateCell(decks.get(i).getLetter(),decks.get(i).getNumber()) == "busy") {
                amount = amount + 1;
            }
        }
        if (amount == length ) {
            state = "killed";
        } else if (amount == 0) {
            state = "alive";
        } else state = "injured";
    }

    public String getState() {
        return state;
    }

    public int getLength() {
        return length;
    }

    public ArrayList<Cell> getDecks() {
        return decks;
    }

    public boolean isOutOfField(int bottom, int top) {
        for (Cell cell : decks) {
            if (cell.getLetter() < bottom || cell.getLetter() > top ||
                    cell.getNumber() < bottom || cell.getNumber() > top) {
                return true;
            }
        }
        return false;
    }


    // подумать как рисовать видимые и невидимые корабли


    public boolean isOverlayOrTouch(Ship currentShip) {
        for (Cell cell : decks)
            if (currentShip.isOverlayOrTouchCell(cell))
                return true;
        return false;
    }

    boolean isOverlayOrTouchCell(Cell currentCell) {
        for (Cell cell : decks)
            for (int dx = -1; dx < 2; dx++)
                for (int dy = -1; dy < 2; dy++)
                    if (currentCell.getLetter() == cell.getLetter() + dx &&
                            currentCell.getNumber() == cell.getNumber() + dy)
                        return true;
        return false;
    }


    public boolean isOverlayOrTouchCell1(Cell cell, Ship ship) {
        for (Cell deck : ship.getDecks()) {
            for (int dx = -1; dx < 2; dx++) {
                for (int dy = -1; dy < 2; dy++) {
                    if((cell.getLetter() == deck.getLetter() + dx) &&
                            (cell.getNumber() == deck.getNumber() + dy))
                        return true;
                }
            }
        }
        return false;

    }

    @Override
    public String toString() {
        ArrayList<String> strDecks = new ArrayList<>();
        for (Cell cell:decks) {
            strDecks.add(cell.toString());
        }
        return  strDecks.toString();
    }
}



