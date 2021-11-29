package com.mycompany.mavenproject1;

import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnHolder;
import static com.mycompany.mavenproject1.DataOpener.Read;
import static com.mycompany.mavenproject1.Parser.parse;
import java.io.IOException;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondaryController implements Initializable {

    private List<String> sortedgames = new ArrayList<String>();
    private List<String> games = new ArrayList<String>();

    @FXML
    private Button BtnSwitchPerspective;

    @FXML
    private Button BtnConfirmNextMove;

    @FXML
    private TextField TextFieldNextMove;
    
    @FXML
    private Canvas CanvasChess;

    @FXML
    private void HandleBtnMainMenu() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void HandleBtnSwitchPerspective() throws IOException {

    }
    @FXML
    private ListView ListViewMoves;

    @FXML
    private void HandleBtnConfirmNextMove(ActionEvent event) throws Exception {

    }

    @FXML
    private void HandleNextMove(MouseEvent event) throws Exception {
        String selectedMove = (String) ListViewMoves.getSelectionModel().getSelectedItem();
        selectedMove = selectedMove.substring(0,selectedMove.indexOf(":"));
        App.board.doMove(selectedMove);
        PrintBoard.printFEN(App.board.getFen());
        Parser p = new Parser();
        sortedgames = p.shortenList(selectedMove, sortedgames);
        sortedgames = p.shortenGame(sortedgames);
        updateListViewMoves(p.sortByValue(parse(sortedgames), false));
        

    }

    public void initialize(URL url, ResourceBundle rb) {
        try {

            Parser p = new Parser();
            games = Read();
            sortedgames = Read();
            updateListViewMoves(p.parse(games));
            

            

        } catch (Exception ex) {

        }
    }

    private void updateListViewMoves(Map<String, Integer> map) {
        ListViewMoves.getItems().clear();
        map.forEach((key, value) -> ListViewMoves.getItems().add(key + ": " + value));
    }

}
