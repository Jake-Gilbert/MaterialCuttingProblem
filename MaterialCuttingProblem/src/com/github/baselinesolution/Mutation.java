package com.github.baselinesolution;

import com.github.materialcuttingproblem.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Mutation {

    Map<Integer, Integer> mutatedSolutions;
    public Mutation(int lengthRequired, Map<Integer, Integer> offspringSolution, ArrayList<Stock> availableStockLengths) {
        mutatedSolutions = offspringSolution;
        mutatedSolutions = mutateOffspring(lengthRequired, offspringSolution, availableStockLengths);
    }

    protected Map<Integer, Integer> addSufficientPieces(int solutionTotal, int lengthRequired, Map<Integer, Integer> mutatedSolution, ArrayList<Stock> availableStockLengths) {
        Map<Integer, Integer> newMutatedSolution = new LinkedHashMap<>(mutatedSolution);
        Random random = new Random();
        while (solutionTotal < lengthRequired) {
            int stockToAdd = availableStockLengths.get(random.nextInt(availableStockLengths.size())).getLength();
            newMutatedSolution.put(stockToAdd, newMutatedSolution.get(stockToAdd) + 1);
            solutionTotal += stockToAdd;
        }

        return newMutatedSolution;
    }

    public Map<Integer, Integer> getMutatedSolutions() {
        return mutatedSolutions;
    }

    private Map<Integer, Integer> mutateOffspring(int lengthRequired, Map<Integer, Integer> offspringSolution, ArrayList<Stock> availableStockLengths) {
        Map<Integer, Integer> mutatedSolution = new LinkedHashMap<>(offspringSolution);
        Random random = new Random();
        int solutionTotal = 0;
        for (Integer stockLength : mutatedSolution.keySet()) {
            solutionTotal += mutatedSolution.get(stockLength) * stockLength;
        }
        int piecesToRemove = 2;
        while (piecesToRemove > 0) {
            int pieceToRemove = availableStockLengths.get(random.nextInt(availableStockLengths.size())).getLength();
            if (mutatedSolution.get(pieceToRemove) > 0) {
                mutatedSolution.put(pieceToRemove, mutatedSolution.get(pieceToRemove) - 1);
                solutionTotal -= pieceToRemove;
                piecesToRemove--;
            }

        }
        if (solutionTotal < lengthRequired) {
            mutatedSolution = addSufficientPieces(solutionTotal, lengthRequired,  mutatedSolution, availableStockLengths);
        }
        return mutatedSolution;
    }
}
