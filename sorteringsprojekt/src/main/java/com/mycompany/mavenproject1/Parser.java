/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import static com.mycompany.mavenproject1.DataOpener.Read;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tobia
 */
public class Parser {

    public static String getFirstMove(String string) {

        string = string.replaceAll("\\s(.*)", "");

        return string;
    }


/*  public static List<Boolean> isFirstMove(String move, List<String> g){
                List<Boolean> booleang = new ArrayList<Boolean>();
    
    for (int y = g.size()-1; y >= 0; y--) {
            if (getFirstMove(g).get(y).contains(move)) {

                booleang.add(true);
            }
            else{
            booleang.add(false);
            }
        
    }
    return booleang;
    }
 */
public static Map<String, Integer> moveCount;

    public static Map<String, Integer> parse(List<String> list) {
        ArrayList<String> copyList = new ArrayList<String>(list);
        for (int i = 0; i < copyList.size(); i++) {
            String x = copyList.get(i);
            x = x.replaceAll("\\s(.*)", "");
            copyList.set(i, x);

        }
        Map<String, Integer> moveCount = new HashMap<String, Integer>();

        for (int i = 0; i < list.size(); i++) {
            String move = copyList.get(i).replaceAll("[^A-Za-z0-9]", " ");
            Integer count = moveCount.get(move);
            if (count == null) {
                moveCount.put(move, 1);
            } else {
                moveCount.put(move, count + 1);
            }
        }

        return sortByValue(moveCount, false);

    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((key, value) -> System.out.println("Key : " + key + " Value : " + value));
    }

    public static List<String> shortenList(String move, List<String> g) {

        List<String> sortedg = new ArrayList<String>();
        ArrayList<String> g2 = new ArrayList<String>(g);
        for (int y = 0;y<g.size();y++) {
            if (getFirstMove(g.get(y)).contains(move)) {
                sortedg.add(g2.get(y));
            }
        }

        return sortedg;
    }

    public static List<String> shortenGame(List<String> g) {
        ArrayList<String> copyG = new ArrayList<String>(g);
        for (int i = 0; i < copyG.size(); i++) {
            String x = copyG.get(i);
            if (x.contains(" ")){
            String[] arrtemp = x.split(" ", 2);
            
            
            copyG.set(i, arrtemp[1]);
            }
            else{
            copyG.set(i,"");
            }
        }
        return copyG;
    }
    
    public static JSONArray jArray(){
        JSONArray data = null;
        try {
            JSONParser parser = new JSONParser();
        //Use JSONObject for simple JSON and JSONArray for array of JSON.
        APIrequest apiJSON = new APIrequestJSON();
            data = (JSONArray) parser.parse(new FileReader("data/"+App.fileNameRating));//path to the JSON file.
            //String json = data.toJSONString();
            //System.out.println(json);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    return data;
    }

}
