package ru.spbu.apmath.prog.hw1.task1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter number");
        Scanner scanner = new Scanner(System.in);
        try {
            int number = scanner.nextInt();
            Binary b = new Binary(number);
            System.out.println(b.toBinary());
            System.out.println(b.toBinary());
        } catch (InputMismatchException e) {
            System.err.println("Invalid number format!");
        }
        }


}
