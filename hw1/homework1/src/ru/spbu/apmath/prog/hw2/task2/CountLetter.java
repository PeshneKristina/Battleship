package ru.spbu.apmath.prog.hw2.task2;

import static ru.spbu.apmath.prog.hw2.task2.Converter.convert;

public class CountLetter {
    public static int countLetter() {
        int amountOfLetter = 0;
        for (int i = 1; i < 1001; i++) {
            amountOfLetter += convert(i).replaceAll(" ", "").length();
        }
        return amountOfLetter;
    }
}
