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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SecondaryController implements Initializable {

    @FXML
    private Canvas CanvasChess;

    private List<String> sortedgames = new ArrayList<String>();
    private List<String> games = new ArrayList<String>();
    GraphicsContext gc;
    @FXML
    private Button BtnSwitchPerspective;

    @FXML
    private Button BtnConfirmNextMove;

    @FXML
    private TextField TextFieldNextMove;

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
    private void HandleNextMove(MouseEvent event) throws Exception {//runs chess move when move pressed and shows current chess board on canvas
        String selectedMove = (String) ListViewMoves.getSelectionModel().getSelectedItem();
        selectedMove = selectedMove.substring(0, selectedMove.indexOf(":"));
        App.board.doMove(selectedMove);
        String board = App.board.toString();

        String parts[] = new String[8];
        for (int i = 0; i < 8; i++) {
            parts[i] = board.substring(i * 8 + i, i * 8 + 8 + i);
        }
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CanvasChess.getWidth(), CanvasChess.getHeight());
        gc.setFill(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            for (int u = 0; u < 8; u++) {
                String s = parts[i];
                String d = s.substring(u, u + 1);
                gc.fillText(d, 200 + u * 25, 200 + i * 25);
                //200 + u * 25, 200 + i * 25
            }
        }

        Parser p = new Parser();
        sortedgames = p.shortenList(selectedMove, sortedgames);
        sortedgames = p.shortenGame(sortedgames);
        updateListViewMoves(p.sortByValue(parse(sortedgames), false));

    }

    public void initialize(URL url, ResourceBundle rb) {//sets font and initializes varables games and sortedgames
        try {

            Parser p = new Parser();
            games = Read();
            sortedgames = Read();
            updateListViewMoves(p.parse(games));
            gc = CanvasChess.getGraphicsContext2D();
            gc.setFont(new Font("Times New Roman", 30));

            gc.setFill(Color.BLACK);

        } catch (Exception ex) {

        }
    }

    private void updateListViewMoves(Map<String, Integer> map) {//shows sorted hashmap on listview
        ListViewMoves.getItems().clear();
        map.forEach((key, value) -> ListViewMoves.getItems().add(key + ": " + value));
    }

}
