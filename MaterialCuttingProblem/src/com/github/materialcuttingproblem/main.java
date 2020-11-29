package com.github.materialcuttingproblem;

public class main {

    public static void main(String[] args) {
        FileReaderClass fileReaderClass = new FileReaderClass("/Users/Jake/Documents/repos/MaterialCuttingProblem/MaterialCuttingProblem/csv/cutting_problem_instance2.csv");
        OrderSolver orderSolver = new OrderSolver();
        System.out.println("Cost of operation was £" + orderSolver.solveOrder(fileReaderClass.getAllOrders().get(0)));
        System.out.println();



        //CuttingController cuttingController = new CuttingController();
        //Order order = new Order(new int[]{10, 20, 30}, new int[]{7, 5, 2});

    }
}
