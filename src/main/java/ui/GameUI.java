
package ui;

import domain.MenuLogic;
import domain.entity.GameCharacter;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameUI {
    private MenuLogic logic;
    private Scene scene;
    private int windowX;
    private int windowY;
    
    public GameUI(MenuLogic menuLogic, int gameWindowX, int gameWindowY) {
        this.logic = menuLogic;
        this.windowX = gameWindowX;
        this.windowY = gameWindowY;
        
        this.init();
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    private void init() {
        Button goBack = new Button("return to main menu");
        
        GameCharacter character = new GameCharacter(windowX / 2, windowY / 2);

        Pane gameDraw = new Pane();
        gameDraw.getChildren().add(character.getPoly());

        BorderPane gameLayout = new BorderPane();
        gameLayout.setCenter(gameDraw);
        gameLayout.setBottom(goBack);

        this.scene = new Scene(gameLayout, windowX, windowY);

        goBack.setOnAction((ActionEvent event) -> {
            logic.goToMain();
        });

        scene.setOnKeyPressed(event -> {
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
                logic.goToMain();
            }
        });
    }
}
