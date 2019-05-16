package ru.spbu.apmath.prog.hw1.task1;


public class Binary {
    private int number;

    public Binary(int number) {
        this.number = number;
    }

    public String toBinary() {
        StringBuilder sb = new StringBuilder();
        int n = Math.abs(number);
        while (n > 0) {
            sb.append(n % 2);
            n /= 2;
        }
        if (number < 0) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }
}
