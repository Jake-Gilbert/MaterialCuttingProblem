package com.github.baselinesolution;

import com.github.materialcuttingproblem.Order;
import com.github.materialcuttingproblem.OrderSolver;
import com.github.materialcuttingproblem.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderSolverEvo extends OrderSolver {
    Order order;
    Map<Integer, Integer> stocksAndQuantities;
    double cost;
    Map<Integer, ArrayList<ArrayList<Integer>>> entireOrder;

    public OrderSolverEvo(Order order, Map<Integer, Integer> stocksPassedIn,Map<Integer, Integer> ordersAndQuantities) {
        this.stocksAndQuantities = stocksPassedIn;
        this.order = order;
        cost = 0;
        Map<Integer, Integer> orderLengthsAndQuantities = new LinkedHashMap<>(order.getOrderLengthsAndQuantities());
        ArrayList<Stock> availableStock = order.getStockLengths();
        ArrayList<Double> stockCosts = order.getStockCosts();
        solveOrder(orderLengthsAndQuantities, availableStock, stockCosts);
    }

    private void solveOrder(Map<Integer, Integer> orderLengthsAndQuantities, ArrayList<Stock> availableStocks, ArrayList<Double> stockCosts) {
            PieceCutterEvo pieceCutterEvo = new PieceCutterEvo();
            Map<Integer, Integer> stocksUsed = pieceCutterEvo.completeAllOrders(orderLengthsAndQuantities, this.stocksAndQuantities, availableStocks);
            CostCalculatorEvo costCalculator = new CostCalculatorEvo(stocksUsed, stockCosts);
            stocksAndQuantities = new LinkedHashMap<>(stocksUsed);
            entireOrder =  pieceCutterEvo.getEntireOrder();
            setCostOfSolution(costCalculator.getCost());
    }

    public Map<Integer, Integer> getStocksAndQuantities() {
        return stocksAndQuantities;
    }

    public void printStockCuts() {
        for (int stockLength : entireOrder.keySet()) {
            System.out.println("Stock length: " + stockLength + " used to cut: " + entireOrder.get(stockLength).toString());
        }
    }

    private void setCostOfSolution(double cost) {
        this.cost = cost;
    }



    public double getCost() {
        return cost;
    }

}
