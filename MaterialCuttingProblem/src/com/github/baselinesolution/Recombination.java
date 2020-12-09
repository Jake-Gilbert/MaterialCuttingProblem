package com.github.baselinesolution;

import com.github.materialcuttingproblem.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Recombination {

    Map<Integer, Integer> offspring;
    ArrayList<Stock> availableStockLengths;
    int total;
    public Recombination(Map<Integer, Integer> cheapestSolution, Map<Integer, Integer> secondCheapest, Map<Integer, Integer> orderLengthAndQuantities, ArrayList<Stock> availableStockLengths) {
        this.availableStockLengths = availableStockLengths;
        offspring = performCrossover(cheapestSolution, secondCheapest, orderLengthAndQuantities);
    }

    private int calculateTotalLengthRequired(Map<Integer, Integer> orderLengthAndQuantity) {
        int total = 0;
        for (int order : orderLengthAndQuantity.keySet()) {
            if (orderLengthAndQuantity.get(order) > 0) {
                total += order * orderLengthAndQuantity.get(order);
            }
        }
        return total;
    }

    private Map<Integer, Integer> initialiseStockMap() {
        Map<Integer, Integer> initialisedStockMap = new LinkedHashMap<>();
        for (Stock stockLength : availableStockLengths) {
           initialisedStockMap.put(stockLength.getLength(), 0);
        }
        return initialisedStockMap;
    }

    private int checkContainsStock(int stockLength, Map<Integer, Integer> solution) {
        if (solution.get(stockLength) > 0) {
            return stockLength;
        }
        return 0;
    }

    private Map<Integer, Integer> fillWIthFirstSolutionStock(Map<Integer, Integer> firstSolution) {
        Map<Integer, Integer> offspringStocks = initialiseStockMap();
        Map<Integer, Integer> localFirstSolution = new LinkedHashMap<>(firstSolution);
        int initialTotal = total;
        Random random = new Random();

        while (total > initialTotal / 2) {
            int stockLengthToAdd = availableStockLengths.get(random.nextInt(availableStockLengths.size())).getLength();
            if (checkContainsStock(stockLengthToAdd, localFirstSolution) > 0) {
                offspringStocks.put(stockLengthToAdd, offspringStocks.get(stockLengthToAdd) + 1);
                localFirstSolution.put(stockLengthToAdd, localFirstSolution.get(stockLengthToAdd) - 1);
                total -= stockLengthToAdd;
            }
        }
        return offspringStocks;
    }

    private Map<Integer, Integer> fillWithSecondSolutionStock(Map<Integer, Integer> offspringSolution, Map<Integer, Integer> secondSolution) {
        Map<Integer, Integer> localOffspringSolution = new LinkedHashMap<>(offspringSolution);
        Map<Integer, Integer> localSecondSolution = new LinkedHashMap<>(secondSolution);
        Random random = new Random();
        while (total > 0) {
            int stockLengthToAdd = availableStockLengths.get(random.nextInt(availableStockLengths.size())).getLength();
            if (checkContainsStock(stockLengthToAdd, localSecondSolution) > 0) {
                localOffspringSolution.put(stockLengthToAdd, localOffspringSolution.get(stockLengthToAdd) + 1);
                localSecondSolution.put(stockLengthToAdd, localSecondSolution.get(stockLengthToAdd) - 1);
                total -= stockLengthToAdd;
            }
        }
        return localOffspringSolution;
    }

    public Map<Integer, Integer> getOffspring() {
        return offspring;
    }
        private Map<Integer, Integer> performCrossover(Map<Integer, Integer> cheapest, Map<Integer, Integer> secondCheapest, Map<Integer, Integer> orderLengthAndQuantities) {
            total = calculateTotalLengthRequired(orderLengthAndQuantities);
            Map<Integer, Integer>  offspringSolution = fillWIthFirstSolutionStock(cheapest);
            offspringSolution = fillWithSecondSolutionStock(offspringSolution, secondCheapest);
            //TODO : mutation and average cost per generation
            return offspringSolution;
    }

}
