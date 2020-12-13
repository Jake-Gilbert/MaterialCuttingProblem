package com.github.baselinesolution;

import com.github.materialcuttingproblem.PieceCutter;
import com.github.materialcuttingproblem.PieceRecombination;
import com.github.materialcuttingproblem.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PieceCutterEvo extends PieceCutter {

    ArrayList<Integer> piecesRemaining;
    Map<Integer, ArrayList<Integer>> entireOrder;
    Solution solution;
    public PieceCutterEvo() {
        entireOrder = new LinkedHashMap<>();
        piecesRemaining = new ArrayList<>();
        solution = new Solution();
    }

    private void cutAsManyTimesAsPossible(Stock localStock, Map<Integer, Integer> ordersAndQuantities) {
        ArrayList<Integer> orderTracker = new ArrayList<>();
        Stock originalStock = new Stock(localStock.getLength());
        while (localStock.getLength() > 0) {
            int orderLength = getRandomOrderLength(ordersAndQuantities);
            while (orderLength <= localStock.getLength() && checkQuantitiesRemaining(orderLength, ordersAndQuantities) > 0) {
                if (orderLength != 0) {
                    if (localStock.getLength() == orderLength) {
                        orderTracker.add(orderLength);
                        localStock.setLength(0);
                    }
                    else {
                        orderTracker.add(orderLength);
                        localStock.setLength(localStock.getLength() - orderLength);
                    }
                    decrementQuantity(ordersAndQuantities, orderLength);

                }

            }

            if (Integer.MAX_VALUE > localStock.getLength()) {
                int newOrderLength = checkValidSolutionsExist(localStock.getLength(), ordersAndQuantities);
                if (newOrderLength != 0) {
                    while (localStock.getLength() >= newOrderLength && checkQuantitiesRemaining(newOrderLength, ordersAndQuantities) > 0) {
                        if (localStock.getLength() == newOrderLength) {
                            localStock.setLength(0);
                            orderTracker.add(newOrderLength);
                        }
                        else {
                            orderTracker.add(newOrderLength);
                            localStock.setLength(localStock.getLength() - newOrderLength);
                        }
                        decrementQuantity(ordersAndQuantities, newOrderLength);
                    }

                }
                piecesRemaining.add(localStock.getLength());
                localStock.setLength(0);
                //trackTheCuts(originalStock.getLength(), orderTracker);
                solution.addActivity(originalStock.getLength(), orderTracker);
                //System.out.println("Stock length: " +originalStock.getLength() + " used to cut: " + orderTracker);
            }
        }

    }


    public Map<Integer, Integer>  completeAllOrders(Map<Integer, Integer> orderLengthsAndQuantities, Map<Integer, Integer> stocksAndQuantities, ArrayList<Stock> availableStockLengths) {
        ArrayList<Integer> stockLengthsPassedIn = orderTheStockLengths(stocksAndQuantities);
        Map<Integer, Integer> stocksUsed = initialiseStocksUsedMap(availableStockLengths);
        int pointer = 0;
        while (checkQuantitiesRemaining(orderLengthsAndQuantities) > 0) {
            Stock stockToUse = new Stock(stockLengthsPassedIn.get(pointer));
            if (piecesRemaining.size() > 0) {
                PieceRecombination pieceRecombination = new PieceRecombination();
                Stock recombinedStock = pieceRecombination.generateFromPiecesRemaining(piecesRemaining, availableStockLengths);
                if (recombinedStock != null) {
                    //System.out.println("Recombined stock created of length " + recombinedStock.getLength());
                    stockToUse = recombinedStock;
                }
            }
            stocksUsed.put(stockToUse.getLength(), stocksUsed.get(stockToUse.getLength())  + 1);
            if (pointer < stockLengthsPassedIn.size() - 1) {
                pointer++;
            }
            cutAsManyTimesAsPossible(stockToUse, orderLengthsAndQuantities);
        }
        return stocksUsed;
    }

    private ArrayList<Integer> orderTheStockLengths(Map<Integer, Integer> stocksAndQuantities) {
        ArrayList<Integer> stocksToBePassed = new ArrayList<>();
        for (Integer stockLength : stocksAndQuantities.keySet()) {
            while (stocksAndQuantities.get(stockLength) > 0) {
                stocksToBePassed.add(stockLength);
                stocksAndQuantities.put(stockLength, stocksAndQuantities.get(stockLength) - 1);
            }
        }
        return stocksToBePassed;
    }

    public Map<Integer, ArrayList<ArrayList<Integer>>> getEntireOrder() {
        return solution.getEntireOrder();
    }



}
