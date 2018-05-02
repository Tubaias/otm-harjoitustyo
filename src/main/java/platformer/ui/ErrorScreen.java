package platformer.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ErrorScreen {

    private Stage stage;
    private Exception exception;
    private Scene scene;

    public ErrorScreen(Stage stage, Exception e) {
        this.stage = stage;
        this.exception = e;

        this.setup();
    }
    
    public Scene getScene() {
        return this.scene;
    }

    private void setup() {
        GridPane exitLayout = new GridPane();
        Button confirmButton = new Button("Confirm");

        exitLayout.setAlignment(Pos.CENTER);

        exitLayout.add(new Label("OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!"), 0, 0);
        exitLayout.add(new Label(""), 0, 1);
        exitLayout.add(new Label(exception.getMessage()), 0, 2);
        exitLayout.add(new Label(""), 0, 3);
        exitLayout.add(confirmButton, 0, 4);

        scene = new Scene(exitLayout, 360, 120);

        scene.setOnKeyPressed(event -> {
            stage.close();
        });

        confirmButton.setOnAction(event -> {
            stage.close();
        });
    }
}
