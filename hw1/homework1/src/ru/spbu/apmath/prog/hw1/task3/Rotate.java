package ru.spbu.apmath.prog.hw1.task3;

import java.util.ArrayList;
import java.util.List;


public class Rotate {

    public static List<Integer> rotate(List<Integer> lst, int n) {
        List<Integer> copy = new ArrayList<>(lst);
        for (int i = 0; i < n; i++) {
            int last = copy.remove(copy.size() - 1);
            copy.add(0, last);
        }
        return copy;

    }

}