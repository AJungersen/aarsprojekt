/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Bruger
 */
public class jSonDataImport{

    JSONParser parser = new JSONParser();
    JSONObject o;
    JSONArray array;
    ArrayList<JSONObject> list = new ArrayList<>();
    void metode() throws Exception{
        o = (JSONObject) parser.parse(new FileReader("xdTobs-rating-history.json"));
        array = (JSONArray) o.get("results");
    for (int i = 0; array.size()>i; i++){
            list.add((JSONObject) array.get(i));
    }
    System.out.println("JSondata importeret");
    }
}
