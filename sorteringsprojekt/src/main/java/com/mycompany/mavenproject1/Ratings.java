/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
//import org.json.simple.JSONObject;
import org.json.JSONObject;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Bruger
 * 
 * Ideen med denne klasse er at den skal importere dataen om ratings og plotte
 * dataen på en graf, hvor der på x-aksen skal være tiden over hvilken spillene
 * er forløbet
 */
public class Ratings {
    //File f;
    //JSONParser parser = new JSONParser();
    
    Ratings(){
    
    }
 
    //(Files.readAllBytes(Paths.get("GitHub\\aarsprojekt\\sorteringsprojekt\\src\\xdTobs-rating-history.json")));
    //JSONObject j = new JSONObject(Files.readAllBytes(Paths.get("app.log")));
    /*Ratings() throws FileNotFoundException, IOException{
        f = new File("GitHub\\aarsprojekt\\sorteringsprojekt\\src\\xdTobs-rating-history.txt");
        if(f.exists()){
            InputStream is = new FileInputStream("GitHub\\aarsprojekt\\sorteringsprojekt\\src\\xdTobs-rating-history.txt");
            String jsonTxt = IOUtils.toString(is, "UTF-8");            
            JSONObject j = new JSONObject(jsonTxt);
            System.out.println("f eksisterer");
        }
    }    */
    
    void importData(){//i'm yet to test it for errors
   /* try {
        JSONArray a = (JSONArray) parser.parse(new FileReader("GitHub\\\\aarsprojekt\\\\sorteringsprojekt\\\\src\\\\xdTobs-rating-history.json"));
        for (Object o : a)
        {
            JSONObject player = (JSONObject) o;

            String na = (String) player.get("name");
            System.out.println("Player name : " + na);

            String st = (String) player.get("points");//this one definitly shouldn't be a string, but im not sure whether it should be an array or an interger, so we'll just keep it this way for now 09:42 12-11-2021
            System.out.println("points : " + st);

            System.out.println("\n");

        }


    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        // TODO Auto-generated catch block
   */     
    }
    
}
