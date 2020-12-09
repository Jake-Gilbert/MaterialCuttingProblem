package com.github.baselinesolution;

import com.github.materialcuttingproblem.Order;
import com.github.materialcuttingproblem.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Population {

    public ArrayList<OrderSolverEvo> solutions;
    public Population(int populationSize, Order order) {
      solutions = initialiseSolutions(populationSize, order);
    }

    private ArrayList<OrderSolverEvo> initialiseSolutions(int populationSize, Order order) {
        ArrayList<OrderSolverEvo> solutions = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Map<Integer, Integer> localOrderLengthAndQuantity = new LinkedHashMap<>(order.getOrderLengthsAndQuantities());
            Map<Integer, Integer> stockLengthQuantity = initialiseStocksForSolution(order.getStockLengths(), localOrderLengthAndQuantity);
            solutions.add(new OrderSolverEvo(order, stockLengthQuantity, localOrderLengthAndQuantity));
        }
        return solutions;
    }

    private Map<Integer, Integer> initialiseStockQuantityMap(ArrayList<Stock> availableStockLength) {
        Map<Integer, Integer> stockQuantities = new LinkedHashMap<>();
        for (Stock stock : availableStockLength) {
            stockQuantities.put(stock.getLength(), 0);
        }
        return stockQuantities;
    }

    private Map<Integer, Integer> initialiseStocksForSolution(ArrayList<Stock> availableStockLengths, Map<Integer, Integer> ordersAndQuantities) {
            int total = 0;
            for (int order : ordersAndQuantities.keySet()) {
                if (ordersAndQuantities.get(order) > 0) {
                    total += order * ordersAndQuantities.get(order);
                }
            }
            Map<Integer, Integer> stocksToUse = initialiseStockQuantityMap(availableStockLengths);
            Random random = new Random();
            while (total > 0) {
                int randomStockLength = availableStockLengths.get(random.nextInt(availableStockLengths.size())).getLength();
                if (stocksToUse.get(randomStockLength) >= 0) {
                    stocksToUse.put(randomStockLength, stocksToUse.get(randomStockLength) + 1);
                    total -= randomStockLength;
                }
            }
            return stocksToUse;
    }

}
