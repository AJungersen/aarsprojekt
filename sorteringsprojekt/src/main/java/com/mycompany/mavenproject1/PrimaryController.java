package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import org.json.JSONArray;
import org.json.JSONObject;

public class PrimaryController {

    @FXML
    private Button BtnSecondaryView;
    @FXML
    private Button BtnSubmit;
    @FXML
    private Button test;
    @FXML
    private TextField userNameText;
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    @FXML
    private void HandleBtnSecondaryView() throws IOException, Exception {
        App.setRoot("secondary");
    }

    @FXML
    private void HandleBtnSubmit() throws IOException, Exception {
        // check for existing files with matching username
        List<String> fileNames = (APIrequest.getFilesUserName(userNameText.getText()));
        if (fileNames.size() == 0) {
            APIrequest.downloadGames(userNameText.getText());
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
            } else {
                //do nothing
            }
        }
        //update file list now with at least one file
        fileNames = (APIrequest.getFilesUserName(userNameText.getText()));
         App.fileName = (fileNames.get(0));
    }
    
    @FXML
    private void drawGraph(){
        
        for(int i = 0; i<3;i++){
            JSONObject obj = new JSONObject(Parser.jArray().get(i).toString());
            JSONArray arr = obj.getJSONArray("points");
            String b = arr.toString();
            b = b.replaceAll("[\\p{Ps}]", "");//converts brackets(18:59 5-12-2021, Adam)
            b = b.replaceAll("[\\p{Pe}]", "");
            b = b.replaceAll(",", " ");
            int[] c = Arrays.stream(b.split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < c.length; j++){
                
            }
            
            
            
        }
    }
}
