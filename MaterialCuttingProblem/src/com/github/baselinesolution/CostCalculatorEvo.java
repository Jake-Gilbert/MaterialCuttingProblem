package com.github.baselinesolution;

import java.util.ArrayList;
import java.util.Map;

public class CostCalculatorEvo {

    double cost;

    public CostCalculatorEvo(Map<Integer, Integer> stockQuantitiesOriginal, ArrayList<Double> stockCosts) {
        cost = 0;
        calculateCost(stockQuantitiesOriginal, stockCosts);
    }

    private void calculateCost(Map<Integer, Integer> stockQuantities, ArrayList<Double> stockCosts) {
        int pointer = 0;
        for (int stockLength : stockQuantities.keySet()) {
            cost += stockQuantities.get(stockLength) * stockCosts.get(pointer);
            pointer++;
        }
    }

    public double getCost() {
        return cost;
    }

}

