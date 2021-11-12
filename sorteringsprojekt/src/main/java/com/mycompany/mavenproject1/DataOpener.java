/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnHolder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author tobia
 */
public class DataOpener {
 
    public static void Read() throws Exception
    {
 String token1;
        token1 = "";
        Scanner inFile1;
        inFile1 = new Scanner(new File("src/lichess_xdTobs_2021-11-01.pgn")).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
        List<String> temps = new ArrayList<String>();

    // while loop
    while (inFile1.hasNext()) {
      // find next line
      token1 = inFile1.next();
      temps.add(token1);
    }
    inFile1.close();

   /* String[] tempsArray = temps.toArray(new String[0]);
    for (String s : tempsArray) {
      System.out.println(s);
      
    }*/
    List<String> games = new ArrayList<String>();
   List<String> info = new ArrayList<String>();
    splitList(temps,info,games);
    
    String[] gamesArray = games.toArray(new String[0]);
    for (String s : gamesArray) {
      System.out.println(s);
    }
      String[] infoArray = info.toArray(new String[0]);
    for (String s : infoArray) {
      System.out.println(s);
    }
    
    }
    //assumes all lists initialized by caller
public static void splitList(List<String> input, List<String> current, List<String> onDeck) {
    if (input.isEmpty()) { //base case
        return;
    } else { //add the current element and call us on the rest of the list
        current.add(input.get(0));
        splitList(input.subList(1, input.size()), onDeck, current); //note toggling output list and call on rest of list, if any
    }
}
}
