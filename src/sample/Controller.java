package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML private Button helloButton;
    @FXML private Label helloLabel;

    private int cnt;

    @FXML
    public void onHelloButtonClicked(ActionEvent event) {
        this.helloLabel.setText("clicked! : " + cnt);
        cnt++;
    }


    }
