package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
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

public class PrimaryController implements Initializable {
    private int[] currentScore = {0, 0, 0};
    int[] c;
    GraphicsContext gcc;
    @FXML private Button BtnSecondaryView;
    @FXML private Button BtnSubmit;
    @FXML private TextField userNameText;
    @FXML private TextArea bulletScore;
    @FXML private TextArea blitzScore;
    @FXML private TextArea rapidScore;       
    @FXML private Canvas canvas;
    @FXML private GraphicsContext gc;

     public void initialize(URL url, ResourceBundle rb) {
     gcc = canvas.getGraphicsContext2D();
     }
    
    @FXML
    private void HandleBtnSecondaryView() throws IOException, Exception {
        App.setRoot("secondary");
    }

    @FXML
private void HandleBtnSubmit() throws IOException, Exception {
        // check for existing files with matching username
        List<String> fileNames = (APIrequest.getFilesUserName(userNameText.getText()));
        List<String> fileNamesRating = (APIrequest.getRatingFilesUserName(userNameText.getText()));
        if (fileNames.size() == 0) {
            APIrequest.downloadGames(userNameText.getText());
            APIrequest.downloadRating(userNameText.getText());
        } else {
            String fileDate = fileNames.get(0).substring(fileNames.get(0).indexOf(userNameText.getText() + "_") + userNameText.getLength() + 1, fileNames.get(0).indexOf(userNameText.getText() + "_") + userNameText.getLength() + 11);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Request Update");
            alert.setHeaderText("Last file from this user was downloaded at: " + fileDate);
            alert.setContentText("Do you want to download the most recent file?");
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");
            alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                APIrequest.downloadGames(userNameText.getText());
                APIrequest.downloadRating(userNameText.getText());
            } else {
                //do nothing
            }
        }
        //update file list now with at least one file
        fileNames = (APIrequest.getFilesUserName(userNameText.getText()));
        fileNamesRating = (APIrequest.getRatingFilesUserName(userNameText.getText()));
         App.fileName = (fileNames.get(0));
         App.fileNameRating = (fileNamesRating.get(0));
         calculateScores();
         drawGraph(2);
    }
    
    
    private void calc(int t){
        JSONObject obj = new JSONObject(Parser.jArray().get(t).toString());
            JSONArray arr = obj.getJSONArray("points");
            String b = arr.toString();
            b = b.replaceAll("[\\p{Ps}]", "");//converts brackets(18:59 5-12-2021, Adam)
            b = b.replaceAll("[\\p{Pe}]", "");
            b = b.replaceAll(",", " ");
            c = Arrays.stream(b.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
    
    
    
    @FXML
    private void calculateScores(){
        for(int i = 0; i<3;i++){
            calc(i);
            currentScore[i] = c[c.length-1];
            for(int j = 0; 4*j < c.length; j++){
                //System.out.println(c[(4*j)+3]);
            } 
        }
    bulletScore.setText(Integer.toString(currentScore[0]));
    blitzScore.setText(Integer.toString(currentScore[1]));
    rapidScore.setText(Integer.toString(currentScore[2]));
    }
    
    
    @FXML
    private void drawGraph(int r){
    calc(r);
        
    /*int minValue = c[0];
    int maxValue = c[0];
    for (int i = 1; i < c.length; i++) {
        if (c[i] < minValue) {
            minValue = c[i];
        } else if (c[i] > maxValue) {
            maxValue = c[i];
        }
    }*/
    double dis = 764/c.length/4;
    double height;
    for(int y = 1; y < c.length/4-1; y++){    
    height = c[y];
    double x1 = y*dis;
    double y1 = (c[y*4-1])/5;
    double x2 = (y+1)*dis;
    double y2 = (c[y*4])/5;
    gcc.strokeLine(x1,y1,x2,y2);
    }
    
    
                
    
        
    
        
    
    
    
                
    }
    
}
