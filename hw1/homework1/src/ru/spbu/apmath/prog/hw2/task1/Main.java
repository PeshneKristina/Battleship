package ru.spbu.apmath.prog.hw2.task1;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter sale");
        Scanner scanner = new Scanner(System.in);
        try {
            int sale = scanner.nextInt();
            SumSale s = new SumSale(sale);
            System.out.println(s.sumsale());
        } catch (InputMismatchException e) {
            System.err.println("Invalid number format!");
        }

    }
}
