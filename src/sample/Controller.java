package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static javafx.application.Application.launch;

public class Controller {

    @FXML
    private Button helloButton;
    @FXML
    private Label helloLabel;
    @FXML
    private TextField textCode;


    @FXML
    public void onHelloButtonClicked(ActionEvent event) throws IOException {



        String otenkiCode = textCode.getText();

        setCode(otenkiCode);

    }

    public void setCode(String value) throws IOException {

            System.out.println("===== HTTP GET Start =====");

            try {

                URL url = new URL("http://weather.livedoor.com/forecast/webservice/json/v1?city=" + value );

                HttpURLConnection connection = null;

                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
                                StandardCharsets.UTF_8);
                             BufferedReader reader = new BufferedReader(isr)) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                        }
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("===== HTTP GET End =====");
        }


    }
