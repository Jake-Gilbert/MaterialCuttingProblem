package com.github.materialcuttingproblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReaderClass {


        public FileReaderClass(final String CSV) {
            initialiseCollection(CSV);
        }

        private Order initialiseCollection(String CSV) {
                BufferedReader csvReader = null;
                String line = "";
                ArrayList<String[]> dataInCsv = new ArrayList<>();
                int pointer = 0;
                int m = 0; //types of stock lengths
                int n =  0; //types of pieces required
                int[] stockLengths = new int[0];
                double[] stockCostsForLength = new double[0];
                int[]  orderLengths = new int[0];
                int[] orderLengthQuantities = new int[0];
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

            private int[] fillIntArray(int length, String[] parsedElements)
            {
                int[] genericIntList = new int[length - 1];
                genericIntList[0] = Integer.parseInt(parsedElements[0].split(":")[1].trim());
                for (int i = 1; i < length - 1; i++) {
                        genericIntList[i] = Integer.parseInt(parsedElements[i].trim());
                   }

                return genericIntList;
            }

            private double[] fillDoubleArray(int length, String[] parsedElements) {
                double[] genericDoubleList = new double[length -1];
                genericDoubleList[0] = Double.parseDouble(parsedElements[0].split(":")[1].trim());
                for (int i = 1; i < length - 1; i++) {
                           genericDoubleList[i] = Double.parseDouble(parsedElements[i].trim());
                    }
                return genericDoubleList;
            }


        }


