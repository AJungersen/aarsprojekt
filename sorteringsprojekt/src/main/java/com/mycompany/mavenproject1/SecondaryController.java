package com.mycompany.mavenproject1;

import static com.mycompany.mavenproject1.DataOpener.Read;
import java.io.IOException;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


public class SecondaryController {
    @FXML
    private Button BtnSwitchPerspective;
    @FXML
    private void HandleBtnMainMenu() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void HandleBtnSwitchPerspective(ActionEvent event) throws Exception
    {
        Read();
    }
}