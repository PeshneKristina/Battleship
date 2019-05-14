package ru.spbu.apmath.prog.project;

import java.awt.*;

public class Cell {
    private int number;
    private int letter;
    private String state;

    public Cell(int letter,int number) {
        this.number = number;
        this.letter = letter;
        this.state = "free";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cell))
            return false;
        Cell c = (Cell) obj;
        return (this.number == c.number) && (this.letter == c.letter);
    }


    public void checkState(int x,int y){
        if (number == x && letter == y){
            state = "busy";
        }
        else  state = "free";
    }

    public String getState() {
        return state;
    }

    public int getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    public void paint(Graphics g, int cellSize, Color color){
        g.setColor(color);
        g.fill3DRect(letter*cellSize +1, number*cellSize + 1, cellSize -2, cellSize - 2,true );
    }

    @Override
    public String toString() {
        String x = String.valueOf(number);
        String y = String.valueOf(letter);
        return (x + "," + y);
    }
}
