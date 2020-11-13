package com.github.materialcuttingproblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReaderClass {

        public FileReaderClass(final String CSV) {
            initialiseCollection(CSV);
        }



        private void initialiseCollection(String CSV) {
                BufferedReader csvReader = null;
                String line = "";
                int Problems = 0;
                int m = 0;
                int n =  36;
                ArrayList<Integer> stockLengths = new ArrayList<>();
                try {
                    csvReader = new BufferedReader(new java.io.FileReader(CSV));
                    while ((line = csvReader.readLine()) != null) {
                        String[] parsedElements = line.split(":,");

                        if (parsedElements[0].matches("\\d+")) {
                            // tempMap.put(cityIndex, new City(Double.parseDouble(coordinates[1]), Double.parseDouble(coordinates[2])));
                        }
//                    else if (parsedElements[0].equals("Problem")) {
//
//                    }
                        else if (parsedElements[0].equals("Stock lengths:")) {
                            for (int i = 1; i < parsedElements.length; i++) {
                                stockLengths.add(Integer.parseInt(parsedElements[i]));
                            }
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


