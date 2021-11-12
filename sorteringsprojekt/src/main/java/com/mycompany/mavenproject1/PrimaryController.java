package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXML;



public class PrimaryController {
@FXML
    private Button BtnSecondaryView;
@FXML
    private Button BtnSubmit;
    @FXML
    
    private TextField userNameText;
    @FXML
    private void HandleBtnSecondaryView() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    private void HandleBtnSubmit() throws IOException, Exception {
        DataOpener.Read();
    }
}
