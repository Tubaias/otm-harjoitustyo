package asd;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("spaget");
        int windowX = 280;
        int windowY = 360;

        int gameWindowX = 1280;
        int gameWindowY = 720;

        VBox mainLayout = new VBox();
        mainLayout.setSpacing(30);
        Scene mainScene = new Scene(mainLayout, windowX, windowY);
        stage.setResizable(false);

        mainLayout.setAlignment(Pos.CENTER);

        mainLayout.getChildren().add(new Label("SUPER PLATFORMER EXTREME 6000"));

        Button startButton = new Button("Start game");
        Button levelSelectButton = new Button("Level select");
        Button timesButton = new Button("Best times");
        Button optionsButton = new Button("Options");

        Button goBack = new Button("return to main menu");

        mainLayout.getChildren().addAll(startButton, levelSelectButton, timesButton, optionsButton);

        Polygon character = new Polygon(0, 0, 50, 0, 50, 50, 0, 50);
        character.setTranslateX(gameWindowX / 2);
        character.setTranslateY(gameWindowY / 2);

        Pane gameDraw = new Pane();
        gameDraw.getChildren().add(character);

        BorderPane gameLayout = new BorderPane();
        gameLayout.setCenter(gameDraw);
        gameLayout.setBottom(goBack);

        Scene gameScene = new Scene(gameLayout, gameWindowX, gameWindowY);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(gameScene);
            }
        });

        goBack.setOnAction((ActionEvent event) -> {
            stage.setScene(mainScene);
        });

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                character.setTranslateX(character.getTranslateX() - 10);
            }

            if (event.getCode() == KeyCode.RIGHT) {
                character.setTranslateX(character.getTranslateX() + 10);
            }
            
            if (event.getCode() == KeyCode.UP) {
                character.setTranslateY(character.getTranslateY() - 10);
            }

            if (event.getCode() == KeyCode.DOWN) {
                character.setTranslateY(character.getTranslateY() + 10);
            }
        });

        stage.setScene(mainScene);
        stage.show();
    }
}
