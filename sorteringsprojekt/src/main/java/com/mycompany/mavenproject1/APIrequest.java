/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author tobia
 */
public class APIrequest {

    public static boolean downloadGames(String userName) {
        String URL = "https://lichess.org/api/games/user/" + userName + "?max=20";
        String fileName = "data/lichess_" + userName + "_2021-11-28.pgn";
        boolean returnVal = true;
        try (BufferedInputStream in = new BufferedInputStream(new URL(URL).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);

            }
        } catch (IOException e) {
            returnVal = false;
        }
        return returnVal;
    }

    public static List<String>getFilesUserName(String userName) {
        String[] fileNamesArray;
        List<String> fileNames = new ArrayList<String>();
        File f = new File("data/");

// This filter will only include files ending with .py
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                return name.endsWith(".pgn") && name.contains(userName);
            }
        };
        fileNamesArray = f.list(filter);
        fileNames = Arrays.asList(fileNamesArray); 
        Collections.sort(fileNames, Collections.reverseOrder());
        
        return fileNames;
    }
}
