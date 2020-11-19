package com.github.materialcuttingproblem;

import java.util.ArrayList;
import java.util.Scanner;

public class CuttingController {

    public CuttingController() {
        Scanner scanner = new Scanner(System.in);
        //printInstructions();
        ArrayList<Stock> stocks = new ArrayList<>();
        boolean running = true;
//        int[] order1 = {20,25,30};
//        int[] order1Quantities = {2, 2 ,3};
//        Order order = new Order(order1, order1Quantities);

        /*Fitness function = for each activity, work out the cost of the stock length used, k is the total number
        of activities
        Fitness = sum of i , where I belongs to set k , cost(activity (i))
        */

//        while (running) {
//            System.out.println();
//            System.out.println("Select a command");
//            switch (scanner.nextInt()) {
//                case 0:
//                    running = false;
//                    System.out.println("goodbye");
//                    break;
//                case 1:
//                    System.out.println("Specify the stock length");
//                    stocks.add(new Stock(scanner.nextInt()));
//                    break;
//                case 2:
//                    System.out.println("Specify the length to be cut");
//                    stocks.get(0).cutStock(scanner.nextInt());
//            }

    }

    private void printInstructions() {
        String instructions = "0: exits the program" + "\n" +
                "1. generates stock of a specified length" +
                "\n" + "2. cuts a specified amount from the stock";
        System.out.println(instructions);
    }
}
