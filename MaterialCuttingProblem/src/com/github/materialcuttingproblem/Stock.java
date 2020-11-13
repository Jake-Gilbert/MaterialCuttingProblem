package com.github.materialcuttingproblem;

public class Stock {

    private double length;
    private double cutLength;
    public Stock(int length) {
        this.length = length;
        cutLength = 0;
    }

    public double getLength() {
        return length;
    }

    public void cutStock(int lengthToCut) {
        if (lengthToCut <= length) {
            length -= lengthToCut;
            cutLength = lengthToCut;
            System.out.println("Stock has been cut");
            System.out.println(length + " stock remaining");
        }
    }
}
