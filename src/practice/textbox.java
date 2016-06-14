package practice;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by mist36 on 2016/06/08.
 */
public class textbox extends Application {

    Label label;
    TextField field;
    Button button;

    public static void main (String[]args){

        launch(args);
    }

    public void start(Stage stage) throws Exception {
        label = new Label("This is JavaFX!");
        field = new TextField("aska");
        button = new Button("Click");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String msg =("He is seki");

                field.setText(msg);

            }
        });

        BorderPane pane = new BorderPane();

        pane.setTop(label);
        pane.setCenter(field);
        pane.setBottom(button);

        Scene scene = new Scene(pane, 320, 120);

        stage.setScene(scene);

        stage.show();
    }

}
