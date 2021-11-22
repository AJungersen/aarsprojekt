/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 *
 * @author tobia
 */
public class Parser {

    public Map<String, Integer> moveCount;

    Map<String, Integer> parse(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String x = list.get(i);
            x = x.replaceAll("\\s(.*)", "");
            list.set(i, x);

        }
        Map<String, Integer> moveCount = new HashMap<String, Integer>();

        for (int i = 0; i < list.size(); i++) {
            String move = list.get(i).replaceAll("[^A-Za-z0-9]", " ").toLowerCase();
            Integer count = moveCount.get(move);
            if (count == null) {
                //no count registered for the move yet
                moveCount.put(move, 1);
            } else {
                moveCount.put(move, count + 1);
            }
        }
        
        
        return sortByValue(moveCount,false);
       
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    private static void printMap(Map<String, Integer> map) {
        map.forEach((key, value) -> System.out.println("Key : " + key + " Value : " + value));
    }
    

}


