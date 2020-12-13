package com.github.improvedsolution;

import com.github.baselinesolution.Mutation;
import com.github.baselinesolution.Recombination;
import com.github.materialcuttingproblem.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class RecombinationModified extends Recombination {
    Map<Integer, Integer> offspringSolution;
    ArrayList<Stock> availableStockLengths;
    int total;
    double percentageAmount;

    public RecombinationModified(Map<Integer, Integer> cheapestSolution, Map<Integer, Integer> secondCheapest, Map<Integer, Integer> orderLengthAndQuantities, ArrayList<Stock> availableStockLengths) {
        this.percentageAmount = 0.75;
        this.availableStockLengths = availableStockLengths;
        performCrossover(cheapestSolution, secondCheapest, orderLengthAndQuantities);
        Random random = new Random();
        double mutate = random.nextDouble();
        if (mutate <= 0.3) {
            Mutation mutation = new Mutation(calculateTotalLengthRequired(orderLengthAndQuantities), this.offspringSolution, availableStockLengths);
            this.offspringSolution = mutation.getMutatedSolutions();
        }
    }





    private void fillWithStockFromParent(int initialTotal, Map<Integer, Integer> solutionToUse) {
            Map<Integer, Integer> localSolution = new LinkedHashMap<>(solutionToUse);
            Random random = new Random();
            int  amountToSubtract = (int) (initialTotal * this.percentageAmount);
            while (getTotal() >  amountToSubtract) {
                int stockLengthToAdd = availableStockLengths.get(random.nextInt(availableStockLengths.size())).getLength();
                if (checkContainsStock(stockLengthToAdd, localSolution) > 0) {
                   this.offspringSolution.put(stockLengthToAdd, this.offspringSolution.get(stockLengthToAdd) + 1);
                    localSolution.put(stockLengthToAdd, localSolution.get(stockLengthToAdd) - 1);
                    setTotal(getTotal() - stockLengthToAdd);
                }
            }
            this.percentageAmount -= 0.25;
    }

    public Map<Integer, Integer> getOffspring() {
        return this.offspringSolution;
    }

    private int getTotal() {
        return this.total;
    }


    protected Map<Integer, Integer> initialiseStockMap() {
        Map<Integer, Integer> initialisedStockMap = new LinkedHashMap<>();
        for (Stock stockLength : availableStockLengths) {
            initialisedStockMap.put(stockLength.getLength(), 0);
        }
        return initialisedStockMap;
    }

    private void performCrossover(Map<Integer, Integer> cheapest, Map<Integer, Integer> secondCheapest, Map<Integer, Integer> orderLengthAndQuantities) {
        setTotal(calculateTotalLengthRequired(orderLengthAndQuantities));
        int amountOfSplices = 0;
        int initialTotal = total;
        this.offspringSolution = initialiseStockMap();
        while (amountOfSplices < 4) {
            switch (amountOfSplices) {
                case 0: case 2:
                    fillWithStockFromParent(initialTotal, cheapest);
                    amountOfSplices++;
                    break;
                case 1: case 3:
                    fillWithStockFromParent(initialTotal,  secondCheapest);
                    amountOfSplices++;
                    break;
            }
        }
    }

    private void setTotal(int total) {
        this.total = total;
    }






}
