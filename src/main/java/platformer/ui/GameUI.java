package platformer.ui;

import java.text.DecimalFormat;
import platformer.domain.GameLogic;
import platformer.domain.MenuLogic;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
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
    private Label timer;
    private long startTime;
    private DecimalFormat formatter;
    private Polygon characterPoly;

    public GameUI(MenuLogic menuLogic, GameLogic gameLogic, int gameWindowX, int gameWindowY) {
        this.logic = menuLogic;
        this.gameLogic = gameLogic;
        this.windowX = gameWindowX;
        this.windowY = gameWindowY;
        activeKeys = new HashMap();
        timer = new Label("Sample Text");
        timer.setTextFill(Paint.valueOf("RED"));
        formatter = new DecimalFormat("0.00");

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
        if (!gameDraw.getChildren().contains(shape)) {
            gameDraw.getChildren().add(shape);
        }
    }
    
    public void removeShape(Shape shape) {
        gameDraw.getChildren().remove(shape);
    }
    
    public void setStartTime(long now) {
        startTime = now;
    }
    
    public void updateTimer(long now) {
        timer.setText(formatter.format(((double) now - startTime) / 1_000_000_000));
    }
    
    public void clear() {
        gameDraw.getChildren().clear();
        gameDraw.getChildren().add(characterPoly);
    }
    
    public void startGame() {
        gameLogic.startGame();
    }

    private void setup() {
        logic.centerStage();
        
        timer.setTranslateY(-windowY + 30);
        gameLogic.setActiveKeys(activeKeys);
        
        Button goBack = new Button("return to main menu");
        
        gameDraw = new Pane();
        
        HBox infoBar = new HBox();
        infoBar.getChildren().add(goBack);
        infoBar.getChildren().add(timer);
        
        infoBar.setSpacing(-149);

        BorderPane gameLayout = new BorderPane();
        gameLayout.setCenter(gameDraw);
        gameLayout.setBottom(infoBar);

        this.scene = new Scene(gameLayout, windowX, windowY);

        goBack.setOnAction((ActionEvent event) -> {
            gameLogic.fullReset();
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
