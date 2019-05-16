package ru.spbu.apmath.prog.hw1.task2;


public class Employee {
    private String name;
    private float rate;
    private int hours;

    public Employee(String name, float rate, int hours) {
        this.name = name;
        this.rate = rate;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return name + " " + rate + " " + hours + " " + getSalary();
    }

    public float getSalary() {
        if ((hours <= 40) & (rate >= 70)) {
            return rate * hours;
        }
        if ((hours <= 60) & (rate >= 70)) {
            return rate * 40 + rate * 3 * (hours - 40) / 2;
        }
        throw new IllegalArgumentException("Rate must be > 70.0 and hours must be < 60");

    }

    public String getName() {
        return name;
    }

    public float getRate() {
        return rate;
    }

    public int getHours() {
        return hours;
    }
}


















































































































































































































































































