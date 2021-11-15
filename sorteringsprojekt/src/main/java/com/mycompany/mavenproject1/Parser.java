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
import java.util.List;
import java.util.Map;

/**
 *
 * @author tobia
 */
public class Parser {

    public Map<String, Integer> moveCount;

    void parse(List<String> list) {
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
        

    }
    static List<String> getMoveInDescendingFreqOrder(Map<String, Integer> moveCount) {

    // Convert map to list of <String,Integer> entries
    List<Map.Entry<String, Integer>> list = 
        new ArrayList<Map.Entry<String, Integer>>(moveCount.entrySet());

    // Sort list by integer values
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return (o2.getValue()).compareTo(o1.getValue());
        }
    });

    // Populate the result into a list
    List<String> result = new ArrayList<String>();
    for (Map.Entry<String, Integer> entry : list) {
        result.add(entry.getKey());
    }
    return result;
    }
}


