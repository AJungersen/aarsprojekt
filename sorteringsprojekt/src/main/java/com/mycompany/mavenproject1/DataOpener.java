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
import static java.nio.file.Files.list;
import static java.nio.file.Files.size;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author tobia
 */
public class DataOpener {

    public static List<String> Read() throws Exception {
        String token1;
        token1 = "";
        Scanner inFile1;
        inFile1 = new Scanner(new File("data/"+App.fileName)).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
        List<String> temps = new ArrayList<String>();
        List<String> games = new ArrayList<String>();
        List<String> info = new ArrayList<String>();
        // while loop
        while (inFile1.hasNext()) {
            // find next line
            token1 = inFile1.next();
            temps.add(token1);
        }
        inFile1.close();

        String[] tempsArray = temps.toArray(new String[0]);

        splitList(temps, info, games);

        for (int i = games.size() - 1; i >= 0; i--) {
            if (!games.get(i).contains(" 2.")) {
                games.remove(i);
                info.remove(i);
            } else {
                String s = games.get(i).trim();
                s = s.replaceAll("\\d+\\.+ ", "");
                games.set(i, s);

            }
        }
return games;
    }
    //assumes all lists initialized by caller
public static Map<String, Integer> getMap() throws Exception{
  Parser p = new Parser();  
return p.parse(Read());
}
    public static void splitList(List<String> input, List<String> current, List<String> onDeck) {
        if (input.isEmpty()) { //base case
            return;
        } else { //add the current element and call us on the rest of the list
            current.add(input.get(0));
            splitList(input.subList(1, input.size()), onDeck, current); //note toggling output list and call on rest of list, if any
        }
    }

}
