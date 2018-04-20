package platformer.ui;

import platformer.domain.GameLogic;
import platformer.domain.MenuLogic;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class GameUI {

    private MenuLogic logic;
    private GameLogic gameLogic;
    private Scene scene;
    private int windowX;
    private int windowY;
    private HashMap<KeyCode, Boolean> activeKeys;
    private Pane gameDraw;
    private Polygon characterPoly;

    public GameUI(MenuLogic menuLogic, GameLogic gameLogic, int gameWindowX, int gameWindowY) {
        this.logic = menuLogic;
        this.gameLogic = gameLogic;
        this.windowX = gameWindowX;
        this.windowY = gameWindowY;
        this.activeKeys = new HashMap();

        this.setup();
    }

    public Scene getScene() {
        return this.scene;
    }

    public Map getKeys() {
        return this.activeKeys;
    }
    
    public void setCharacterPoly(Polygon poly) {
        gameDraw.getChildren().remove(this.characterPoly);
        this.characterPoly = poly;
        gameDraw.getChildren().add(this.characterPoly);
    }
    
    public void addShape(Shape shape) {
        gameDraw.getChildren().add(shape);
    }

    private void setup() {
        logic.centerStage();
        
        gameLogic.setActiveKeys(activeKeys);
        
        Button goBack = new Button("return to main menu");
        
        gameDraw = new Pane();

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
    }
}
