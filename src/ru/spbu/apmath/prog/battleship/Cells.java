package ru.spbu.apmath.prog.battleship;

import java.util.ArrayList;

public class Cells {
    private ArrayList<Cell> field;

    Cells() {
        field = new ArrayList<>();
    }

    public void addCell(int x, int y) {
        Cell cell = new Cell(x, y);
        field.add(cell);
    }

    public ArrayList<Cell> getField() {
        return field;
    }

    public String getStateCell(int x, int y) {
        for (Cell cell : field) {
            if (cell.getLetter() == x && cell.getNumber() == y) {
                return cell.getState();
            }
        }
        return "";
    }

    public void setStateCell(int x, int y) {
        for (Cell cell : field) {
            if (cell.getLetter() == x && cell.getNumber() == y) {
                cell.setState("busy");
            }
        }
    }

    public boolean cellInField(int x, int y) {
        for (Cell cell : field) {
            if (cell.getLetter() == x && cell.getNumber() == y) return true;

        }
        return false;
    }
}