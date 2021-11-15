package com.mycompany.mavenproject1;

import static com.mycompany.mavenproject1.DataOpener.Read;
import java.io.IOException;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SecondaryController implements Initializable {


    @FXML
    private Button BtnSwitchPerspective;

    @FXML
    private void HandleBtnMainMenu() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private ListView ListViewMoves;

    @FXML
    private void HandleBtnSwitchPerspective(ActionEvent event) throws Exception {
        Read();
    
    }
        @FXML
    private void HandleNextMove(ActionEvent event) throws Exception {
        
    
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Read();
            
            Parser p = new Parser();
            updateListViewMoves(p.parse(Read()));
        } catch (Exception ex) {

        }
    }
    
    
    private void updateListViewMoves(Map<String, Integer> map) {
        map.forEach((key, value) -> ListViewMoves.getItems().add( key +": "+ value));
    }

}
