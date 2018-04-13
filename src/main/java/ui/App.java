package ui;

import domain.MenuLogic;
import domain.entity.GameCharacter;
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
        MenuLogic menuLogic = new MenuLogic(stage);
        OptionsMenu optionsMenu = new OptionsMenu(menuLogic, windowX, windowY);

        Scene mainScene = new Scene(mainLayout, windowX, windowY);
        menuLogic.setMainScene(mainScene);

        mainLayout.setSpacing(30);
        stage.setResizable(false);
        mainLayout.setAlignment(Pos.CENTER);

        mainLayout.getChildren().add(new Label("SUPER PLATFORMER EXTREME 6000"));

        Button startButton = new Button("Start game");
        Button levelSelectButton = new Button("Level select");
        Button timesButton = new Button("Best times");
        Button optionsButton = new Button("Options");

        Button goBack = new Button("return to main menu");

        mainLayout.getChildren().addAll(startButton, levelSelectButton, timesButton, optionsButton);
        
        GameCharacter character = new GameCharacter(gameWindowX / 2, gameWindowY / 2);

        Pane gameDraw = new Pane();
        gameDraw.getChildren().add(character.getPoly());

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
        
        optionsButton.setOnAction((ActionEvent event) -> {
            stage.setScene(optionsMenu.getScene());
        });

        goBack.setOnAction((ActionEvent event) -> {
            stage.setScene(mainScene);
        });

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                character.moveLeft(10);
            }

            if (event.getCode() == KeyCode.RIGHT) {
                character.moveRight(10);
            }
            
            if (event.getCode() == KeyCode.UP) {
                character.moveUp(10);
            }

            if (event.getCode() == KeyCode.DOWN) {
                character.moveDown(10);
            }
            
            if (event.getCode() == KeyCode.P) {
                System.out.println(character);
            }
            
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(mainScene);
            }
        });
        
        mainScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                menuLogic.exit();
            }
        });

        stage.setScene(mainScene);
        stage.show();
    }
}
