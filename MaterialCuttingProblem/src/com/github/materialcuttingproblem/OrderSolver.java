package com.github.materialcuttingproblem;

import java.util.ArrayList;
import java.util.Map;

public class OrderSolver {

    public ArrayList<Order> ordersToSolve;
    public OrderSolver(ArrayList<Order> ordersToSolve) {
        this.ordersToSolve = ordersToSolve;
    }

    public void solveOrder(Order order) {
        int pieceSizesRemaining = order.getOrderLengthsAndQuantities().size();
        int[] pieceSizes = populatePieceSizes(pieceSizesRemaining, order.getOrderLengthsAndQuantities());
        int[] quantitiesForEachSize = populateSizeQuantity(pieceSizesRemaining, order.getOrderLengthsAndQuantities());
        int[] availableStocks = order.getStockLengths().clone();

        while (pieceSizesRemaining > 0) {
            //TODO
            //keep cutting
            Stock stock = new Stock(availableStocks[4]);
        }
    }

    private int[] populatePieceSizes(int pieceSizesRemaining, Map<Integer,Integer> orderLengthsAndQuantities) {
        int[] pieceSizes = new int[pieceSizesRemaining];
        int index = 0;
        for (int pieceSize : orderLengthsAndQuantities.keySet()) {
            pieceSizes[index] = pieceSize;
            index++;
        }
        return pieceSizes;
    }

    private int[] populateSizeQuantity(int pieceSizesRemaining, Map<Integer, Integer> orderLengthsAndQuantities) {
        int[] quantitiesForEachSize = new int[pieceSizesRemaining];
        for (int i = 0; i < pieceSizesRemaining; i++) {
            quantitiesForEachSize[i] = orderLengthsAndQuantities.get(i);
        }
        return quantitiesForEachSize;
    }

}
