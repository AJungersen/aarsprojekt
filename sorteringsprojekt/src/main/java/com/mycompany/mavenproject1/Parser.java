/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import java.util.Map;

/**
 *
 * @author tobia
 */
public class Parser {

    public Map<String, Integer> wordCount;

    void parse(List<String> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            String x = list.get(i);
            x = x.replaceAll("\\s(.*)", "");
            list.set(i, x);

            String[] listArray = list.toArray(new String[0]);
            for (String s : listArray) {
                System.out.println(s);
            }

        }

    }

}
