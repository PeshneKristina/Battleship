package ru.spbu.apmath.prog.hw2.task2;

public class Converter {

    public static String convert(int number) {
        int n = number;
        String unit_str = "";
        String tens_str = "";
        String hundreds_str = "";
        String thousands_str = "";
        int unit = n % 10;
        int tens = (n / 10) % 10;
        int hundreds = (n / 100) % 10;
        int thousands = (n / 1000) % 10;

        if ((unit != 0) && (tens != 1)) {
            if (unit == 1) unit_str = "один";
            if (unit == 2) unit_str = "два";
            if (unit == 3) unit_str = "три";
            if (unit == 4) unit_str = "четыре";
            if (unit == 5) unit_str = "пять";
            if (unit == 6) unit_str = "шесть";
            if (unit == 7) unit_str = "семь";
            if (unit == 8) unit_str = "восемь";
            if (unit == 9) unit_str = "девять";
        }

        if (tens != 0) {
            if (tens == 1) {
                if (unit == 0) tens_str = "десять";
                else {
                    if (unit == 1) unit_str = "одиннадцать";
                    if (unit == 2) unit_str = "двенадцать";
                    if (unit == 3) unit_str = "тринадцать";
                    if (unit == 4) unit_str = "четырнадцать";
                    if (unit == 5) unit_str = "пятнадцать";
                    if (unit == 6) unit_str = "шестнадцать";
                    if (unit == 7) unit_str = "семнадцать";
                    if (unit == 8) unit_str = "восемнадцать";
                    if (unit == 9) unit_str = "девятнадцать";
                }
            }

            if (tens == 2) tens_str = "двадцать";
            if (tens == 3) tens_str = "тридцать";
            if (tens == 4) tens_str = "сорок";
            if (tens == 5) tens_str = "пятьдесят";
            if (tens == 6) tens_str = "шестьдесят";
            if (tens == 7) tens_str = "семьдесят";
            if (tens == 8) tens_str = "восемьдесят";
            if (tens == 9) tens_str = "девяносто";
        }

        if (hundreds != 0) {
            if (hundreds == 1) hundreds_str = "сто";
            if (hundreds == 2) hundreds_str = "двести";
            if (hundreds == 3) hundreds_str = "триста";
            if (hundreds == 4) hundreds_str = "четыреста";
            if (hundreds == 5) hundreds_str = "пятьсот";
            if (hundreds == 6) hundreds_str = "шестьсот";
            if (hundreds == 7) hundreds_str = "семьсот";
            if (hundreds == 8) hundreds_str = "восемьсот";
            if (hundreds == 9) hundreds_str = "девятьсот";
        }

        if (thousands != 0) {
            thousands_str = "тысяча";
        }

        String number_str =  thousands_str + hundreds_str + " " + tens_str + " "  + unit_str;
        return number_str;


    }
}