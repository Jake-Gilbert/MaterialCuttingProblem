package com.github.improvedsolution;

import com.github.baselinesolution.Main;
import com.github.baselinesolution.OrderSolverEvo;
import com.github.baselinesolution.ParentSelection;
import com.github.baselinesolution.Population;
import com.github.materialcuttingproblem.FileReaderClass;
import com.github.materialcuttingproblem.Order;
import com.github.materialcuttingproblem.Stock;

import java.util.ArrayList;
import java.util.Map;

public class MainEvoModified extends Main {

    public static void main(String[] args) {
        FileReaderClass fileReader = new FileReaderClass("/Users/Jake/Documents/repos/MaterialCuttingProblem/MaterialCuttingProblem/csv/cutting_problem_instance2.csv");
        Order orderToUse = fileReader.getOrder();
        int numberOfGenerations = 50;
        int populationSize = 50;
        simulateModified(numberOfGenerations,  populationSize, orderToUse, orderToUse.getOrderLengthsAndQuantities(), orderToUse.getStockLengths() );

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


    protected static ArrayList<OrderSolverEvo> replaceParents(Order order, ArrayList<OrderSolverEvo> parents, int populationSize, Map<Integer, Integer> orderLengthsAndQuantities, ArrayList<Stock> stockLengths) {
        ArrayList<OrderSolverEvo> newPopulation = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            ArrayList<OrderSolverEvo> parentPair = generateParentPair(parents);
            generateRecombinedParents(parentPair, orderLengthsAndQuantities, stockLengths);
            RecombinationModified recombination = new RecombinationModified(parentPair.get(0).getStocksAndQuantities(), parentPair.get(1).getStocksAndQuantities(), orderLengthsAndQuantities, stockLengths);
            OrderSolverEvo offspring = new OrderSolverEvo(order, recombination.getOffspring(), orderLengthsAndQuantities);
            if (offspring.getCost() > parentPair.get(0).getCost()) {
                offspring = parentPair.get(0);
            }
            newPopulation.add(offspring);
        }
        return newPopulation;
    }

    private static void simulateModified(int numberOfGenerations, int populationSize, Order order, Map<Integer, Integer> orderLengthsAndQuantities, ArrayList<Stock> stockLengths) {
        Population population = new Population(populationSize, order);
        getBestOfGeneration(0, population.solutions);
        getAverageOfGeneration(0,  population.solutions);
        int currentGeneration = 1;
        ArrayList<OrderSolverEvo> newGeneration = new ArrayList<>();
        while (currentGeneration < numberOfGenerations) {
            if (currentGeneration == 1) {
                newGeneration = replaceParents(order, population.solutions, populationSize, orderLengthsAndQuantities, stockLengths);
                getBestOfGeneration(currentGeneration, newGeneration);
                getAverageOfGeneration(currentGeneration, newGeneration);
            }
            else {
                ArrayList<OrderSolverEvo> newerGeneration = replaceParents(order, newGeneration, populationSize, orderLengthsAndQuantities, stockLengths);
                getBestOfGeneration(currentGeneration, newerGeneration);
                getAverageOfGeneration(currentGeneration, newerGeneration);
            }

            currentGeneration++;
        }
    }
}

