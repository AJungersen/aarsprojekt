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
public class jSonDataImport {

    JSONParser parser = new JSONParser();
    JSONObject o = (JSONObject) parser.parse(new FileReader("xdTobs-rating-history.json"));
    JSONArray array = (JSONArray) o.get("results");
    ArrayList<JSONObject> list = new ArrayList<>();
    for (int i = 0; i< array.size(); i++){
            list.add((JSONObject) array.get(i));
    }
}
