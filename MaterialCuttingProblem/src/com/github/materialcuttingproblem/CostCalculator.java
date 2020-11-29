package com.github.materialcuttingproblem;

import java.util.ArrayList;
import java.util.Map;

public class CostCalculator {
    Map<Integer, Integer> usedStocksTracker;
    public CostCalculator(Map<Integer, Integer> usedStockTracker) {
        this.usedStocksTracker = usedStockTracker;
    }

    public double getSolution(ArrayList<Double> stockCosts){
       double cost = 0;
       int pointer = 0;
        for (int stockLength : usedStocksTracker.keySet()) {
           cost += usedStocksTracker.get(stockLength) * stockCosts.get(pointer);
       }
        return cost;
    }
}
