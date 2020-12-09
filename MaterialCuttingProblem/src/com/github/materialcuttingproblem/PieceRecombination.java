package com.github.materialcuttingproblem;

import java.util.ArrayList;
import java.util.Random;

public class PieceRecombination {

    public PieceRecombination() {
    }

//
//    private boolean checkIfCombinedMatchesStock(ArrayList<Integer> piecesRemaining, ArrayList<Stock> stockLengths) {
//        int total = 0;
//        for (int piece : piecesRemaining) {
//            total += piece;
//        }
//        for (Stock stock : stockLengths) {
//            if (total > stock.getLength()) {
//                return true;
//            }
//        }
//        return false;
//    }

    public Stock generateFromPiecesRemaining(ArrayList<Integer> piecesRemaining, ArrayList<Stock> stockLengths) {
            int totalLengthGenerated = 0;
            for (int i = 0; i < piecesRemaining.size(); i++) {
                totalLengthGenerated += piecesRemaining.get(i);
            }
            ArrayList<Integer> availableStockLengths = new ArrayList<>();
            for (Stock stock : stockLengths) {
                if (stock.getLength() < totalLengthGenerated) {
                    availableStockLengths.add(stock.getLength());
                }
            }
        Stock stockToReturn = null;
        if (availableStockLengths.size() > 0) {
                Random random = new Random();
                stockToReturn = new Stock(availableStockLengths.get(random.nextInt(availableStockLengths.size())));
                totalLengthGenerated = stockToReturn.getLength() - totalLengthGenerated;
                 for (int i = 0; i < piecesRemaining.size(); i++) {
                      piecesRemaining.set(i, 0 );
                 }
                if (totalLengthGenerated > -1) {
                    availableStockLengths.add(totalLengthGenerated);
                }

            }
            return stockToReturn;
    }
//        if (checkIfCombinedMatchesStock(piecesRemaining, stockLengths)) {
//            ArrayList<Stock> availableStocks= new ArrayList<>();
//            int totalCombinedPiece = 0;
//            for (int i = 0;  i < piecesRemaining.size();  i++) {
//                totalCombinedPiece += piecesRemaining.get(i);
//                piecesRemaining.set(i, 0);
//            }
//            for (Stock stock : stockLengths) {
//
//                if (stock.getLength() <= totalCombinedPiece) {
//                    availableStocks.add(stock);
//                }
//            }
//            Random random = new Random();
//            Stock randomAvailableStock = availableStocks.get(random.nextInt(availableStocks.size()));
//            if (totalCombinedPiece > randomAvailableStock.getLength()) {
//                piecesRemaining.add(totalCombinedPiece - randomAvailableStock.getLength());
//            }
//             return randomAvailableStock;
//        }
//        return null;
//    }
}
