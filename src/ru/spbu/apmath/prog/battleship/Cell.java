package ru.spbu.apmath.prog.battleship;

public class Cell {
    private int number;
    private int letter;
    private String state;

    public Cell(int letter,int number) {
        this.letter = letter;
        this.number = number;
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

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String x = String.valueOf(letter);
        String y = String.valueOf(number);
        return (x + "," + y);
    }
}
