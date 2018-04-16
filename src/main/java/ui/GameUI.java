package ui;

import domain.GameLogic;
import domain.MenuLogic;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameUI {

    private MenuLogic logic;
    private GameLogic gameLogic;
    private Scene scene;
    private int windowX;
    private int windowY;
    private HashMap<KeyCode, Boolean> activeKeys;

    public GameUI(MenuLogic menuLogic, GameLogic gameLogic, int gameWindowX, int gameWindowY) {
        this.logic = menuLogic;
        this.gameLogic = gameLogic;
        this.windowX = gameWindowX;
        this.windowY = gameWindowY;
        this.activeKeys = new HashMap();

        this.init();
    }

    public Scene getScene() {
        return this.scene;
    }

    public Map getKeys() {
        return this.activeKeys;
    }

    private void init() {
        logic.centerStage();
        
        gameLogic.setActiveKeys(activeKeys);
        
        Button goBack = new Button("return to main menu");
        
        Pane gameDraw = new Pane();
        gameDraw.getChildren().add(gameLogic.getCharacter().getPoly());

        BorderPane gameLayout = new BorderPane();
        gameLayout.setCenter(gameDraw);
        gameLayout.setBottom(goBack);

        this.scene = new Scene(gameLayout, windowX, windowY);

        goBack.setOnAction((ActionEvent event) -> {
            logic.goToMain();
        });

        scene.setOnKeyPressed(event -> {
            activeKeys.put(event.getCode(), true);
        });

        scene.setOnKeyReleased(event -> {
            activeKeys.put(event.getCode(), false);
        });

//        scene.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.LEFT) {
//                character.moveLeft(10);
//            }
//
//            if (event.getCode() == KeyCode.RIGHT) {
//                character.moveRight(10);
//            }
//
//            if (event.getCode() == KeyCode.UP) {
//                character.moveUp(10);
//            }
//
//            if (event.getCode() == KeyCode.DOWN) {
//                character.moveDown(10);
//            }
//
//            if (event.getCode() == KeyCode.P) {
//                System.out.println(character);
//            }
//
//            if (event.getCode() == KeyCode.ESCAPE) {
//                logic.goToMain();
//            }
//        });
    }
}
