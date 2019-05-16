package ru.spbu.apmath.prog.hw1.task3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        List<Integer> mylist = new ArrayList<>();
        System.out.println("Enter list of elements");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Scanner sc = new Scanner(s);
        while (sc.hasNext()) {
            try {
                int i = sc.nextInt();
                mylist.add(i);
            } catch (InputMismatchException e) {
                System.err.println("Invalid number format!");
                return;
            }

        }
        if (!mylist.isEmpty()){
        System.out.println("Enter the number of shifts");
        int n = scanner.nextInt();
        System.out.println(mylist);
        List<Integer> newList = Rotate.rotate(mylist, n);
        System.out.println(newList);
        }
        System.out.println("[]");
    }
}
