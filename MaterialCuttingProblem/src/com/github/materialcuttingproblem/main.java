package com.github.materialcuttingproblem;

public class main {

    public static void main(String[] args) {
        FileReaderClass fileReaderClass = new FileReaderClass("/Users/Jake/Documents/repos/MaterialCuttingProblem/MaterialCuttingProblem/csv/cutting_problem_instance2.csv");
        int ordersGenerated = 100;
        int count = 0;
        while (count < ordersGenerated) {
            OrderSolver orderSolver = new OrderSolver();
            System.out.println("Cost of operation was £" + orderSolver.solveOrder(fileReaderClass.getOrder()));
            System.out.println();
            count++;
        }


    }
}
