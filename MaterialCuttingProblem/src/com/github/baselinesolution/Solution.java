package com.github.baselinesolution;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {

    private Map<Integer, ArrayList<ArrayList<Integer>>> entireOrder;

    public Solution() {
        entireOrder = new LinkedHashMap<>();
    }

    public void addActivity(int length, ArrayList<Integer> cutPieces) {
        if (!entireOrder.containsKey(length)) {
            ArrayList<ArrayList<Integer>> order = new ArrayList<>();
            order.add(cutPieces);
            entireOrder.put(length, order);
        }
        else {
            ArrayList<ArrayList<Integer>> existingOrder = entireOrder.get(length);
            existingOrder.add(cutPieces);
            entireOrder.put(length, existingOrder);
        }
    }

    public Map<Integer, ArrayList<ArrayList<Integer>>> getEntireOrder() {
        return entireOrder;
    }
}
