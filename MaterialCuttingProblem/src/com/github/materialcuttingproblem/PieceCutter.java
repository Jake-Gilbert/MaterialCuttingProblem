package com.github.materialcuttingproblem;

import java.util.*;

public class PieceCutter {

     public Map<Integer, Integer> stocksUsed;
     ArrayList<Integer> piecesRemaining;
    public PieceCutter() {
        piecesRemaining = new ArrayList<>();
    }
    //
    private void cutAsManyTimesAsPossible(Stock localStock, Stock originalStock, Map<Integer, Integer> ordersAndQuantities) {
        ArrayList<Integer> orderTracker = new ArrayList<>();
        while (localStock.getLength() > 0) {
            int orderLength = getRandomOrderLength(ordersAndQuantities);
            while (orderLength <= localStock.getLength() && checkQuantitiesRemaining(orderLength, ordersAndQuantities) > 0) {
                if (orderLength != 0) {
                        if (localStock.getLength() == orderLength) {
                            orderTracker.add(orderLength);
                            localStock.setLength(localStock.getLength() - orderLength);
                            decrementQuantity(ordersAndQuantities, orderLength);
                            updateStocksUsed(originalStock);
                        }
                        else {
                            orderTracker.add(orderLength);
                            localStock.setLength(localStock.getLength() - orderLength);
                            decrementQuantity(ordersAndQuantities, orderLength);
                        }
                }

            }


        orderLength = Integer.MAX_VALUE;
            if (orderLength > localStock.getLength()) {
                int newOrderLength = checkValidSolutionsExist(localStock.getLength(), ordersAndQuantities);
                if (newOrderLength != 0) {
                    while (localStock.getLength() >= newOrderLength && checkQuantitiesRemaining(newOrderLength, ordersAndQuantities) > 0) {
                        if (localStock.getLength() == newOrderLength) {
                            orderTracker.add(newOrderLength);
                            localStock.setLength(localStock.getLength() - newOrderLength);
                            updateStocksUsed(originalStock);
                            decrementQuantity(ordersAndQuantities, newOrderLength);
                        }
                        else {
                            orderTracker.add(newOrderLength);
                            localStock.setLength(localStock.getLength() - newOrderLength);
                            decrementQuantity(ordersAndQuantities, newOrderLength);
                        }
                    }

                }
                    updateStocksUsed(originalStock);
                    localStock.setLength(0);
                    System.out.println("Stock length: " +originalStock.getLength() + " used to cut: " + orderTracker);
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


    public Map<Integer, Integer> completeAllOrders(Map<Integer, Integer> orderLengthsAndQuantities, ArrayList<Stock> availableStockLengths) {
        stocksUsed = initialiseStocksUsedMap(availableStockLengths);
        Random random = new Random();
        Map<Integer, Integer> ordersAndQuantities = new LinkedHashMap<>(orderLengthsAndQuantities);
        while (checkQuantitiesRemaining(ordersAndQuantities) > 0) {
                     Stock stockToUse = getRandomStockLength(availableStockLengths);
                    if (piecesRemaining.size() > 0) {
                        PieceRecombination pieceRecombination = new PieceRecombination();
                        Stock recombinedStock = pieceRecombination.generateFromPiecesRemaining(piecesRemaining, availableStockLengths);
                        if (recombinedStock != null) {
                            System.out.println("Recombined stock created of length " + recombinedStock.getLength());
                            stockToUse = recombinedStock;
                        }
                    }

                     Stock localStockCopy = new Stock(stockToUse.getLength());

                    //Cuts from the stock as many orders as possible

                    cutAsManyTimesAsPossible(localStockCopy, stockToUse, ordersAndQuantities);


                    }




    //System.out.println("Stock length " + stockToUse.getLength() + " has been used to cut order lengths: " + orderTracker);
        return stocksUsed;
    }


        private int checkValidSolutionsExist(int length, Map<Integer, Integer> orderLengthsAndQuantities) {
            if (checkQuantitiesRemaining(orderLengthsAndQuantities) > 0) {
                ArrayList<Integer> validOrderLengths = new ArrayList<>();
                for (int orderLength : orderLengthsAndQuantities.keySet()) {
                    if (orderLengthsAndQuantities.get(orderLength) > 0 && length >= orderLength) {
                        validOrderLengths.add(orderLength);
                    }
                }
                Random random = new Random();
                if (validOrderLengths.size() > 0) {
                    return validOrderLengths.get(random.nextInt(validOrderLengths.size()));
                }

            }
            return 0;
        }

        private int checkQuantitiesRemaining(int orderLength, Map<Integer, Integer> ordersAndQuantities) {
                if (ordersAndQuantities.containsKey(orderLength)) {
                    if (ordersAndQuantities.get(orderLength) > 0) {
                        return ordersAndQuantities.get(orderLength);
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
        if (checkQuantitiesRemaining(lengthToCut, orderLengthsAndQuantities) > 0) {
            if (orderLengthsAndQuantities.get(lengthToCut) >= 1) {
                orderLengthsAndQuantities.put(lengthToCut, orderLengthsAndQuantities.get(lengthToCut) - 1);
            }
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


