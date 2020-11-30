package com.github.materialcuttingproblem;

import java.util.*;

public class OrderSolver {

    public ArrayList<Order> ordersToSolve;
    public OrderSolver() {
    }
    public double solveOrder(Order order) {
        Random random = new Random();
        int pieceSizesRemaining = order.getOrderLengthsAndQuantities().size();
        //Order lengths and quantity of each length
        ArrayList<Integer> orderLengthsRequested = order.getOrderLengths();
        ArrayList<Integer> quantitiesForEachSize = order.getQuantitiesForEachLength();
        Map<Integer, Integer> ordersAndQuantities = initialiseOrdersAndQuantities(orderLengthsRequested, quantitiesForEachSize);

        //Available stock lengths and cost of each length
        ArrayList<Stock> availableStockLengths = order.getStockLengths();
        //Stores used stock to calculate costs at the end
        Map<Integer, Integer> usedStocksTracker = initialiseStocksAndQuantities(availableStockLengths);
//        initialiseStocksUsedMap(availableStockLengths);
            int pointer = 0;
            PieceCutter pieceCutter = new PieceCutter();
            usedStocksTracker = pieceCutter.completeAllOrders(ordersAndQuantities, availableStockLengths);


        CostCalculator costCalculator = new CostCalculator(usedStocksTracker);
        //return costCalculator.getSolution; TODOg
        return costCalculator.getSolution(order.getStockCosts());
    }



    //Initialise order lengths associated with their quantities
    private Map<Integer, Integer> initialiseOrdersAndQuantities(ArrayList<Integer> orderLengthsRequested, ArrayList<Integer> orderLengthQuantities) {
        Map<Integer, Integer> orderLengthsAndQuantities = new LinkedHashMap<>();
        for (int i = 0; i < orderLengthsRequested.size(); i++) {
            orderLengthsAndQuantities.put(orderLengthsRequested.get(i), orderLengthQuantities.get(i));
        }
        return orderLengthsAndQuantities;
    }

    private Map<Integer, Integer> initialiseStocksAndQuantities(ArrayList<Stock> stockLengthsAvailable) {
        Map<Integer, Integer> stockTracker= new LinkedHashMap<>();
        for (Stock stockLength : stockLengthsAvailable) {
            stockTracker.put(stockLength.getLength(), 0);
        }
        return stockTracker;
    }

    private double getOverallCostOfSolution(Map<Stock, Integer> costCalculation) {
        double totalCost = 0;
        for (Stock stockUsed : costCalculation.keySet()) {
            totalCost += stockUsed.getLength() * costCalculation.get(stockUsed);
        }
        return totalCost;
    }




}
