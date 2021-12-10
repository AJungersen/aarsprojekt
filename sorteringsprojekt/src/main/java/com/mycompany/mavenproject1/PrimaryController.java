package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.*;
import static java.lang.Math.abs;

public class PrimaryController implements Initializable {

    private int[] currentScore = {0, 0, 0};
    int[] c;
    GraphicsContext gcc;
    @FXML
    private Button BtnSecondaryView;
    @FXML
    private Button BtnSubmit;
    @FXML
    private TextField userNameText;
    @FXML
    private TextArea bulletScore;
    @FXML
    private TextArea blitzScore;
    @FXML
    private TextArea rapidScore;
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    public void initialize(URL url, ResourceBundle rb) {
        gcc = canvas.getGraphicsContext2D();

    }

    @FXML
    private void HandleBtnSecondaryView() throws IOException, Exception {
        App.setRoot("secondary");
    }

    @FXML
    private void HandleBtnSubmit() throws IOException, Exception {
        
        /*gets username from textfield above and reads the current files stored in the data folder, to see if any files 
        match usernames. If no then it downloads the pgn and JSON files for the user from the lichess API, if yes then a 
        prompt opens and you are asked whether you wish to download the newest file, if yes then it downloads the newst 
        file, and that will be used from then on. Currently there are files stored with the username "xdTobs", but any 
        user on lichess.org, will be accepted. Beware that the macimum rate of downloading games is 20 games a second, 
        and that it might take a while for some players with a lot of games*/
        //also draws graph and shows current rating
        
        // check for existing files with matching username
        APIrequest apipgn = new APIrequest();
        APIrequest apiJSON = new APIrequestJSON();
        List<String> fileNames = (apipgn.getFilesUserName(userNameText.getText()));
        List<String> fileNamesRating = (apiJSON.getFilesUserName(userNameText.getText()));
        if (fileNames.size() == 0) {
            apipgn.downloadFile(userNameText.getText());
            apiJSON.downloadFile(userNameText.getText());
        } else {
            String fileDate = fileNames.get(0).substring(fileNames.get(0).indexOf(userNameText.getText() + "_") + userNameText.getLength() + 1, fileNames.get(0).indexOf(userNameText.getText() + "_") + userNameText.getLength() + 11);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Request Update");
            alert.setHeaderText("Last file from this user was downloaded at: " + fileDate);
            alert.setContentText("Do you want to download the most recent file? This might take a while.");
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                apipgn.downloadFile(userNameText.getText());
                apiJSON.downloadFile(userNameText.getText());
            } else {
                //do nothing
            }
        }
        //update file list now with at least one file
        fileNames = (apipgn.getFilesUserName(userNameText.getText()));
        fileNamesRating = (apiJSON.getFilesUserName(userNameText.getText()));
        App.fileName = (fileNames.get(0));
        App.fileNameRating = (fileNamesRating.get(0));
        calculateScores();
        drawGraph(2);
    }

    private void calc(int t) {//sets intarray c to contain the scores of the player in a particular category, removing punctuation
        JSONObject obj = new JSONObject(Parser.jArray().get(t).toString());
        JSONArray arr = obj.getJSONArray("points");
        String b = arr.toString();
        b = b.replaceAll("[\\p{Ps}]", "");//converts brackets(18:59 5-12-2021, Adam)
        b = b.replaceAll("[\\p{Pe}]", "");
        b = b.replaceAll(",", " ");
        c = Arrays.stream(b.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    @FXML
    private void calculateScores() {//sets scoresfields in different categories to their current scores
        for (int i = 0; i < 3; i++) {
            calc(i);
            currentScore[i] = c[c.length - 1];
            for (int j = 0; 4 * j < c.length; j++) {
                //System.out.println(c[(4*j)+3]);
            }
        }
        bulletScore.setText(Integer.toString(currentScore[0]));
        blitzScore.setText(Integer.toString(currentScore[1]));
        rapidScore.setText(Integer.toString(currentScore[2]));
    }

    @FXML
    private void drawGraph(int r) {//turns array into an array that only contains rating data. Then plots the data on canvas

        calc(r);
        double height;
        double height2;
        int[] rating = new int[c.length / 4];
        for (int y = 0; y < c.length; y++) {

            if ((y - 3) % 4 == 0) {
                rating[y / 4] = c[y];
            }
        }
        gcc.strokeText("500", 0, abs(canvas.getHeight()-((500) * (canvas.getHeight() / (2500)))));
        gcc.strokeText("1000", 0, abs(canvas.getHeight()-((1000) * (canvas.getHeight() / (2500)))));
        gcc.strokeText("1500", 0, abs(canvas.getHeight()-((1500) * (canvas.getHeight() / (2500)))));
        gcc.strokeText("2000", 0, abs(canvas.getHeight()-((2000) * (canvas.getHeight() / (2500)))));
        double dis = 764 / rating.length;
        for (int y = 0; y < rating.length-1; y++) {
            height = rating[y];
            height2 = rating[y+1];
            double x1 = y * dis;
            double y1 = abs(canvas.getHeight()-(height) * (canvas.getHeight() / (2500)));
            double x2 = (y + 1) * dis;
            double y2 = abs(canvas.getHeight()-(height2) * (canvas.getHeight() / (2500)));
            gcc.strokeLine(x1, y1, x2, y2);
        }

    }

}
