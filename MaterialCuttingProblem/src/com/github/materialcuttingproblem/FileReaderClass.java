package com.github.materialcuttingproblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReaderClass {

        Order order;
        public FileReaderClass(final String CSV) {
             order = initialiseCollection(CSV);
            System.out.println();
        }

        private Order initialiseCollection(String CSV) {
                BufferedReader csvReader = null;
                String line = "";
                ArrayList<String[]> dataInCsv = new ArrayList<>();;
                int pointer = 0;
                ArrayList<Integer> stockLengths = new ArrayList<>();
                ArrayList<Double> stockCostsForLength = new ArrayList<>();
                ArrayList<Integer>  orderLengths = new ArrayList<>();
                ArrayList<Integer> orderLengthQuantities = new ArrayList<>();
                try {
                    csvReader = new BufferedReader(new java.io.FileReader(CSV));
                    while ((line = csvReader.readLine()) != null) {
                        String[] parsedElements = line.split(",");
                        dataInCsv.add(pointer, parsedElements);
                        if (parsedElements[0].contains("Problem")) {

                            // tempMap.put(cityIndex, new City(Double.parseDouble(coordinates[1]), Double.parseDouble(coordinates[2])));
                        }
                        if (parsedElements[0].contains("Stock lengths")) {
                            stockLengths = fillIntArray(parsedElements.length, dataInCsv.get(pointer));
                        }

                        if (parsedElements[0].contains("Stock costs")) {
                            stockCostsForLength = fillDoubleArray(parsedElements.length, dataInCsv.get(pointer));
                        }

                        if (parsedElements[0].contains("Piece lengths")) {
                            orderLengths = fillIntArray(parsedElements.length, dataInCsv.get(pointer));
                        }

                        if (parsedElements[0].contains(("Quantities"))) {
                            orderLengthQuantities = fillIntArray(parsedElements.length, dataInCsv.get(pointer));
                        }
                        pointer++;
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new Order(orderLengths, orderLengthQuantities, stockLengths, stockCostsForLength);
            }

            private ArrayList<Integer> fillIntArray(int length, String[] parsedElements)
            {
                ArrayList<Integer> genericIntList = new ArrayList<>();
                String[] test = parsedElements[0].split("");
                genericIntList.add(0, Integer.parseInt(parsedElements[0].split(":")[1].trim()));
                for (int i = 1; i < length; i++) {
                    if (length - i == 1) {
                        parsedElements[length - 1] = parsedElements[length - 1].replace("\"", "");
                    }
                    genericIntList.add(i, Integer.parseInt(parsedElements[i].trim()));
                   }

                return genericIntList;
            }

            private ArrayList<Double> fillDoubleArray(int length, String[] parsedElements) {
                ArrayList<Double> genericDoubleList = new ArrayList<>();
                genericDoubleList.add(0, Double.parseDouble(parsedElements[0].split(":")[1].trim()));
                for (int i = 1; i < length; i++) {
                    if (length - i == 1) {
                        parsedElements[length - 1] = parsedElements[length - 1].replace("\"", "");
                    }
                           genericDoubleList.add(i, Double.parseDouble(parsedElements[i].trim()));
                    }
                return genericDoubleList;
            }

            public Order getOrder() {
                return order;
            }
        }


