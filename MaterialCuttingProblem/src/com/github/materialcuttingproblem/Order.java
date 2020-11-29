package com.github.materialcuttingproblem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {


    //order consists of n piece lengths e.g. 3
    //in associated quantities, e.g. [5, 5, 7]
    //Map that stores the different order lengths as well as the quantities for each
    Map<Integer, Integer> orderLengthsAndQuantities;
    Map<Integer, Double> stockLengthsAndCosts;
    public Order(ArrayList<Integer> requestedPieceLengths, ArrayList<Integer> quantities, ArrayList<Integer> stockLengths, ArrayList<Double> stockCostForLengths) { ;
        orderLengthsAndQuantities = initialiseOrderLengthsAndQuantities(requestedPieceLengths, quantities);
        stockLengthsAndCosts = initialiseStockLengthsAndCosts(stockLengths, stockCostForLengths);

        System.out.println("Lengths requested: " + orderLengthsAndQuantities.keySet().toString());
        System.out.println("Quantities in order: " + orderLengthsAndQuantities.values().toString());
        System.out.println();

        System.out.println("Stock available: " + stockLengthsAndCosts.keySet().toString());
        System.out.println("Cost in order: " + stockLengthsAndCosts.values().toString());
        System.out.println();
    }

    //Takes in stock lengths and stock costs and compiles them into one map
    private Map<Integer, Double> initialiseStockLengthsAndCosts(ArrayList<Integer> stockLengths, ArrayList<Double> stockCostForLengths){

        Map<Integer, Double> stockLengthsAndCostsMap = new LinkedHashMap<>();
        for (int i =0; i < stockLengths.size(); i++) {
            stockLengthsAndCostsMap.put(stockLengths.get(i), stockCostForLengths.get(i));
        }
        return stockLengthsAndCostsMap;
    }

    private Map<Integer, Integer> initialiseOrderLengthsAndQuantities(ArrayList<Integer> requestedPieceLengths, ArrayList<Integer> quantitiesForEachLength) {
        Map<Integer, Integer> orderLengthsAndQuantities = new LinkedHashMap<>();
        for (int i = 0; i < requestedPieceLengths.size(); i++) {
            orderLengthsAndQuantities.put(requestedPieceLengths.get(i), quantitiesForEachLength.get(i));
        }
        return orderLengthsAndQuantities;
    }

    public Map<Integer, Integer> getOrderLengthsAndQuantities() {
        return this.orderLengthsAndQuantities;
    }

    public ArrayList<Integer> getOrderLengths() {
        ArrayList<Integer> stockLengths = new ArrayList<>();
        for (int orderLength : orderLengthsAndQuantities.keySet()) {
            stockLengths.add(orderLength);
        }
        return stockLengths;
    }

    public ArrayList<Integer> getQuantitiesForEachLength() {
        ArrayList<Integer> orderLengthQuantities = new ArrayList<>();
        for (int orderQuantity : orderLengthsAndQuantities.values()) {
            orderLengthQuantities.add(orderQuantity);
        }
        return orderLengthQuantities;
    }

    public ArrayList<Stock> getStockLengths() {
        ArrayList<Stock> stockLengths = new ArrayList<>();
        for (int stock : stockLengthsAndCosts.keySet()) {
            stockLengths.add(new Stock(stock));
        }
        return stockLengths;
    }

    public ArrayList<Double> getStockCosts() {
        ArrayList<Double> stockCosts = new ArrayList<>();
        for (double stockCost : stockLengthsAndCosts.values()) {
            stockCosts.add(stockCost);
        }
        return stockCosts;
    }


}
