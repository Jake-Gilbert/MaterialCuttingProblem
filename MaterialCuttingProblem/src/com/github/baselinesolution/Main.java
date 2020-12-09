package com.github.baselinesolution;

import com.github.materialcuttingproblem.FileReaderClass;
import com.github.materialcuttingproblem.Order;
import com.github.materialcuttingproblem.Stock;

import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        FileReaderClass fileReader = new FileReaderClass("/Users/Jake/Documents/repos/MaterialCuttingProblem/MaterialCuttingProblem/csv/cutting_problem_instance2.csv");
        Order orderToUse = fileReader.getOrder();
        int numberOfGenerations = 50;
        int populationSize = 50;
//        ParentSelection parentSelection = new ParentSelection(population.solutions);
//        Recombination recombination = new Recombination(parentSelection.getCheapestSolutions().get(0), parentSelection.getCheapestSolutions().get(1), orderToUse.getOrderLengthsAndQuantities(), orderToUse.getStockLengths());
//        OrderSolverEvo orderSolverEvo = new OrderSolverEvo(orderToUse, recombination.getOffspring(), orderToUse.getOrderLengthsAndQuantities());
        simulate( numberOfGenerations,  populationSize, orderToUse, orderToUse.getOrderLengthsAndQuantities(), orderToUse.getStockLengths() );

    }

    private static ArrayList<OrderSolverEvo> generateParentPair(ArrayList<OrderSolverEvo> parents) {
        ParentSelection parentSelection = new ParentSelection(parents);
        ArrayList<OrderSolverEvo> cheapestSolutions = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            OrderSolverEvo cheapSolution = parentSelection.selectCheapestParent(10);
            cheapestSolutions.add(cheapSolution);
        }
        return cheapestSolutions;
    }

    private static void getBestOfGeneration(int currentGeneration, ArrayList<OrderSolverEvo> generation) {
        OrderSolverEvo best = generation.get(0);
        for (OrderSolverEvo orderSolverEvo : generation) {
            if (orderSolverEvo.getCost() < best.getCost()) {
                best = orderSolverEvo;
            }
        }
        System.out.println("Cheapest solution of generation: " + currentGeneration);
        best.printStockCuts();
        System.out.println("Cheapest solution costs " + best.getCost());

        System.out.println();
    }

    private static Map<Integer, Integer> generateRecombinedParents(ArrayList<OrderSolverEvo> parentPair, Map<Integer, Integer> orderLengthsAndQuantities, ArrayList<Stock> stockLengths) {
        Recombination recombination = new Recombination(parentPair.get(0).getStocksAndQuantities(), parentPair.get(1).getStocksAndQuantities(), orderLengthsAndQuantities, stockLengths);
        return recombination.getOffspring();
    }
    private static ArrayList<OrderSolverEvo> replaceParents(Order order, ArrayList<OrderSolverEvo> parents, int populationSize, Map<Integer, Integer> orderLengthsAndQuantities, ArrayList<Stock> stockLengths) {
        ArrayList<OrderSolverEvo> newPopulation = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            ArrayList<OrderSolverEvo> parentPair = generateParentPair(parents);
            generateRecombinedParents(parentPair, orderLengthsAndQuantities, stockLengths);
            Recombination recombination = new Recombination(parentPair.get(0).getStocksAndQuantities(), parentPair.get(1).getStocksAndQuantities(), orderLengthsAndQuantities, stockLengths);
            newPopulation.add(new OrderSolverEvo(order, recombination.getOffspring(), orderLengthsAndQuantities));
        }
        return newPopulation;
    }

    private static void simulate(int numberOfGenerations, int populationSize, Order order, Map<Integer, Integer> orderLengthsAndQuantities, ArrayList<Stock> stockLengths) {
        Population population = new Population(populationSize, order);
        getBestOfGeneration(0, population.solutions);
        int currentGeneration = 1;
        while (currentGeneration < numberOfGenerations) {
            ArrayList<OrderSolverEvo> newGeneration = replaceParents(order, population.solutions, populationSize, orderLengthsAndQuantities, stockLengths);
            getBestOfGeneration(currentGeneration, newGeneration);
            currentGeneration++;
        }
    }
}
