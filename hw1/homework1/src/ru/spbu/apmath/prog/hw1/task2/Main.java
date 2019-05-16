package ru.spbu.apmath.prog.hw1.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        System.out.println("Enter number of employees");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Enter name, rate per hour, hours");
        for (int i = 1; i <= n; i++) {
            String name = scanner.next();
            float rate = scanner.nextFloat();
            int hours = scanner.nextInt();
            Employee employee = new Employee(name, rate, hours);
            employees.add(employee);
        }
        for (Employee e : employees) {
            try {
                System.out.println(e);
            } catch (IllegalArgumentException ex) {
                System.out.println(e.getName() + " " + e.getRate() + " " + e.getHours() + " " + "ошибка");
            }
        }
    }
}
