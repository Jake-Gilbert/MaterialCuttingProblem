package com.github.materialcuttingproblem;

import java.util.*;

public class PieceCutter {

     Map<Integer, Integer> stocksUsed;
    static ArrayList<Integer> piecesRemaining;
    public PieceCutter() {
        piecesRemaining = new ArrayList<>();
    }

    private void cutAsManyTimesAsPossible(Stock localStock, Stock originalStock, int orderLength, Map<Integer, Integer> ordersAndQuantities, ArrayList<Integer> orderTracker) {

        while (localStock.getLength() > 0) {
            if (orderTracker == null) {
                orderTracker = new ArrayList<>();
            }

            if (localStock.getLength() == orderLength && localStock.getLength() > 0) {
                orderTracker.add(orderLength);
                localStock.setLength(0);
                updateStocksUsed(originalStock);
                decrementQuantity(ordersAndQuantities, orderLength);

            } else if (localStock.getLength() > orderLength) {
                orderTracker.add(orderLength);
                localStock.setLength(localStock.getLength() - orderLength);
                decrementQuantity(ordersAndQuantities, orderLength);

            } else {
                int newValidLength = checkValidSolutionsExist(localStock.getLength(), ordersAndQuantities);
                if (newValidLength > 0) {
                    cutAsManyTimesAsPossible(localStock, originalStock, newValidLength, ordersAndQuantities, orderTracker);
                }
                else {
                    if (localStock.getLength() > 0 && newValidLength == 0) {
                        updateStocksUsed(originalStock);
                        piecesRemaining.add(localStock.getLength());
                    }

                    localStock.setLength(0);
                    System.out.println("Stock length used: " + originalStock.getLength() + " orders completed: " + orderTracker);
                }
            }
        }
    }

    private int getRandomOrderLength(Map<Integer, Integer> orderLengthsAndQuantities) {
        Random random = new Random();
        ArrayList<Integer> orderLengths = new ArrayList<>();
        for (int orderLength : orderLengthsAndQuantities.keySet()) {
            if (orderLengthsAndQuantities.get(orderLength) > 0) {
                orderLengths.add(orderLength);
            }
        }
        return orderLengths.get(random.nextInt(orderLengths.size()));
    }


    public Map<Integer, Integer> useEntireStockLength(Map<Integer, Integer> orderLengthsAndQuantities, ArrayList<Stock> availableStockLengths) {
        stocksUsed = initialiseStocksUsedMap(availableStockLengths);
        Random random = new Random();
        Map<Integer, Integer> ordersAndQuantities = new LinkedHashMap<>(orderLengthsAndQuantities);

        //keeps track of which pieces have been cut using one stock piece
        ArrayList<Integer> cuttingTracker = new ArrayList<>();
        int i = 1;
        while (checkQuantitiesRemaining(ordersAndQuantities) > 0) {
            Stock stockToUse = getRandomStockLength(availableStockLengths);
            Stock localStockCopy = new Stock(stockToUse.getLength());

            while (localStockCopy.getLength() > 0) {
                int orderLength = getRandomOrderLength(ordersAndQuantities);
                    //Cuts from the stock as many orders as possible
                ArrayList<Integer> orderTracker = new ArrayList<>();
                cutAsManyTimesAsPossible(localStockCopy, stockToUse, orderLength, ordersAndQuantities, orderTracker);

                    }

            }

        //System.out.println("Stock length " + stockToUse.getLength() + " has been used to cut order lengths: " + orderTracker);
        return stocksUsed;
    }


        private int checkValidSolutionsExist(int length, Map<Integer, Integer> orderLengthsAndQuantities) {
        if (checkQuantitiesRemaining(orderLengthsAndQuantities) <= 0){
            return 0;
        }
        else {
            for (int orderLength : orderLengthsAndQuantities.keySet()) {
                if (orderLength < length) {
                    return orderLength;
                }
            }
        }
            return 0;
        }


        private int checkQuantitiesRemaining(Map<Integer, Integer> ordersAndQuantities) {
            int count = 0;
            for (int orderLength : ordersAndQuantities.keySet()) {
                if (ordersAndQuantities.get(orderLength) > 0) {
                    count++;
                }
            }
            return count;
        }

        public Map<Integer, Integer> getStocksUsedForActivity(Map<Integer, Integer> stocksUsed) {
            for (int stockLength : this.stocksUsed.keySet()) {
                if (stocksUsed.containsKey(stockLength)) {
                    stocksUsed.put(stockLength, stocksUsed.get(stockLength) + 1);
                }
            }
            return stocksUsed;
        }

        private Map<Integer, Integer> initialiseStocksUsedMap(ArrayList<Stock> stockLengths) {
            Map<Integer, Integer> stocksUsed = new HashMap<>();
            for (Stock stock : stockLengths) {
                stocksUsed.put(stock.getLength(), 0);
            }
            return stocksUsed;
        }

        private Stock getRandomStockLength(ArrayList<Stock> stockLengths) {
                Random random = new Random();
                return stockLengths.get(random.nextInt(stockLengths.size()));
        }

        private void decrementQuantity(Map<Integer, Integer> orderLengthsAndQuantities, int lengthToCut){
            if (orderLengthsAndQuantities.get(lengthToCut) >= 1) {
                orderLengthsAndQuantities.put(lengthToCut, orderLengthsAndQuantities.get(lengthToCut) - 1);
            }
        }

        private Map<Integer, Integer> updateStocksUsed(Stock stockUsed) {
            Map<Integer, Integer> stockTracker = this.stocksUsed;
            stockTracker.put(stockUsed.getLength(), stockTracker.get(stockUsed.getLength()) + 1);
            return stockTracker;
        }

        private Map<Integer, Integer> getStocksUsed() {
        return stocksUsed;
        }
}

