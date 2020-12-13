package com.github.baselinesolution;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ParentSelection {

        private ArrayList<OrderSolverEvo> tournament;
        private  ArrayList<Map<Integer, Integer>> cheapestSolutions;
        private final ArrayList<OrderSolverEvo> originalPopulation;
        public ParentSelection(ArrayList<OrderSolverEvo> population) {
            cheapestSolutions = new ArrayList<>();
            originalPopulation = new ArrayList<>(population);


        }

        private ArrayList<OrderSolverEvo> initialiseTournament(int size) {
            Random random = new Random();
            ArrayList<OrderSolverEvo> tournament = new ArrayList<>();
            int tournamentSize = 0;
            while (tournamentSize < size) {
                int randomIndex = random.nextInt(originalPopulation.size());
                tournament.add(originalPopulation.get(randomIndex));
                tournamentSize++;
            }

            return tournament;
        }

        public ArrayList<Map<Integer, Integer>> getCheapestSolutions() {
            return this.cheapestSolutions;
        }

        public ArrayList<OrderSolverEvo> getOriginalPopulation() {
            return originalPopulation;
        }
        public OrderSolverEvo selectCheapestParent(int size) {
            ArrayList<OrderSolverEvo> tournament = initialiseTournament( size);
            OrderSolverEvo cheapestSolution = tournament.get(0);
            int pointer = 0;
            for (int i = 1;  i < tournament.size(); i++) {
                if (tournament.get(i).getCost() < cheapestSolution.getCost()) {
                    cheapestSolution = tournament.get(i);
                    pointer = i;
                }
            }
            tournament.remove(pointer);
          //  System.out.println("Cheapest solution selected for parent: " + cheapestSolution + " cost £" + cheapestSolution.getCost());
            return cheapestSolution;
        }

}
