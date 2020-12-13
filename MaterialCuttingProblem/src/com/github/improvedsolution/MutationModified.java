package com.github.improvedsolution;

import com.github.baselinesolution.Mutation;
import com.github.materialcuttingproblem.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class MutationModified extends Mutation {


    public MutationModified(int lengthRequired, Map<Integer, Integer> offspringSolution, ArrayList<Stock> availableStockLengths) {
        super(lengthRequired, offspringSolution, availableStockLengths);
    }

    private Map<Integer, Integer> modifiedMutateOffspring(int lengthRequired, Map<Integer, Integer> offspringSolution, ArrayList<Stock> availableStockLengths) {
        Map<Integer, Integer> mutatedSolution = new LinkedHashMap<>(offspringSolution);
        Random random = new Random();
        int solutionTotal = 0;
        for (Integer stockLength : mutatedSolution.keySet()) {
            solutionTotal += mutatedSolution.get(stockLength) * stockLength;
        }
        int piecesToRemove = (int) (solutionTotal * 0.25);
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
