package com.github.materialcuttingproblem;

import java.util.HashMap;
import java.util.Map;

public class Order {


    //order consists of n piece lengths e.g. 3
    //in associated quantities, e.g. [5, 5, 7]
    int[] requestedPieceLengths;
    int[] quantitiesForEachLength;
    //Map that stores the different order lengths as well as the quantities for each
    Map<Integer, Integer> orderLengthsAndQuantities = new HashMap<>();
    public Order(int[] requestedPieceLengths, int[] quantities) {
        this.requestedPieceLengths = requestedPieceLengths;
        quantitiesForEachLength = quantities;
        for (int i = 0; i < requestedPieceLengths.length; i++) {
            orderLengthsAndQuantities.put(requestedPieceLengths[i], quantitiesForEachLength[i]);
        }
        System.out.println();
    }
}
