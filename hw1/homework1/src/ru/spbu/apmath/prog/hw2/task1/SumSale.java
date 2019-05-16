package ru.spbu.apmath.prog.hw2.task1;

public class SumSale {
    private int sale;

    public SumSale(int sale) {
        this.sale = sale;
    }

    public int sumsale() {
        int number = sale;
        int result = 0;
        while ((result < 1) || (result > 9)) {
            if(result!=0){
                number = result;
                System.out.println(number);
                result = 0;
            }
            while (number != 0) {
                result = result + (number%10);
                System.out.println(result);
                number = number/10;
                System.out.println(number);
            }
        }
        return result;
    }

}
