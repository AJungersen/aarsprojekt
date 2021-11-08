/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
//import org.json.simple.JSONObject;
import org.json.JSONObject;
//import org.apache.commons.io.IOUtils;
import org.apache.commons.io.IOUtils;
/**
 *
 * @author Bruger
 */
public class Ratings {
    File f;
    
        
    
    

    

    //(Files.readAllBytes(Paths.get("GitHub\\aarsprojekt\\sorteringsprojekt\\src\\xdTobs-rating-history")));
    //JSONObject j = new JSONObject(Files.readAllBytes(Paths.get("app.log")));
    Ratings() throws FileNotFoundException, IOException{
        f = new File("GitHub\\aarsprojekt\\sorteringsprojekt\\src\\xdTobs-rating-history.txt");
        if(f.exists()){
            InputStream is = new FileInputStream("GitHub\\aarsprojekt\\sorteringsprojekt\\src\\xdTobs-rating-history.txt");
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            
            JSONObject j = new JSONObject(jsonTxt);
        }
            
            

    }    
    
    void importData(){
    
    }
    
}
