package com.github.materialcuttingproblem;

import java.util.HashMap;
import java.util.Map;

public class Order {


    //order consists of n piece lengths e.g. 3
    //in associated quantities, e.g. [5, 5, 7]
    int[] requestedPieceLengths;
    int[] quantitiesForEachLength;
    int[] stockLengths;
    int[] stockCostForLengths;
    //Map that stores the different order lengths as well as the quantities for each
    Map<Integer, Integer> orderLengthsAndQuantities = new HashMap<>();
    Map<Integer, Double> stockLengthsAndCosts = new HashMap<>();
    public Order(int[] requestedPieceLengths, int[] quantities, int[] stockLengths, double[] stockCostForLengths) { ;
        for (int i = 0; i < requestedPieceLengths.length - 1; i++) {
            orderLengthsAndQuantities.put(requestedPieceLengths[i], quantities[i]);
            stockLengthsAndCosts.put(stockLengths[i], stockCostForLengths[i]);
        }
        System.out.println("Lengths requested: " + orderLengthsAndQuantities.keySet().toString());
        System.out.println("Quantities in order: " + orderLengthsAndQuantities.values().toString());
        System.out.println();
    }


    public int getQuantityOfPiece(int requestedLength) {
        for (int length : orderLengthsAndQuantities.keySet()) {
           if (length == requestedLength) {
               return orderLengthsAndQuantities.get(requestedLength);
           }
        }
        return 0;
    }

    public Map<Integer, Integer> getOrderLengthsAndQuantities() {
        return orderLengthsAndQuantities;
    }

    public int[] getStockLengths() {
        return this.stockLengths;
    }

}
